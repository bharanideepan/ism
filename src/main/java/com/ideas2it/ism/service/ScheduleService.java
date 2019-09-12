package com.ideas2it.ism.service;

import java.util.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.entity.ScheduleInfo;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.exception.IsmException;

public interface ScheduleService {

	/**
	 * Adds the schedule for a candidate which is generated by the recruiter
	 * 
	 * @param schedule - Schedule created by the recruiter
	 * @param candidateId - Id of the candidate for whom the schedule is created.
	 * @param date - interview date which is in string format
	 * @param time - interview time which is in string format
	 * @param interviewerId - Id of the interviewer Which is in string format
	 * 
	 * @throws IsmException -
	 * @return true when the schedule added successfully and id created for that schedule else false
	 */
	Schedule addSchedule(Schedule schedule, long candidateId, String shdate,
			String time, String interviewerId, Date date);
	
	/**
	 * For the given candidate id the schedules conducted for the particular candidate
	 * is fetch and returned as list.
	 * 
	 * @param candidateId - Id of the candidate whose schedule track is required.
	 * 
	 * @return schedules - List of schedules conducted for the particular candidate.
	 * If there is no schedule conducted empty list is passed.
	 */
	List<Schedule> fetchSchedulesByCandidateId(long candidateId);
	
	/**
	 * For the given employee id the newly assigned schedules for the particular employee
	 * is fetch and returned as list.
	 * 
	 * @param employeeId - Id of the employee whose newly assigned schedule to be fetched.
	 * @return schedules - List of newly assigned schedules for the particular employee.
	 * If there is no schedule is assigned empty list is passed.
	 */
	List<Schedule> getEmployeeNewSchedulesById(long employeeId);

	/**
	 * For the given employee id the pending schedules for the particular employee
	 * is fetch and returned as list.
	 * 
	 * @param employeeId - Id of the employee whose newly assigned schedule to be fetched.
	 * @return schedules - List of pending schedules for the particular employee.
	 * If there is no schedule is assigned empty list is passed.
	 */
	List<Schedule> getEmployeePendingSchedulesById(long employeeId);

	/**
	 * The schedule is fetched from DB and the given status is updated.
	 * 
	 * @param scheduleId - Id of the schedule in which the status to be changed.
	 * @param status - Type of status to be updated.
	 * @return schedule - After updating successfully.
	 */
	Schedule updateScheduleStatus(long scheduleId, ScheduleStatus status);
	
	/**
	 * When the employee updates the result of the schedule id, feedback and result
	 * is obtained and updated in the schedule.
	 * 
	 * @param feedBack - Comments passed for the interview result.
	 * @param scheduleId - Id of the schedule in which the result to be updated.
	 * @param result - Result of the schedule.
	 */
	void updateResult(String feedBack, long scheduleId, String result);
	
	/**
	 * Gets all schedules
	 * 
	 * @return schedules - List of all schedules.
	 */
	List<Schedule> getAllSchedules();
	
	/**
	 * Gets the schedule having the given ID
	 * 
	 * @param id - Schedule ID which should not be null.
	 * 
	 * @return schedule - If the id exists, the schedule will be returned else null will be returned
	 */
	Schedule getScheduleById(long id);
	
	/**
	 * Gets the schedule having the given ID and converts it into schedule info
	 * 
	 * @param id - Schedule ID which should not be null.
	 * 
	 * @return scheduleInfo - If the id exists, the scheduleInfo will be returned else null will be returned
	 */
	ScheduleInfo getScheduleInfoById(long id);
	
	/**
	 * Updates the schedule status as cancelled.
	 * 
	 * @param scheduleInfo - Model schedule from the client along with cancellation comment
	 * @param comment - Comment for cancelling
	 * 
	 * @return true if the status of the schedule is updated as cancelled else false
	 */
	boolean cancelSchedule(Schedule scheduleInfo);
	
	/**
	 * Updates the schedule status as rescheduled and also creates a new schedule.
	 * 
	 * @param newSchedule - Model schedule from the client along with reschedule comment
	 * @param comment - Comment for rescheduling
	 * @param scheduleId - Schedule id used to update the schedule
	 * @param candidateId - Candidate id used to reschedule
	 * @param date - Interview date
	 * @param time - Interview time
	 * @param interviewerId - Id of the interviewer Which is in string format
	 * 
	 * @return schedule - After rescheduling successfully
	 */
	Schedule reschedule(Schedule newSchedule, String comment,
			long scheduleId, long candidateId, String date,
			String time, String interviewerId);
	
	/**
	 * Gets the schedules by status
	 * 
	 * @param status - Status of the schedule to view
	 * 
	 * @return schedules - List of schedule having the given status
	 * If the given status is not available, returns null
	 */
	List<Schedule> getSchedulesByStatus(ScheduleStatus status);
	
	/**
	 * Gets the schedule and available interviewers for that schedule.
	 * 
	 * @param scheduleId - Used to fetch the schedule so that we can fetch the available interviewers.
	 * 
	 * @return scheduleAndInterviewers - Both schedule and the available interviewers for that schedule.
	 */
	Map<String, Object> getScheduleAndInterviewersByTechnology(long scheduleId);
	
	/**
	 * Assigns the interviewer with the schedule
	 * 
	 * @param scheduleId - To fetch schedule by using this id
	 * @param employeeId - To fetch employee by using this id
	 * 
	 * @return schedule - After assigning with the interviewer
	 */
	Schedule assignSchedule(long scheduleId, long employeeId);
	
	/**
	 * Gets candidate by id
	 * 
	 * @param candidateId - id of the candidate to fetch the candidate
	 * 
	 * @return candidate - candidate having the id
	 */
	Candidate getcandidateById(long candidateId);
	
	/**
	 * Gets candidate and available interviewers
	 * 
	 * @param candidateId - id of the candidate to fetch the candidate
	 * 
	 * @return candidateAndInterviewers - After assigning with the interviewer
	 */
	Map<String, Object> getCandidateAndInterviewersByTechnology(long candidateId);

	/**
	 * Schedules of certain manager are retrieved based on his department.
	 * From the id the manager object is fetched and the department is obtained
	 * and corresponding schedules are retrieved. 
	 * @param managerId - Id of the manager whose department schedules to be fetched.
	 * @return schedules - List of schedules for the manager.
	 */
	List<Schedule> getSchedulesByManager(long managerId);
}