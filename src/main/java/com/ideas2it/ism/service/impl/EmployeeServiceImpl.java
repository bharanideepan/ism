package com.ideas2it.ism.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.dao.EmployeeRepository;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.info.ScheduleInfo;
import com.ideas2it.ism.service.CandidateService;
import com.ideas2it.ism.service.EmployeeService;
import com.ideas2it.ism.service.ScheduleRejectionTrackService;
import com.ideas2it.ism.service.ScheduleService;
import com.ideas2it.ism.service.ScheduleRejectionTrackService;

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
	@Autowired
	ScheduleRejectionTrackService scheduleRejectionTrackService;

	public List<ScheduleInfo> getEmployeeNewScheduleInfosById(long employeeId) {
		return scheduleService.getEmployeeNewScheduleInfosById(employeeId);
	}

	public List<ScheduleInfo> getEmployeePendingScheduleInfosById(long employeeId) {
		return scheduleService.getEmployeePendingScheduleInfosById(employeeId);
	}

	public List<ScheduleInfo> acceptAndGetNewScheduleInfos(long candidateId, long employeeId, long scheduleId) {
        scheduleService.updateScheduleStatus(scheduleId, ScheduleStatus.Pending);
        candidateService.updateCandidateStatus(candidateId, Result.Pending);
		return scheduleService.getEmployeeNewScheduleInfosById(employeeId);
	}

	public List<ScheduleInfo> rejectAndGetNewScheduleInfos(long candidateId, long employeeId, long scheduleId, String comment) {
        Employee employee = this.getEmployeeById(employeeId);
        Schedule schedule = scheduleService.updateScheduleStatus(scheduleId, ScheduleStatus.Declined);
        candidateService.updateCandidateStatus(candidateId, Result.New);
        scheduleRejectionTrackService.createScheduleRejectionTrack(employee, schedule, comment);
		return scheduleService.getEmployeeNewScheduleInfosById(employeeId);
	}
	
	public List<Employee> getEmployeesByTechnology(Technology technology) {
		return employeeRepository.fetchEmployeesByTechnology(technology);
	}

	public Employee getEmployeeById(long employeeId) {
		return employeeRepository.getOne(employeeId);
	}

	@Override
	public List<Employee> getNewEmployees() {
		return employeeRepository.getNewEmployees();
	}
}
