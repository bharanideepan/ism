package com.ideas2it.ism.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.Result;
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

	public boolean addSchedule(Schedule schedule, long candidateId, String date, String time) 
	        {
		schedule.setCandidate(candidateService.fetchCandidateById(candidateId));
    	schedule.setDate(Date.valueOf(date));
    	schedule.setTime(Time.valueOf(time));
		schedule = scheduleRepository.save(schedule);
		return schedule.getId() != 0;
	}
	
	public List<Schedule> fetchSchedulesByCandidateId(long candidateId) {
		return scheduleRepository.getSchedulesByCandidateId(candidateId);
	}
	
	public List<Schedule> getAllSchedules() {
		// TODO Auto-generated method stub
		return scheduleRepository.findAll();
	}

	public Schedule getScheduleById(long id) {
		return scheduleRepository.getOne(id);
	}

	@Override
	public List<Schedule> getEmployeeNewSchedulesById(long employeeId) {
		return scheduleRepository.fetchEmployeeNewSchedulesById(employeeId);
	}
	
	@Override
	public List<Schedule> getEmployeePendingSchedulesById(long employeeId) {
		return scheduleRepository.fetchEmployeePendingSchedulesById(employeeId);
	}

	@Override
	public void updateScheduleStatus(long scheduleId, ScheduleStatus status) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.setStatus(status);
		scheduleRepository.save(schedule);
	}

	@Override
	public void updateResult(String feedBack, long scheduleId, String result) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		if (result.equals(Constant.SELECTED)) {
			schedule.setStatus(ScheduleStatus.Selected);
		} else {
			schedule.setStatus(ScheduleStatus.Rejected);	
			candidateService.updateCandidateStatus(schedule.getCandidate().getId(), Result.Rejected);
		}
		schedule.setInterviewFeedback(feedBack);
		scheduleRepository.save(schedule);
	}
}