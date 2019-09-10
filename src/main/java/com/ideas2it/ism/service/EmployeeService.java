package com.ideas2it.ism.service;

import java.util.List;

import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.entity.Employee;

public interface EmployeeService {
	
	/**
	 * Newly assigned interview schedules for the particular employee
	 * are fetched from the DB. Add the list of schedule is set into the employee.
	 * @param employeeId  -Id of the employee whose schedules to be fetched.
	 * @return employee - Employee along with newly assigned schedules. 
	 */
	Employee getEmployeeWithNewSchedulesById(long employeeId);

	/**
	 * Pending schedules for the particular employee are fetched from the DB. 
	 * Add the list of schedule is set into the employee.
	 * @param employeeId  -Id of the employee whose schedules to be fetched.
	 * @return employee - Employee along with newly assigned schedules. 
	 */
	Employee getEmployeeWithPendingSchedulesById(long employeeId);

	/**
	 * When the employee accepts the assigned schedule the status of the schedule
	 * and candidate is changed to pending. Then the update list of schedule is
	 * updated in the employee and returned.
	 * 
	 * @param employeeId - Id of the employee whose schedule to be updated.
	 * @param scheduleId - Id of the schedule whose status to be updated.
	 * @param candidateId - Id of the candidate whose status to be updated.
	 * @return employee - Employee along with updated schedules. 
	 */
	Employee acceptSchedule(long candidateId, long employeeId, long scheduleId);
 
	/**
	 * Employees working in the particular technology who are interested to take 
	 * interview is fetched as list and passed.
	 *  
	 * @param technology - Employees corresponding to this technology is to be fetched.
	 * @return employees - List of employees working in the particular technology.
	 */
	List<Employee> getEmployeesByTechnology(Technology technology);
}
