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
     *
     * @return String - Page to be displayed to the client
     */ 
	@RequestMapping(value = Constant.CREATE_SCHEDULE, method = RequestMethod.POST)
    public String createSchedule(HttpServletRequest request, 
    		@ModelAttribute(Constant.SCHEDULE)Schedule schedule, Model model) {
        try {
        	System.out.println(request.getParameter(Constant.CANDIDATE_ID));
        	long candidateId = Integer.parseInt(request.getParameter(Constant.CANDIDATE_ID));
        	scheduleService.addSchedule(schedule, candidateId,
        			    request.getParameter(Constant.SCHEDULED_DATE), 
        			    request.getParameter(Constant.SCHEDULED_TIME) + Constant.SECONDS);
        	model.addAttribute(Constant.SCHEDULE, new Schedule());
            return Constant.INDEX_JSP;
        } catch(Exception exception) {
            request.setAttribute(Constant.MESSAGE, exception.getMessage());
            return Constant.INDEX_JSP;
        }
    }
 
    /**
     * Gets schedule by id.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.GET_SCHEDULE, method = RequestMethod.GET)  
    private String getScheduleById(@RequestParam(name = Constant.SCHEDULE_ID) long scheduleId, Model model) {
        model.addAttribute(Constant.SCHEDULE, scheduleService.getScheduleById(scheduleId));
        return Constant.VIEW_SCHEDULE_JSP;
    }
 
    /**
     * Gets all pending schedules.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.SCHEDULE_BY_STATUS, method = RequestMethod.GET)  
    private String getSchedulesByStatus(HttpServletRequest request, Model model) {
        model.addAttribute(Constant.SCHEDULES, scheduleService.getAllSchedules()); 
        model.addAttribute(Constant.SCHEDULE, new Schedule());
        return Constant.VIEW_SCHEDULES_JSP;
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
}