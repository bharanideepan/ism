package com.ideas2it.ism.service;

import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;

public interface ScheduleRejectionTrackService {
	
	
	/**
	 * Creates a schedule rejection track when an employee declined his schedule
	 * @param employee  - Who declined the schedule
	 * @param schedule  - Which is declined by the employee
	 * @param comment  - Comment entered by the employee while declining schedule
	 * @return scheduleRejectionTrack - Track created while declining the schedule
	 */
	ScheduleRejectionTrack createScheduleRejectionTrack(Employee employee, Schedule schedule, String comment);

}
