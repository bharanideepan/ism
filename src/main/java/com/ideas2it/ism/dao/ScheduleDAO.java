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

	public List<Schedule> fetchSchedulesByLimit(int startId) throws IsmException;

	public List<Schedule> getSchedulesByDate(int i, String date);

	public int totalCountForDate(String date);

	public List<Schedule> fetchManagerSchedulesByLimit(Technology technology, int pageNo);

	public int totalCountFoTechnology(Technology technology);

	public List<Schedule> getMangerSchedulesByDate(Technology technology, int pageNo, String date);
}
