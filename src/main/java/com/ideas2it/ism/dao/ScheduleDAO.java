package com.ideas2it.ism.dao;

import java.util.List;

import com.ideas2it.ism.common.Department;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.exception.IsmException;

/**
 * This layer act as an intermediate between DB and 
 * our project. This layer interacts with the Schedule
 * table. It enable us to perform all required actions 
 * to be done in DB.
 * 
 * @author M.Mani Bharathi
 *
 */
public interface ScheduleDAO {

	/**
	 * Retrieves a limit of schedule objects from DB from the starting id.
	 * 
	 * @param startId - For pagenation to get each page schedules.
	 * @return schedules - List of schedules within the page. 
	 * @throws IsmException - Hibernate exceptions are catched and re thrown 
	 * as IsmException custom exception.
	 */
	public List<Schedule> fetchSchedulesByLimit(int startId) throws IsmException;

	/**
	 * Get limited amount of schedules assigned for the particular date.
	 *
	 * @param pageNo - To retrieve the corresponding page schedules.
	 * @param date - Schedules assigned for this date is retrieved.
	 * @return schedules - List of schedules scheduled for the date.
	 */
	public List<Schedule> getSchedulesByDate(int pageNo, String date);

	/**
	 * Count the number of schedules scheduled in  the given date.
	 *
	 * @param date - Schedules scheduled in the particular date is fetched.
	 * @return  size - Count of schedules scheduled for the date.
	 */
	public int totalCountForDate(String date);

	/**
	 * Get limited amount of schedules assigned for the particular manager
	 * based on his technology.
	 * 
	 * @param technology - Schedules based on this technology is retrieved.
	 * @param pageNo - To retrieve the corresponding page schedules.
	 * @return schedules - List of schedules scheduled for the date.
	 */
	public List<Schedule> fetchManagerSchedulesByLimit(Technology technology, int pageNo);

	/**
	 * Count the number of schedules scheduled for the given technology.
	 * 
	 * @param technology - Schedules scheduled for the particular technology is fetched.
	 * @return size - Count of schedules scheduled for the technology.
	 */
	public int totalCountFoTechnology(Technology technology);

	/**
	 * Get limited amount of schedules assigned for the particular manager
	 * based on his technology and the adte entered.
	 * 
	 * @param technology - Schedules scheduled for the particular technology is fetched.
	 * @param pageNo - To retrieve the corresponding page schedules.
	 * @param date - Schedules scheduled in the particular date is fetched.
	 * @return schedules - List of schedules scheduled for the date.
	 */
	public List<Schedule> getMangerSchedulesByDate(Technology technology, int pageNo, String date);
}
