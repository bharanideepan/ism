package com.ideas2it.ism.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.info.ScheduleInfo;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.exception.IsmException;

public interface ScheduleService {

	/**
	 * Adds the schedule for a candidate which is generated by the recruiter
	 * 
	 * @param scheduleInfo - Schedule created by the recruiter
	 * @param candidateId - Id of the candidate for whom the schedule is created.
	 * @param interviewerId - Id of the interviewer Which is in string format
	 * @param date - Scheduled date 
	 * 
	 * @return true when the schedule added successfully and id created for that schedule else false
	 */
	ScheduleInfo addSchedule(ScheduleInfo scheduleInfo, long candidateId, String interviewerId, Date date);
	
	/**
	 * For the given candidate id the schedules conducted for the particular candidate
	 * is fetch and returned as list.
	 * 
	 * @param candidateId - Id of the candidate whose schedule track is required.
	 * 
	 * @return schedules - List of schedules conducted for the particular candidate.
	 * If there is no schedule conducted empty list is passed.
	 */
	List<ScheduleInfo> fetchScheduleInfosByCandidateId(long candidateId);
	
	/**
	 * For the given employee id the newly assigned schedules for the particular employee
	 * is fetch and returned as list.
	 * 
	 * @param employeeId - Id of the employee whose newly assigned schedule to be fetched.
	 * @return schedules - List of newly assigned schedules for the particular employee.
	 * If there is no schedule is assigned empty list is passed.
	 */
	List<ScheduleInfo> getEmployeeNewScheduleInfosById(long employeeId);

	/**
	 * For the given employee id the pending schedules for the particular employee
	 * is fetch and returned as list.
	 * 
	 * @param employeeId - Id of the employee whose newly assigned schedule to be fetched.
	 * @return schedules - List of pending schedules for the particular employee.
	 * If there is no schedule is assigned empty list is passed.
	 */
	List<ScheduleInfo> getEmployeePendingScheduleInfosById(long employeeId);

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
	List<ScheduleInfo> getAllScheduleInfos();
	
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
	 * @param scheduleId - Id of the schedule to be cancelled
	 * @param comment - Comment for cancelling
	 * 
	 * @return true if the status of the schedule is updated as cancelled else false
	 */
	boolean cancelSchedule(long scheduleId, String comment);
	
	/**
	 * Updates the schedule status as rescheduled and also creates a new schedule.
	 * 
	 * @param newSchedule - Model schedule from the client along with reschedule comment
	 * @param comment - Comment for rescheduling
	 * @param scheduleId - Schedule id used to update the schedule
	 * @param dateTime - Interview date and time
	 * @param interviewerId - Id of the interviewer Which is in string format
	 * 
	 * @return schedule - After rescheduling successfully
	 */
	Schedule reschedule(ScheduleInfo scheduleInfo, String comment,
			long scheduleId, Date date, String interviewerId);
	
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
	Schedule updateSchedule(ScheduleInfo scheduleInfo, long scheduleId,
			Date date, String interviewerId);
	
	/**
	 * Gets the scheduleInfos by status
	 * 
	 * @param status - Status of the schedule to view
	 * 
	 * @return scheduleInfos - List of scheduleInfo having the given status
	 * If the given status is not available, returns null
	 */
	List<ScheduleInfo> getScheduleInfosByStatus(ScheduleStatus status);
	
	/**
	 * Gets the schedule and available interviewers for that schedule.
	 * 
	 * @param scheduleId - Used to fetch the schedule so that we can fetch the available interviewers.
	 * 
	 * @return scheduleAndInterviewers - Both schedule and the available interviewers for that schedule.
	 */
	Map<String, Object> getScheduleInfoAndInterviewersByTechnology(long scheduleId);
	
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
	 *  
	 * @param managerId - Id of the manager whose department schedules to be fetched.
	 * 
	 * @return scheduleInfos - List of schedules for the manager.
	 */
	List<ScheduleInfo> getScheduleInfosByManager(long managerId);

	/**
	 * gets the schedules which are scheduled on the given date
	 *  
	 * @param date - Date which is given by the client.
	 * 
	 * @return scheduleInfos - List of schedules which are scheduled on that day.
	 */
	List<ScheduleInfo> getScheduleInfosByDate(String date);
}