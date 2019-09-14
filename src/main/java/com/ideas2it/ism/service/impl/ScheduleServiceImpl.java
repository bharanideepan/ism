package com.ideas2it.ism.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.InterviewType;
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.dao.ScheduleDAO;
import com.ideas2it.ism.dao.ScheduleRepository;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.Schedule;
import com.ideas2it.ism.info.CandidatePagenationInfo;
import com.ideas2it.ism.info.ScheduleInfo;
import com.ideas2it.ism.info.SchedulePagenationInfo;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.service.CandidateService;
import com.ideas2it.ism.service.EmployeeService;
import com.ideas2it.ism.service.ScheduleService;
import com.ideas2it.ism.util.CalculatePage;
import com.ideas2it.ism.util.EmailSender;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private ScheduleDAO scheduleDAO;
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
     * @throws IsmException 
     */	
	public SchedulePagenationInfo getAllScheduleInfos() throws IsmException {
		SchedulePagenationInfo pagenationInfo = new SchedulePagenationInfo();
		int count = (int) scheduleRepository.count();
		pagenationInfo.setScheduleInfos(this.getScheduleInfosBySchedules(scheduleDAO.fetchSchedulesByLimit(0)));
		if (0 != count) {
		    List<Integer> pages = CalculatePage.calculatePages(count, Constant.RETRIEVE_LIMIT); 
		    pagenationInfo.setPages(pages);
		    int lastPage = pages.get(pages.size() - 1); 
		    pagenationInfo.setLastPageNo(lastPage);
		    pagenationInfo.setTotalCount(count);
		}
		return pagenationInfo;
	}
	
	@Override
	public JSONArray retrieveAllSchedules(int pageNo, String date) throws IsmException {
		List<ScheduleInfo> scheduleInfos;
		if ((null != date) && !(date.isEmpty())) {
	        pageNo = pageNo - 1;
	        pageNo = pageNo * Constant.RETRIEVE_LIMIT; 
            scheduleInfos  = this.getScheduleInfosBySchedules(scheduleDAO.getSchedulesByDate(pageNo, date));
		} else {
	        pageNo = pageNo - 1;
	        pageNo = pageNo * Constant.RETRIEVE_LIMIT; 
			scheduleInfos = this.getScheduleInfosBySchedules(scheduleDAO.fetchSchedulesByLimit(pageNo));
			for (ScheduleInfo scheduleInfo : scheduleInfos) {
				System.out.println(scheduleInfo.getCandidate().getId());
			}
		}
        JSONArray schedules = new JSONArray();
        for (ScheduleInfo scheduleInfo : scheduleInfos) { 
            JSONObject schedule = new JSONObject();
            schedule.put(Constant.SCHEDULE_ID, scheduleInfo.getId());
            schedule.put(Constant.CANDIDATE_ID, scheduleInfo.getCandidate().getId());
            schedule.put(Constant.CANDIDATE_NAME, scheduleInfo.getCandidate().getName());
            schedule.put(Constant.ROUND, scheduleInfo.getRound());
            schedule.put(Constant.INTERVIEW_TYPE, scheduleInfo.getInterviewType());
            schedule.put(Constant.DATE, scheduleInfo.getDate());
            schedule.put(Constant.TIME, scheduleInfo.getTime());
            schedule.put(Constant.STATUS, scheduleInfo.getStatus());
            if(null != scheduleInfo.getInterviewer()) {
                schedule.put(Constant.INTERVIEWER_NAME, scheduleInfo.getInterviewer().getName());
            } else {
            	schedule.put(Constant.INTERVIEWER_NAME, "null");
            }
            schedules.put(schedule);
        }
        return schedules;
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
	public SchedulePagenationInfo getScheduleInfosByDate(String date) {
		SchedulePagenationInfo pagenationInfo = new SchedulePagenationInfo();
    	System.out.println("service "+ date);
		int count = (int) scheduleDAO.totalCountForDate(date);
		pagenationInfo.setScheduleInfos(
				this.getScheduleInfosBySchedules(scheduleDAO.getSchedulesByDate(0, date)));
		if (0 != count) {
		    List<Integer> pages = CalculatePage.calculatePages(count, Constant.RETRIEVE_LIMIT); 
		    pagenationInfo.setPages(pages);
		    int lastPage = pages.get(pages.size() - 1); 
		    pagenationInfo.setLastPageNo(lastPage);
		    pagenationInfo.setTotalCount(count);
		}
		pagenationInfo.setSearchedDate(date);
		return pagenationInfo;
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

}