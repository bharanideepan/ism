package com.ideas2it.ism.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.dao.ScheduleRepository;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.service.CandidateService;
import com.ideas2it.ism.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private CandidateService candidateService;

    /**
     * {@inheritDoc}
     */	

	public Schedule addSchedule(Schedule schedule, long candidateId, String date, String time) {
		schedule.setCandidate(candidateService.fetchCandidateById(candidateId));
    	schedule.setDate(Date.valueOf(date));
    	schedule.setTime(Time.valueOf(time));
		return scheduleRepository.save(schedule);
	}
	
	public List<Schedule> fetchSchedulesByCandidateId(long candidateId) {
		return scheduleRepository.getSchedulesByCandidateId(candidateId);
	}
	
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}

	public Schedule getScheduleById(long id) {
		return scheduleRepository.getOne(id);
	}

	public boolean cancelSchedule(Schedule scheduleInfo) {
		Schedule schedule = scheduleRepository.getOne(scheduleInfo.getId());
		schedule.setCancellationComment(scheduleInfo.getCancellationComment());
		schedule.setStatus(ScheduleStatus.Cancelled);
		schedule = scheduleRepository.save(schedule);
		return schedule.getStatus().equals(ScheduleStatus.Cancelled);
	}

	public Schedule reschedule(Schedule newSchedule, String comment, long scheduleId, long candidateId, String date, String time) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.setRescheduleComment(comment);
		schedule.setStatus(ScheduleStatus.Rescheduled);
		scheduleRepository.save(schedule);
		return this.addSchedule(newSchedule, candidateId, date, time);
	}

	public List<Schedule> getSchedulesByStatus(ScheduleStatus status) {
		return scheduleRepository.getSchedulesByStatus(status);
	}

	public Map<String, Object> getScheduleAndInterviewersByTechnology(long scheduleId) {
		Map<String, Object>
		return null;
	}

}