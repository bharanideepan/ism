package com.ideas2it.ism.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.InterviewType;
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.dao.ScheduleRepository;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.info.ScheduleInfo;
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
	public Schedule addSchedule(ScheduleInfo scheduleInfo, long candidateId, String interviewerId, Date date) {
		Candidate candidate = candidateService.fetchCandidateById(candidateId);
		candidate.setStatus(Result.Pending);
		scheduleInfo.setCandidate(candidate);
    	scheduleInfo.setDateTime(date);
    	if((null != interviewerId) && (!interviewerId.isEmpty())) {
			scheduleInfo.setInterviewer(employeeService.getEmployeeById(Long.parseLong(interviewerId)));
			mailSender.sendMail("manibharathi@ideas2it.com", "Testing", "Success");
    	}
		return scheduleRepository.save(this.getScheduleByScheduleInfo(scheduleInfo));
	}

    /**
     * {@inheritDoc}
     */	
	public List<ScheduleInfo> fetchScheduleInfosByCandidateId(long candidateId) {
		return this.getScheduleInfosBySchedules(scheduleRepository.getSchedulesByCandidateId(candidateId));
	}

    /**
     * {@inheritDoc}
     */	
	public List<ScheduleInfo> getAllScheduleInfos() {
		return this.getScheduleInfosBySchedules(scheduleRepository.findAll());
	}

    /**
     * {@inheritDoc}
     */	
	public Schedule getScheduleById(long id) {
		return scheduleRepository.getOne(id);
	}

    /**
     * {@inheritDoc}
     */	
	public ScheduleInfo getScheduleInfoById(long id) {
		return this.getScheduleInfoBySchedule(scheduleRepository.getOne(id));
	}

    /**
     * {@inheritDoc}
     */	
	public List<ScheduleInfo> getEmployeeNewScheduleInfosById(long employeeId) {
		return this.getScheduleInfosBySchedules(scheduleRepository.fetchEmployeeNewSchedulesById(employeeId));
	}

    /**
     * {@inheritDoc}
     */	
	public List<ScheduleInfo> getEmployeePendingScheduleInfosById(long employeeId) {
		return this.getScheduleInfosBySchedules(scheduleRepository.fetchEmployeePendingSchedulesById(employeeId));
	}

    /**
     * {@inheritDoc}
     */	
	public Schedule updateScheduleStatus(long scheduleId, ScheduleStatus status) {
		Schedule schedule = this.getScheduleById(scheduleId);
		schedule.setStatus(status);
		if(status.equals(ScheduleStatus.Declined)) {
			schedule.setInterviewer(null);
		}
		return scheduleRepository.save(schedule);
	}

    /**
     * {@inheritDoc}
     */	
	public void updateResult(String feedBack, long scheduleId, String result) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.setInterviewFeedback(feedBack);
		if (Constant.SELECTED.equals(result)) {
			schedule.setStatus(ScheduleStatus.Selected);
			schedule.getCandidate().setStatus(
					schedule.getInterviewType().equals(InterviewType.Final)
					? Result.Selected
					: Result.Cleared);
			
			/*if(schedule.getInterviewType().equals(InterviewType.Final)) {
				schedule.getCandidate().setStatus(Result.Selected);
			} else {
				schedule.getCandidate().setStatus(Result.Cleared);
			}*/
			
		} else {
			schedule.setStatus(ScheduleStatus.Rejected);
			schedule.getCandidate().setStatus(Result.Rejected);	
		}
		scheduleRepository.save(schedule);
	}

    /**
     * {@inheritDoc}
     */
	public boolean cancelSchedule(long scheduleId, String comment) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.setCancellationComment(comment);
		schedule.setStatus(ScheduleStatus.Cancelled);
		schedule = scheduleRepository.save(schedule);
		return schedule.getStatus().equals(ScheduleStatus.Cancelled);
	}

    /**
     * {@inheritDoc}
     */
	public Schedule reschedule(ScheduleInfo scheduleInfo, String comment,
			long scheduleId, Date date, String interviewerId) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.setRescheduleComment(comment);
		schedule.setStatus(ScheduleStatus.Rescheduled);
		scheduleRepository.save(schedule);
		scheduleInfo.setDateTime(date);
		scheduleInfo.setCandidate(schedule.getCandidate());
    	if((null != interviewerId) && (!interviewerId.isEmpty())) {
    		scheduleInfo.setInterviewer(employeeService.getEmployeeById(Long.parseLong(interviewerId)));
			mailSender.sendMail("manibharathi@ideas2it.com", "Testing", comment);
    	}
		return scheduleRepository.save(this.getScheduleByScheduleInfo(scheduleInfo));
	}

    /**
     * {@inheritDoc}
     */	
	public List<ScheduleInfo> getScheduleInfosByStatus(ScheduleStatus status) {
		return this.getScheduleInfosBySchedules(scheduleRepository.getSchedulesByStatus(status));
	}

    /**
     * {@inheritDoc}
     */	
	public Map<String, Object> getScheduleInfoAndInterviewersByTechnology(long scheduleId) {
		Map<String, Object> scheduleInfoAndInterviewers = new HashMap<String, Object>();
		ScheduleInfo scheduleInfo = this.getScheduleInfoById(scheduleId);
		scheduleInfoAndInterviewers.put(Constant.SCHEDULE, scheduleInfo);
		scheduleInfoAndInterviewers.put(Constant.INTERVIEWERS,
				employeeService.getEmployeesByTechnology(scheduleInfo.getCandidate().getTechnology()));
		return scheduleInfoAndInterviewers;
	}

    /**
     * {@inheritDoc}
     */	
	public Schedule assignSchedule(long scheduleId, long employeeId) {
		Schedule schedule = this.getScheduleById(scheduleId);
		schedule.setInterviewer(employeeService.getEmployeeById(employeeId));
		schedule.setStatus(ScheduleStatus.New);
		mailSender.sendMail("manibharathi@ideas2it.com", "Testing", "Success");
		return scheduleRepository.save(schedule);
	}

    /**
     * {@inheritDoc}
     */	
	public Map<String, Object> getCandidateAndInterviewersByTechnology(long candidateId) {
		Map<String, Object> candidateAndInterviewers = new HashMap<String, Object>();
		Candidate candidate = candidateService.fetchCandidateById(candidateId);
		candidateAndInterviewers.put(Constant.CANDIDATE, candidate);
		candidateAndInterviewers.put(Constant.INTERVIEWERS,
				employeeService.getEmployeesByTechnology(candidate.getTechnology()));
		return candidateAndInterviewers;
	}

    /**
     * {@inheritDoc}
     */	
	public List<ScheduleInfo> getScheduleInfosByManager(long managerId) {
		return this.getScheduleInfosBySchedules(scheduleRepository.fetchSchedulesByTechnology(
				employeeService.getEmployeeById(managerId).getTechnology()));
	}

    /**
     * {@inheritDoc}
     */	
	private List<ScheduleInfo> getScheduleInfosBySchedules(List<Schedule> schedules) {
		List<ScheduleInfo> scheduleInfos = new ArrayList<ScheduleInfo>();
		for(Schedule schedule : schedules) {
			scheduleInfos.add(new ScheduleInfo(schedule.getId(), schedule.getInterviewType(),
				schedule.getDateTime(), schedule.getInterviewFeedback(),
				schedule.getCancellationComment(), schedule.getRescheduleComment(),
				schedule.getStatus(), schedule.getCandidate(),
				schedule.getInterviewer(), schedule.getRound(),
				schedule.getScheduleRejectionTracks()));
		}
		return scheduleInfos;
	}

    /**
     * {@inheritDoc}
     */	
	private ScheduleInfo getScheduleInfoBySchedule(Schedule schedule) {
		return new ScheduleInfo(schedule.getId(), schedule.getInterviewType(),
				schedule.getDateTime(), schedule.getInterviewFeedback(),
				schedule.getCancellationComment(), schedule.getRescheduleComment(),
				schedule.getStatus(), schedule.getCandidate(),
				schedule.getInterviewer(), schedule.getRound(),
				schedule.getScheduleRejectionTracks());
	}

    /**
     * {@inheritDoc}
     */	
	private Schedule getScheduleByScheduleInfo(ScheduleInfo scheduleInfo) {
		return new Schedule(
				scheduleInfo.getId(), scheduleInfo.getInterviewType(),
				scheduleInfo.getDateTime(), scheduleInfo.getInterviewFeedback(),
				scheduleInfo.getCancellationComment(), scheduleInfo.getRescheduleComment(),
				scheduleInfo.getStatus(), scheduleInfo.getCandidate(),
				scheduleInfo.getInterviewer(), scheduleInfo.getRound(),
				scheduleInfo.getScheduleRejectionTracks());
	}

    /**
     * {@inheritDoc}
     */	
	public List<ScheduleInfo> getScheduleInfosByDate(String date) {
		return this.getScheduleInfosBySchedules(scheduleRepository.getSchedulesByDate(date));
	}

	@Override
	public Schedule updateSchedule(ScheduleInfo scheduleInfo, long scheduleId,
			Date date, String interviewerId) {
		Schedule schedule = this.getScheduleById(scheduleId);
		schedule.setDateTime(date);
		schedule.setInterviewType(scheduleInfo.getInterviewType());
		schedule.setRound(scheduleInfo.getRound());
		return scheduleRepository.save(schedule);
	}

	@Override
	public Map<String, Object> getSchedulesAndCounts(long managerId) {
		Map<String, Object> schedulesAndCounts = new HashMap<String, Object>();
		schedulesAndCounts.put(Constant.SCHEDULES,
				this.getScheduleInfosByManager(managerId));
		schedulesAndCounts.put(Constant.NO_OF_NEW,
				this.getEmployeeNewScheduleInfosById(managerId).size());
		schedulesAndCounts.put(Constant.NO_OF_PENDING,
				this.getEmployeePendingScheduleInfosById(managerId).size());
		schedulesAndCounts.put(Constant.NO_OF_DECLINED,
				this.getDeclinedScheduleInfosByManagerId(managerId).size());
		return schedulesAndCounts;
	}

	@Override
	public List<ScheduleInfo> getDeclinedScheduleInfosByManagerId(long managerId) {
		return this.getScheduleInfosBySchedules(scheduleRepository
				.fetchDeclinedSchedulesByTechnology(employeeService
						.getEmployeeById(managerId).getTechnology()));
	}

}