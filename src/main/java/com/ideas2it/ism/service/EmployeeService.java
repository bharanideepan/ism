package com.ideas2it.ism.service;

import java.util.List;

import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.info.ScheduleInfo;

public interface EmployeeService {
	
	/**
	 * Newly assigned interview schedules for the particular employee
	 * are fetched from the DB. Add the list of schedule is set into the employee.
	 * 
	 * @param employeeId  -Id of the employee whose schedules to be fetched.
	 * 
	 * @return scheduleInfos - Employee along with newly assigned schedules. 
	 */
	List<ScheduleInfo> getEmployeeNewScheduleInfosById(long employeeId);

	/**
	 * Pending schedules for the particular employee are fetched from the DB. 
	 * Add the list of schedule is set into the employee.
	 * 
	 * @param employeeId  -Id of the employee whose schedules to be fetched.
	 * 
	 * @return scheduleInfos -  
	 */
	List<ScheduleInfo> getEmployeePendingScheduleInfosById(long employeeId);

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
	List<ScheduleInfo> acceptAndGetNewScheduleInfos(long candidateId, long employeeId, long scheduleId);

	/**
	 * When the employee declained the assigned schedule the status of the schedule
	 * and candidate is changed. Then the update list of schedule is
	 * updated in the employee and returned.
	 * 
	 * @param employeeId - Id of the employee whose schedule to be updated.
	 * @param scheduleId - Id of the schedule whose status to be updated.
	 * @param candidateId - Id of the candidate whose status to be updated.
	 * @param comment - Reason entered by the employee while declining schedule.
	 * @return employee - Employee along with updated schedules. 
	 */
	List<ScheduleInfo> rejectAndGetNewScheduleInfos(long candidateId, long employeeId, long scheduleId, String comment);
 
	/**
	 * Employees working in the particular technology who are interested to take 
	 * interview is fetched as list and passed.
	 *  
	 * @param technology - Employees corresponding to this technology is to be fetched.
	 * @return employees - List of employees working in the particular technology.
	 */
	List<Employee> getEmployeesByTechnology(Technology technology);
	 
	/**
	 * Gets employee by id
	 *  
	 * @param employeeId - To fetch the employee by id
	 * @return employee - Employee having the ID
	 */
	Employee getEmployeeById(long employeeId);

    /**
     * Get list of employees to show while creating a new user. Employees
     * who does not have an user account are fetched.
     * 
     * @return employees - List of employees who dont have an user account.
     */
	List<Employee> getNewEmployees();
}
