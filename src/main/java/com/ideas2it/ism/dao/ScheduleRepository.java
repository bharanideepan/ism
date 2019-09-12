package com.ideas2it.ism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.ism.common.Department;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.entity.Schedule;

/**
 * This layer act as an intermediate between DB and 
 * our project. This layer interacts with the Candidate 
 * table. It enable us to perform all required actions 
 * to be done in DB.
 * 
 * @author Bharani Deepan K
 *
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
	/**
	 * Fetch List of schedules assigned for a particular candidate. Each schedule consist
	 * of the candidateId. With the help of the candidate Id list of schedule is fetched. 
	 * 
	 * @param candidateId - Id of the candidate whose schedule track is required.
	 * @return schedules - List of schedules conducted for the particular candidate.
	 * If there is no schedule conducted empty list is passed.
	 */
	@Query("SELECT s FROM Schedule s WHERE candidate_id = :candidateId")
	List<Schedule> getSchedulesByCandidateId(long candidateId);

	/**
	 * Fetch List of schedules assigned for a particular employee. Each schedule consist
	 * of the employeeId. With the help of the employee id list of schedule is fetched. 
	 * 
	 * @param employeeId - Id of the employee whose newly assigned schedule to be fetched.
	 * @return schedules - List of newly assigned schedules for the particular employee.
	 * If there is no schedule is assigned empty list is passed.
	 */
	@Query("SELECT s FROM Schedule s WHERE employee_id = :employeeId and status = 'New'")
	List<Schedule> fetchEmployeeNewSchedulesById(long employeeId);
	
	/**
	 * Fetch List of pending schedules for a particular employee. Each schedule consist
	 * of the employeeId. With the help of the employee id list of schedule is fetched. 
	 * 
	 * @param employeeId - Id of the employee whose newly assigned schedule to be fetched.
	 * @return schedules - List of pending schedules for the particular employee.
	 * If there is no schedule is assigned empty list is passed.
	 */
	@Query("SELECT s FROM Schedule s WHERE employee_id = :employeeId and status = 'Pending'")
	List<Schedule> fetchEmployeePendingSchedulesById(long employeeId);

	
	/**
	 * Fetch List of schedules having the given status
	 * 
	 * @param status - Status of the schedule to view
	 * 
	 * @return schedules - List of schedules having the given status
	 */
	@Query("SELECT s FROM Schedule s WHERE status = :status")
	List<Schedule> getSchedulesByStatus(@Param("status")ScheduleStatus status);

	/**
	 * Fetch List of schedules having the given department
	 * 
	 * @param department - Schedules assigned for the this department should be fetched.
	 * 
	 * @return schedules - List of schedules having the given department.
	 */
	@Query("SELECT s FROM Schedule s WHERE department = :department")
	List<Schedule> fetchSchedulesByDepartment(@Param("department")Department department);
}