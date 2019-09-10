package com.ideas2it.ism.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.InterviewType;
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.dao.ScheduleRepository;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.service.CandidateService;
import com.ideas2it.ism.service.EmployeeService;
import com.ideas2it.ism.service.ScheduleService;
import com.ideas2it.ism.util.EmailSender;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmailSender mailSender;

    /**
     * {@inheritDoc}
     */	
	public Schedule addSchedule(Schedule schedule, long candidateId, String date, String time) {
		schedule.setCandidate(candidateService.fetchCandidateById(candidateId));
    	schedule.setDate(Date.valueOf(date));
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

	public List<Schedule> getEmployeeNewSchedulesById(long employeeId) {
		return scheduleRepository.fetchEmployeeNewSchedulesById(employeeId);
	}
	
	@Override
	public List<Schedule> getEmployeePendingSchedulesById(long employeeId) {
		return scheduleRepository.fetchEmployeePendingSchedulesById(employeeId);
	}

	@Override
	public Schedule updateScheduleStatus(long scheduleId, ScheduleStatus status) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.setStatus(status);
		return scheduleRepository.save(schedule);
	}

	@Override
	public void updateResult(String feedBack, long scheduleId, String result) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		if (result.equals(Constant.SELECTED)) {
			schedule.setStatus(ScheduleStatus.Selected);
			if(schedule.getInterviewType().equals(InterviewType.Final)) {
				candidateService.updateCandidateStatus(schedule.getCandidate().getId(), Result.Selected);
			} else {
				candidateService.updateCandidateStatus(schedule.getCandidate().getId(), Result.Cleared);
			}
		} else {
			schedule.setStatus(ScheduleStatus.Rejected);	
			candidateService.updateCandidateStatus(schedule.getCandidate().getId(), Result.Rejected);
		}
		schedule.setInterviewFeedback(feedBack);
		scheduleRepository.save(schedule);
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
		Map<String, Object> scheduleAndInterviewers = new HashMap<String, Object>();
		Schedule schedule = this.getScheduleById(scheduleId);
		scheduleAndInterviewers.put(Constant.SCHEDULE, schedule);
		scheduleAndInterviewers.put(Constant.INTERVIEWERS,
				employeeService.getEmployeesByTechnology(schedule.getCandidate().getTechnology()));
		return scheduleAndInterviewers;
	}

	@Override
	public Schedule assignSchedule(long scheduleId, long employeeId) {
		Schedule schedule = this.getScheduleById(scheduleId);
		schedule.setInterviewer(employeeService.getEmployeeById(employeeId));
		mailSender.sendMail("manibharathi@ideas2it.com", "Testing", "Success");
		return scheduleRepository.save(schedule);
	}

	@Override
	public Candidate getcandidateById(long candidateId) {
		return candidateService.fetchCandidateById(candidateId);
	}

}