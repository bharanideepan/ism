package com.ideas2it.ism.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.info.ScheduleInfo;
import com.ideas2it.ism.service.EmployeeService;

/**
 * Employees are assigned to an interview schedule. Allows employees to 
 * accept and reject the schedule , then allowed to update the interview result.
 * @author M.Mani Bharathi.
 *
 */
@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
    
	/**
     * All the newly assigned schedules for the employee are fetched from DB.
     * 
     * @param model - Used to send candidate object along with request to jsp.
     * @return VIEW_CANDIDATES_JSP - Display all the fetched candidates information.
     */
    @RequestMapping(value = Constant.NEW_SCHEDULES, method = RequestMethod.GET)  
    private String viewNewSchedules(HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	Long employeeId = (long) session.getAttribute("employee");
    	List<ScheduleInfo> schedules = employeeService.getEmployeeNewScheduleInfosById(employeeId);
        model.addAttribute(Constant.SCHEDULES, schedules);
        session.setAttribute(Constant.NO_OF_NEW, schedules.size());
        session.setAttribute(Constant.NO_OF_PENDING, employeeService
        		.getEmployeePendingScheduleInfosById(employeeId).size());
        return Constant.VIEW_NEW_SCHEDULES_JSP;
    }
    
    /**
     * Accepts schedule
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.ACCEPT_SCHEDULE, method = RequestMethod.GET)  
    private String acceptSchedule(@RequestParam(name = Constant.SCHEDULE_ID) long scheduleId, 
    		@RequestParam(name = Constant.ID) long employeeId, HttpServletRequest request,
    		@RequestParam(name = Constant.CANDIDATE_ID) long candidateId, Model model) {
    	HttpSession session = request.getSession();
    	if((int)session.getAttribute(Constant.NO_OF_NEW) != 0)
    		session.setAttribute(Constant.NO_OF_NEW, (int)session.getAttribute(Constant.NO_OF_NEW) - 1);
    	session.setAttribute(Constant.NO_OF_PENDING, (int)session.getAttribute(Constant.NO_OF_PENDING) + 1);
        model.addAttribute(Constant.SCHEDULES, employeeService.acceptAndGetNewScheduleInfos(candidateId, 
        		employeeId, scheduleId));
        return Constant.VIEW_NEW_SCHEDULES_JSP;
    }
    
    /**
     * Rejects schedule
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.REJECT_SCHEDULE, method = RequestMethod.GET)  
    private String rejectSchedule(@RequestParam(name = Constant.SCHEDULE_ID) long scheduleId, 
    		@RequestParam(name = Constant.ID) long employeeId, HttpServletRequest request, 
    		@RequestParam(name = Constant.CANDIDATE_ID) long candidateId,
    		@RequestParam(name = Constant.COMMENT) String comment ,Model model) {
    	HttpSession session = request.getSession();
    	if((int)session.getAttribute(Constant.NO_OF_NEW) != 0)
    		session.setAttribute(Constant.NO_OF_NEW, (int)session.getAttribute(Constant.NO_OF_NEW) - 1);
        model.addAttribute(Constant.SCHEDULES, employeeService.rejectAndGetNewScheduleInfos(candidateId, 
        		employeeId, scheduleId, comment));
        return Constant.VIEW_NEW_SCHEDULES_JSP;
    }
    
	/**
     * All the pending schedules for the employee are fetched from DB.
     * 
     * @param model - Used to send candidate object along with request to jsp.
     * @return VIEW_CANDIDATES_JSP - Display all the fetched candidates information.
     */
    @RequestMapping(value = Constant.PENDING_SCHEDULES, method = RequestMethod.GET)  
    private String viewPendingSchedules(HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
    	Long employeeId = (long) session.getAttribute("employee");
    	List<ScheduleInfo> schedules = employeeService
        		.getEmployeePendingScheduleInfosById(employeeId);
        model.addAttribute(Constant.SCHEDULES, schedules);
        session.setAttribute(Constant.NO_OF_PENDING, schedules.size());
        session.setAttribute(Constant.NO_OF_NEW, employeeService
        		.getEmployeeNewScheduleInfosById(employeeId).size());
        return Constant.VIEW_PENDING_SCHEDULES_JSP;
    }
}
