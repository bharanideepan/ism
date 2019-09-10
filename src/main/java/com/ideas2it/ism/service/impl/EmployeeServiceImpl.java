package com.ideas2it.ism.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.dao.EmployeeRepository;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.service.CandidateService;
import com.ideas2it.ism.service.EmployeeService;
import com.ideas2it.ism.service.ScheduleService;

/**
 * Gets the schedule assigned for the employee from {link-@ScheduleService} and set 
 * it in the employee and fetch employee of the particular department to assign to 
 * a schedule. 
 * @author M.Mani Bharathi.
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	CandidateService candidateService;
	@Autowired
	ScheduleService scheduleService;

	@Override
	public Employee getEmployeeWithNewSchedulesById(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setSchedules(scheduleService.getEmployeeNewSchedulesById(employeeId));
		return employee;
	}

	@Override
	public Employee getEmployeeWithPendingSchedulesById(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setSchedules(scheduleService.getEmployeePendingSchedulesById(employeeId));
		return employee;
	}

	@Override
	public Employee acceptSchedule(long candidateId, long employeeId, long scheduleId) {
        Employee employee = employeeRepository.getOne(employeeId);
        scheduleService.updateScheduleStatus(scheduleId, ScheduleStatus.Pending);
        candidateService.updateCandidateStatus(candidateId, Result.Pending);
        employee.setSchedules(scheduleService.getEmployeeNewSchedulesById(employeeId));      
		return employee;
	}
	
	public List<Employee> getEmployeesByTechnology(Technology technology) {
		return employeeRepository.fetchEmployeesByTechnology(technology);
	}
    
}
