package com.ideas2it.ism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.entity.Employee;
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
     * All the newly assinged schedules for the employee are fetched from DB.
     * 
     * @param model - Used to send candidate object along with request to jsp.
     * @return VIEW_CANDIDATES_JSP - Display all the fetched candidates information.
     */
    @RequestMapping(value = Constant.NEW_SCHEDULES, method = RequestMethod.GET)  
    private String viewNewSchedules(@RequestParam(name = Constant.ID) long employeeId, 
    		Model model) {
        try { 
            Employee employee = employeeService.getEmployeeWithNewSchedulesById(employeeId);
            model.addAttribute(Constant.EMPLOYEE, employee); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_NEW_SCHEDULES_JSP;
    }
    
    /**
     * Gets schedule by id.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.ACCEPT_SCHEDULE, method = RequestMethod.GET)  
    private String acceptSchedule(@RequestParam(name = Constant.SCHEDULE_ID) long scheduleId, 
    		@RequestParam(name = Constant.ID) long employeeId, 
    		@RequestParam(name = Constant.CANDIDATE_ID) long candidateId, Model model) {
        model.addAttribute(Constant.EMPLOYEE, employeeService.acceptSchedule(candidateId, 
        		employeeId, scheduleId));
        return Constant.VIEW_NEW_SCHEDULES_JSP;
    }
    
	/**
     * All the pending schedules for the employee are fetched from DB.
     * 
     * @param model - Used to send candidate object along with request to jsp.
     * @return VIEW_CANDIDATES_JSP - Display all the fetched candidates information.
     */
    @RequestMapping(value = Constant.PENDING_SCHEDULES, method = RequestMethod.GET)  
    private String viewPendingSchedules(@RequestParam(name = Constant.ID) long employeeId, 
    		Model model) {
        try { 
            Employee employee = employeeService.getEmployeeWithPendingSchedulesById(employeeId);
            model.addAttribute(Constant.EMPLOYEE, employee); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_PENDING_SCHEDULES_JSP;
    }
}
