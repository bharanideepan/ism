package com.ideas2it.ism.info;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.ScheduleRejectionTrack;
import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.DateTimeUtil;
import com.ideas2it.ism.common.InterviewType;
import com.ideas2it.ism.common.ScheduleStatus;

/**
 * A schedule will be created by a recruiter and will be assigned to an employee
 * either by a manager or a recruiter. One candidate can have many schedules.
 * And whenever a schedule is rejected by an employee, track will be saved as
 * link-@ScheduleRejectionTrack. And also each schedule can have many track
 * link-@ScheduleRejectionTrack. After successful completion of an interview
 * status of the schedule will be updated.
 *
 * @author-Bharani Deepan K
 * @date-4/09/19
 */
public class ScheduleInfo {

    private long id;
    
    private InterviewType interviewType;
    
    private String date;
    
    private String time;
    
    private String interviewFeedback;
    
    private String cancellationComment;
    
    private String rescheduleComment;
    
    private ScheduleStatus status;
    
    private Candidate candidate;

    private Employee interviewer;
    
    private int round;

	private List<ScheduleRejectionTrack> scheduleRejectionTracks;
    
    private Date dateTime;

	public ScheduleInfo() {
    	this.status = ScheduleStatus.New;
    }

	public ScheduleInfo(long id,
			InterviewType interviewType,
			Date dateTime,
			String interviewFeedback,
			String cancellationComment,
			String rescheduleComment,
			ScheduleStatus status,
			Candidate candidate,
			Employee interviewer,
			int round,
			List<ScheduleRejectionTrack> scheduleRejectionTracks) {
    	this.interviewType = interviewType;
    	this.setDateAndTime(dateTime);
    	this.interviewFeedback = interviewFeedback;
    	this.cancellationComment = cancellationComment;
    	this.rescheduleComment = rescheduleComment;
    	this.status = status;
    	this.candidate = candidate;
    	this.interviewer = interviewer;
    	this.round = round;
    	this.scheduleRejectionTracks = scheduleRejectionTracks;
    	this.dateTime = dateTime;
    }
    
    private void setDateAndTime(Date dateTime) {
    	Map<String, String> dateAndTime = DateTimeUtil.getDateAndTimeInStringFormat(dateTime);
    	this.date = dateAndTime.get(Constant.DATE);
    	this.time = dateAndTime.get(Constant.TIME);
	}

	// Getters and Setter 
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public InterviewType getInterviewType() {
        return this.interviewType;
    }
    
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setInterviewType(InterviewType interviewType) {
        this.interviewType = interviewType;
    }
    
    public String getInterviewFeedback() {
        return this.interviewFeedback;
    }
    
    public void setInterviewFeedback(String interviewFeedback) {
        this.interviewFeedback = interviewFeedback;
    }
    
    public String getCancellationComment() {
        return this.cancellationComment;
    }
    
    public void setCancellationComment(String cancellationComment) {
        this.cancellationComment = cancellationComment;
    }
    
    public String getRescheduleComment() {
        return this.rescheduleComment;
    }
    
    public void setRescheduleComment(String rescheduleComment) {
        this.rescheduleComment = rescheduleComment;
    }
    
    public ScheduleStatus getStatus() {
        return this.status;
    }
    
    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }
    
    public Candidate getCandidate() {
        return this.candidate;
    }
    
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
   
    
    public Employee getInterviewer() {
        return this.interviewer;
    }
    
    public void setInterviewer(Employee interviewer) {
        this.interviewer = interviewer;
    }
   
    public int getRound() {
        return this.round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public List<ScheduleRejectionTrack> getScheduleRejectionTracks() {
		return scheduleRejectionTracks;
	}

	public void setScheduleRejectionTracks(List<ScheduleRejectionTrack> scheduleRejectionTracks) {
		this.scheduleRejectionTracks = scheduleRejectionTracks;
	}
    
    public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}