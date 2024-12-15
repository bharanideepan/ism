package com.ideas2it.ism.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.InterviewLevel;
import com.ideas2it.ism.common.InterviewType;
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.CandidateStatus;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.info.ScheduleInfo;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.service.EmployeeService;
import com.ideas2it.ism.service.ScheduleService;

@Controller
public class ScheduleController {
	@Autowired
    private ScheduleService scheduleService;
	@Autowired
	EmployeeService employeeService;

    /**
     * Dispatches the create schedule page.
     *
     * @param candidateId - Id of the candidate who needs to be scheduled
     * @param model - Used to send a new schedule to the dispatched page
     *
     * @return CREATE_SCHEDULE_JSP - Page to be displayed to the client
     */ 
	  @RequestMapping(value = Constant.SCHEDULE_FORM, method = RequestMethod.GET)
    public String getScheduleForm(@RequestParam(name = Constant.CANDIDATE_ID) 
        long candidateId, Model model) {
    	model.addAttribute(Constant.SCHEDULE, new ScheduleInfo());
    	model.addAttribute(Constant.TYPES, new ArrayList<InterviewType>(Arrays.asList(InterviewType.values())));
    	Map<String, Object> candidateAndInterviewers = scheduleService.getCandidateAndInterviewersByTechnology(candidateId);
        model.addAttribute(Constant.INTERVIEWERS, candidateAndInterviewers.get(Constant.INTERVIEWERS));
    	model.addAttribute(Constant.CANDIDATE, candidateAndInterviewers.get(Constant.CANDIDATE));
        return Constant.CREATE_SCHEDULE_JSP;
    }
	
    /**
     * Creates a schedule for a candidate.
     * It will be created by recruiter.
     *
     * @param model - Used to send a new schedule to the dispatched page
     * @param ModelAttribute scheduleInfo - Schedule model object from the browser
     * @param ModelAttribute date - Scheduled date for the candidate(pattern="yyyy-MM-dd'T'HH:mm")
     * @param ModelAttribute interviewerId - Id of the interviewer may or may not present
     * @param ModelAttribute candidateId - Id of the candidate should be present
     *
     * @return VIEW_CANDIDATES - Page to be displayed to the client
     */ 
	@RequestMapping(value = Constant.CREATE_SCHEDULE, method = RequestMethod.POST)
    public String createSchedule( Model model,
    		@ModelAttribute(Constant.SCHEDULE)ScheduleInfo scheduleInfo,
    		@RequestParam(Constant.SCHEDULED_DATE)
    		@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")Date date,
    		@RequestParam(Constant.INTERVIEWER_ID)String interviewerId,
    		@RequestParam(Constant.CANDIDATE_ID)long candidateId) {
    	scheduleService.addSchedule(scheduleInfo, candidateId, interviewerId, date);
        return Constant.REDIRECT + Constant.VIEW_SCHEDULES;
    }
    
    /**
     * When the employee update the interview result the status of the schedule
     * is updated as per the feedback send by the employee.
     * 
     * @param request - An HttpServletRequest object that contains the request
     * the client has made of the servlet
     * @param ModelAttribute schedule - Schedule model object from the browser
     * 
     * @return
     */
    @RequestMapping(value = Constant.INTERVIEW_RESULT, method = RequestMethod.GET)  
    private String updateResult(Model model, HttpServletRequest request,
    		@RequestParam(Constant.FEED_BACK)String feedBack,
    		@RequestParam(Constant.RESULT)String result,
    		@RequestParam(Constant.ID)long scheduleId
    		) {
    	HttpSession session = request.getSession();
    	session.setAttribute(Constant.NO_OF_PENDING, (int)session.getAttribute(Constant.NO_OF_PENDING) - 1);
        scheduleService.updateResult(feedBack, scheduleId, result);
        return Constant.REDIRECT + Constant.PENDING_SCHEDULES;
    }
  
     /** 
     * Gets all schedules by status.
     * 
     * @param status - Status of the schedule entered by the client.
     * @param model - Used to send schedules to the jsp.
     * 
     * @return VIEW_SCHEDULES_JSP - 
     */
    @RequestMapping(value = Constant.VIEW_SCHEDULES_BY_STATUS, method = RequestMethod.GET)  
    private String getSchedulesByStatus(@RequestParam(Constant.STATUS)ScheduleStatus status,
    		Model model) {
        model.addAttribute(Constant.SCHEDULES, scheduleService.getScheduleInfosByStatus(status)); 
        return Constant.VIEW_SCHEDULES_JSP;
    }

    /**
     * Updates the schedule status as rescheduled and also reschedules.
     * 
     * @param ModelAttribute schedule - Needs to be rescheduled.
     * @param RequestParam comment - Comment given by the client for rescheduling.
     * @param model - Used to send schedule object to jsp.
     * 
     * @return VIEW_SCHEDULE_JSP - 
     */
    @RequestMapping(value = Constant.RESCHEDULE, method = RequestMethod.POST)  
    private String reschedule(Model model,
    		@ModelAttribute(Constant.NEW_SCHEDULE)ScheduleInfo scheduleInfo,
    		@RequestParam(Constant.COMMENT)String comment,
    		@RequestParam(Constant.SCHEDULE_ID)long scheduleId,
    		@RequestParam(name = Constant.INTERVIEWER_ID,
			required = false)String interviewerId,
    		@RequestParam(Constant.SCHEDULED_DATE)
    				@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")Date date) {
    	
		scheduleService.reschedule(scheduleInfo, comment, scheduleId, date, interviewerId);
        return "redirect:/viewSchedulesByStatus?status=New";
    }

    /**
     * Updates the schedule .
     * 
     * @param scheduleInfo - Needs to be updated.
     * @param RequestParam comment - Comment given by the client for rescheduling.
     * @param model - Used to send schedule object to jsp.
     * 
     * @return VIEW_SCHEDULE_JSP - 
     */
    @RequestMapping(value = Constant.UPDATE_SCHEDULE, method = RequestMethod.POST)  
    private String updateSchedule(Model model,
    		@ModelAttribute(Constant.SCHEDULE)ScheduleInfo scheduleInfo,
    		@RequestParam(Constant.SCHEDULE_ID)long scheduleId,
    		@RequestParam(name = Constant.INTERVIEWER_ID,
    				required = false)String interviewerId,
    		@RequestParam(Constant.SCHEDULED_DATE)
    				@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")Date date) {
    	
        model.addAttribute(Constant.SCHEDULE,
        		scheduleService.updateSchedule(scheduleInfo ,scheduleId, date, interviewerId));
        model.addAttribute(Constant.NEW_SCHEDULE, new ScheduleInfo());
        return "redirect:/viewSchedulesByStatus?status=New";
    }
 
    /**
     * Cancels the schedule
     * 
     * @param scheduleId - Id of the schedule needs to be cancelled.
     * @param RequestParam comment - Comment given by the client for cancelling.
     * 
     * @return GET_RECRUITER_OPERATIONS - 
     */
    @RequestMapping(value = Constant.CANCEL_SCHEDULE, method = RequestMethod.POST)  
    private String cancelSchedule(@RequestParam(Constant.SCHEDULE_ID)long scheduleId,
    		@RequestParam(Constant.COMMENT)String comment) {
    	
    	scheduleService.cancelSchedule(scheduleId, comment);
        return "redirect:/viewSchedulesByStatus?status=New";
    }
 
    /**
     * Gets interviewers available for that schedule
     * 
     * @param model - Used to send schedule object to jsp.
     * @param scheduleId - Id of the schedule to be assigned
     * 
     * @return ASSIGN_INTERVIEWER_JSP - 
     */
    @RequestMapping(value = Constant.GET_SCHEDULE_WITH_INTERVIEWERS, method = RequestMethod.GET)  
    private String getInterviewersByTechnology(HttpServletRequest request, Model model,
    		@RequestParam(Constant.SCHEDULE_ID)long scheduleId) {
    	Map<String, Object> ScheduleInfoAndInterviewers= 
    			scheduleService.getScheduleInfoAndInterviewersByTechnology(scheduleId);
        model.addAttribute(Constant.SCHEDULE, ScheduleInfoAndInterviewers.get(Constant.SCHEDULE));
        model.addAttribute(Constant.INTERVIEWERS, ScheduleInfoAndInterviewers.get(Constant.INTERVIEWERS));
        model.addAttribute(Constant.NEW_SCHEDULE, new ScheduleInfo());
    	model.addAttribute(Constant.TYPES, new ArrayList<InterviewType>(Arrays.asList(InterviewType.values())));
        return Constant.VIEW_SCHEDULE_JSP;
    }
 
    /**
     * Gets interviewers available for that schedule
     * 
     * @param model - Used to send schedule object to jsp.
     * @param RequestParam scheduleId - Id of the schedule to be assigned
     * @param RequestParam candidateId - Id of the candidate to be assigned
     * 
     * @return VIEW_SCHEDULE_JSP - 
     */
    @RequestMapping(value = Constant.ASSIGN_INTERVIEWER, method = RequestMethod.GET)  
    private String assignInterviewer(Model model, HttpServletRequest request,
    		@RequestParam(Constant.SCHEDULE_ID)long scheduleId,
    		@RequestParam(Constant.INTERVIEWER_ID)long employeeId) {
    	HttpSession session = request.getSession();
        model.addAttribute(Constant.SCHEDULE, scheduleService.assignSchedule(scheduleId, employeeId));
        return Constant.REDIRECT_SCHEDULE_WITH_INTERVIEWER + scheduleId;
    }
 
    /**
     * Gets all schedules
     * 
     * @param model - Used to send schedule objects to jsp.
     * 
     * @return VIEW_SCHEDULES_JSP - 
     */
    @RequestMapping(value = Constant.VIEW_SCHEDULES, method = RequestMethod.GET)  
    private String getAllScheduleInfos(Model model) {
    	try {
            model.addAttribute(Constant.PAGENATION_INFO, scheduleService.getAllScheduleInfos());
    	} catch (IsmException e) {
    		System.out.println(e.getMessage());
    	}
        return Constant.VIEW_SCHEDULES_JSP;
    }
    
    /**
     * Gets all schedules corresponding to the managers department.
     * 
     * @param request - An HttpServletRequest object that contains the request
     * the client has made of the servlet
     * @param model - Used to send schedule objects to jsp.
     * 
     * @return VIEW_SCHEDULES_JSP - 
     */
    @RequestMapping(value = Constant.VIEW_SCHEDULES_MANAGER, method = RequestMethod.GET)  
    private String getScheduleInfosByManager(HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	Map<String, Object> schedulesAndCounts = scheduleService.getSchedulesAndCounts(
				(long)session.getAttribute(Constant.EMPLOYEE));
        model.addAttribute(Constant.PAGENATION_INFO, schedulesAndCounts.get(Constant.SCHEDULES));
        session.setAttribute(Constant.NO_OF_NEW, schedulesAndCounts.get(Constant.NO_OF_NEW));
        session.setAttribute(Constant.NO_OF_PENDING, schedulesAndCounts.get(Constant.NO_OF_PENDING));
        session.setAttribute(Constant.NO_OF_DECLINED, schedulesAndCounts.get(Constant.NO_OF_DECLINED));
        return Constant.VIEW__MANAGER_SCHEDULES_JSP;
    }
    
    /**
     * Gets all schedules which are scheduled on the given date.
     * 
     * @param date - Date which is entered by the client.
     * @param model - Used to send schedule objects to jsp.
     * 
     * @return VIEW_SCHEDULES_JSP - 
     */
    @RequestMapping(value = Constant.SCHEDULES_BY_DATE, method = RequestMethod.GET)  
    private String getScheduleInfosByDate(@RequestParam(Constant.SCHEDULED_DATE)String date, Model model) {
        model.addAttribute(Constant.PAGENATION_INFO, scheduleService.getScheduleInfosByDate(date));
        return Constant.VIEW_SCHEDULES_JSP;
    }
    
    /**
     * Gets all schedules which are scheduled on the given date.
     * 
     * @param date - Date which is entered by the client.
     * @param model - Used to send schedule objects to jsp.
     * 
     * @return VIEW_SCHEDULES_JSP - 
     */
    @RequestMapping(value = Constant.DECLINED_SCHEDULES, method = RequestMethod.GET)  
    private String getDeclinedScheduleInfos(HttpServletRequest request, Model model) {
        model.addAttribute(Constant.SCHEDULES, scheduleService
        		.getDeclinedScheduleInfosByManagerId((long)request.getSession()
        				.getAttribute(Constant.EMPLOYEE)));
        return Constant.VIEW_DECLINED_SCHEDULES;
    }
    
    /**
     * Sets required information along with request.
     * Set the pageno  for pagenation along with request.
     * Forward the request to displayplayerspage.
     *
     * @param request - User request from server.
     * @param reponse - Response to server from servlet
     * @throws ServletException -
     *         Defines a general exception a servlet can throw when it 
     *         encounters difficulty.
     * @throws IOException - 
     *         general class of exceptions produced by failed or 
     *         interrupted I/O operations.
     */
    @RequestMapping(value = Constant.VIEW_ALL_SCHEDULES, method = RequestMethod.GET)  
    private void viewAllSchedules(HttpServletRequest request, HttpServletResponse 
            response) throws IOException {
    	try {
            int pageNo = Integer.parseInt(request.getParameter(Constant.PAGE_NO));
            String date = request.getParameter(Constant.DATE);
            JSONArray scheduleInfos = scheduleService.retrieveAllSchedules(pageNo, date);
            response.setContentType(Constant.APPLICATION_JSON);
            response.getWriter().write(scheduleInfos.toString());
    	} catch(IsmException e) {
    	    System.out.println(e.getMessage());	
    	}
    }
    
   /**
    * Sets required information along with request.
    * Set the pageno  for pagenation along with request.
    * Forward the request to displayplayerspage.
    *
    * @param request - User request from server.
    * @param reponse - Response to server from servlet
    * @throws ServletException -
    *         Defines a general exception a servlet can throw when it 
    *         encounters difficulty.
    * @throws IOException - 
    *         general class of exceptions produced by failed or 
    *         interrupted I/O operations.
    */
   @RequestMapping(value = Constant.VIEW_ALL_MANAGER_SCHEDULES, method = RequestMethod.GET)  
   private void viewAllManagerSchedules(HttpServletRequest request, HttpServletResponse 
           response) throws IOException {
   	try {
           int pageNo = Integer.parseInt(request.getParameter(Constant.PAGE_NO));         
           String date = request.getParameter(Constant.DATE);
           Technology technology = Technology.valueOf(request.getParameter(Constant.TECHNOLOGY));
           JSONArray scheduleInfos = 
        		   scheduleService.retrieveAllManagerSchedules(technology, pageNo, date);
           response.setContentType(Constant.APPLICATION_JSON);
           response.getWriter().write(scheduleInfos.toString());
   	} catch(IsmException e) {
   	    System.out.println(e.getMessage());	
   	}
   }
   
   /**
    * Gets all schedules which are scheduled on the given date.
    * 
    * @param date - Date which is entered by the client.
    * @param model - Used to send schedule objects to jsp.
    * 
    * @return VIEW_SCHEDULES_JSP - 
    */
   @RequestMapping(value = Constant.MANAGER_SCHEDULES_BY_DATE, method = RequestMethod.GET)  
   private String getManagerScheduleInfosByDate(HttpServletRequest request,
		   @RequestParam(Constant.SCHEDULED_DATE)String date, 
		   @RequestParam(Constant.TECHNOLOGY) Technology technology, Model model) {
       HttpSession session = request.getSession();
   	   Map<String, Object> schedulesAndCounts = scheduleService.getSchedulesAndCounts(
				(long)session.getAttribute(Constant.EMPLOYEE));
	   model.addAttribute(Constant.PAGENATION_INFO, 
			   scheduleService.getManagerScheduleInfosByDate(technology, date));
       session.setAttribute(Constant.NO_OF_NEW, schedulesAndCounts.get(Constant.NO_OF_NEW));
       session.setAttribute(Constant.NO_OF_PENDING, schedulesAndCounts.get(Constant.NO_OF_PENDING));
       session.setAttribute(Constant.NO_OF_DECLINED, schedulesAndCounts.get(Constant.NO_OF_DECLINED));
       return Constant.VIEW__MANAGER_SCHEDULES_JSP;
   }
}