package com.ideas2it.ism.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.InterviewLevel;
import com.ideas2it.ism.common.InterviewType;
import com.ideas2it.ism.common.CandidateStatus;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.service.ScheduleService;

@Controller
public class ScheduleController {
	@Autowired
    private ScheduleService scheduleService;


    /**
     * Dispatches the create schedule page.
     *
     * @param model - Used to send a new schedule to the dispatched page
     *
     * @return CREATE_SCHEDULE_JSP - Page to be displayed to the client
     */ 
	  @RequestMapping(value = Constant.GET_RECRUITER_OPERATIONS, method = RequestMethod.GET)
    public String getRecruitersOperations(Model model) {
    	model.addAttribute(Constant.SCHEDULE_STATUS, new ArrayList<ScheduleStatus>(Arrays.asList(ScheduleStatus.values())));
    	model.addAttribute(Constant.CANDIDATE_STATUS, new ArrayList<CandidateStatus>(Arrays.asList(CandidateStatus.values())));
        return Constant.RECRUITER_JSP;
    }

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
    	model.addAttribute(Constant.SCHEDULE, new Schedule());
    	model.addAttribute(Constant.LEVELS, new ArrayList<InterviewLevel>(Arrays.asList(InterviewLevel.values())));
    	model.addAttribute(Constant.TYPES, new ArrayList<InterviewType>(Arrays.asList(InterviewType.values())));
    	model.addAttribute(Constant.CANDIDATE_ID, candidateId);
        return Constant.CREATE_SCHEDULE_JSP;
    }
	
    /**
     * Creates a schedule for a candidate.
     * It will be created by recruiter.
     *
     * @param request - An HttpServletRequest object that contains the request
     * the client has made of the servlet
     * @param ModelAttribute schedule - Schedule model object from the browser
     * @param model - Used to send a new schedule to the dispatched page
     *
     * @return VIEW_CANDIDATES - Page to be displayed to the client
     */ 
	@RequestMapping(value = Constant.CREATE_SCHEDULE, method = RequestMethod.POST)
    public String createSchedule(HttpServletRequest request, 
    		@ModelAttribute(Constant.SCHEDULE)Schedule schedule, Model model) {
    	long candidateId = Integer.parseInt(request.getParameter(Constant.CANDIDATE_ID));
    	scheduleService.addSchedule(schedule, candidateId,
    			    request.getParameter(Constant.SCHEDULED_DATE), 
    			    request.getParameter(Constant.SCHEDULED_TIME) + Constant.SECONDS);
    	model.addAttribute(Constant.SCHEDULE, new Schedule());
        return Constant.REDIRECT + Constant.VIEW_CANDIDATES;
    }
 
    /**
     * Gets schedule by id.
     * 
     * @param RequestParam scheduleId - Used to get the schedule by id.
     * @param model - Used to send schedule object to jsp.
     * 
     * @return VIEW_SCHEDULE_JSP - 
     */
    @RequestMapping(value = Constant.GET_SCHEDULE, method = RequestMethod.GET)  
    private String getScheduleById(@RequestParam(name = Constant.SCHEDULE_ID) long scheduleId, Model model) {
        model.addAttribute(Constant.SCHEDULE, scheduleService.getScheduleById(scheduleId));
        model.addAttribute(Constant.NEW_SCHEDULE, new Schedule());
        return Constant.VIEW_SCHEDULE_JSP;
    }
 
    
    /**
     * When the employee update the interview result the status of the schedule
     * is updated as per the feedback send by the employee.
     * 
     * @param request - An HttpServletRequest object that contains the request
     * the client has made of the servlet
     * @param ModelAttribute schedule - Schedule model object from the browser
     * @return
     */
    @RequestMapping(value = Constant.INTERVIEW_RESULT, method = RequestMethod.GET)  
    private String updateResult(HttpServletRequest request, Model model) {
        String feedBack = request.getParameter(Constant.FEED_BACK); 
        long scheduleId = Long.parseLong(request.getParameter(Constant.ID));
        String result = request.getParameter(Constant.RESULT);
        scheduleService.updateResult(feedBack, scheduleId, result);
        return Constant.INDEX_JSP;
    }
  
     /** 
     * Gets all schedules by status.
     * 
     * @param RequestParam status - Status of the schedule entered by the client.
     * @param model - Used to send schedules to the jsp.
     * 
     * @return VIEW_SCHEDULES_JSP - 
     */
    @RequestMapping(value = Constant.SCHEDULE_BY_STATUS, method = RequestMethod.GET)  
    private String getSchedulesByStatus(@RequestParam(Constant.STATUS)ScheduleStatus status, Model model) {
        model.addAttribute(Constant.SCHEDULES, scheduleService.getSchedulesByStatus(status)); 
        model.addAttribute(Constant.SCHEDULE, new Schedule());
        return Constant.VIEW_SCHEDULES_JSP;
    }
 
    /**
     * Updates the schedule status as rescheduled and also reschedules.
     * 
     * @param ModelAttribute schedule - Needs to be rescheduled.
     * @param RequestParam comment - Comment given by the client for rescheduling.
     * @param model - Used to send schedule object to jsp.
     * 
     * @return CREATE_RESCHEDULE_JSP - 
     */
    @RequestMapping(value = Constant.RESCHEDULE, method = RequestMethod.POST)  
    private String reschedule(Model model,
    		@ModelAttribute(Constant.SCHEDULE)Schedule newSchedule,
    		@RequestParam(Constant.SCHEDULED_DATE)String date,
    		@RequestParam(Constant.SCHEDULED_TIME)String time,
    		@RequestParam(Constant.COMMENT)String comment,
    		@RequestParam(Constant.SCHEDULE_ID)long scheduleId,
    		@RequestParam(Constant.CANDIDATE_ID)long candidateId
    		) {
        model.addAttribute(Constant.SCHEDULE,
        		scheduleService.reschedule(newSchedule, comment,
				scheduleId, candidateId, date, time + Constant.SECONDS));
        model.addAttribute(Constant.NEW_SCHEDULE, new Schedule());
        return Constant.VIEW_SCHEDULE_JSP;
    }
 
    /**
     * Gets all pending schedules.
     * 
     * @param ModelAttribute schedule - Needs to be cancelled.
     * @param RequestParam comment - Comment given by the client for cancelling.
     * @param model - Used to send schedule object to jsp.
     * 
     * @return SCHEDULE_BY_STATUS - 
     */
    @RequestMapping(value = Constant.CANCEL_SCHEDULE, method = RequestMethod.POST)  
    private String cancelSchedule(Model model, @ModelAttribute(Constant.SCHEDULE)Schedule schedule) {
    	scheduleService.cancelSchedule(schedule);
        return Constant.GET_RECRUITER_OPERATIONS;
    }
 
    /**
     * Gets interviewers available for that schedule
     * 
     * @param ModelAttribute schedule - Needs to be cancelled.
     * @param RequestParam comment - Comment given by the client for cancelling.
     * @param model - Used to send schedule object to jsp.
     * 
     * @return SCHEDULE_BY_STATUS - 
     */
    @RequestMapping(value = Constant.GET_INTERVIEWERS, method = RequestMethod.POST)  
    private String getInterviewersByTechnology(Model model,
    		@RequestParam(Constant.SCHEDULE_ID)long scheduleId) {
    	scheduleService.getScheduleAndInterviewersByTechnology(scheduleId);
        return Constant.GET_RECRUITER_OPERATIONS;
    }
}