package com.ideas2it.ism.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.dao.ScheduleRejectionTrackRepository;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.service.ScheduleRejectionTrackService;

@Service
public class ScheduleRejectionTrackServiceImpl implements ScheduleRejectionTrackService {
	
	@Autowired
	ScheduleRejectionTrackRepository scheduleRejectionTrackRepository;

	@Override
	public ScheduleRejectionTrack createScheduleRejectionTrack(Employee employee, Schedule schedule, String comment) {
		return scheduleRejectionTrackRepository.save(new ScheduleRejectionTrack(employee, schedule, comment));
	}

}
