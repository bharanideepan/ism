package com.ideas2it.ism.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.common.InterviewLevel;
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
@Entity
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "interview_level")
    private InterviewLevel interviewLevel;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "interview_type")
    private InterviewType interviewType;
    
    @Column(name = "date")
    private Date date;
    
    @Column(name = "time")
    private Date time;
    
    @Column(name = "interview_feedback")
    private String interviewFeedback;
    
    @Column(name = "cancellation_comment")
    private String cancellationComment;
    
    @Column(name = "reshedule_comment")
    private String rescheduleComment;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ScheduleStatus status;
    
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee interviewer;
    
    public Schedule() {
    	this.status = ScheduleStatus.New;
    }
    
    @Override
    public String toString() {
    	return this.interviewType.toString();
    }
    
    // Getters and Setter 
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public InterviewLevel getInterviewLevel() {
        return this.interviewLevel;
    }
    
    public void setInterviewLevel(InterviewLevel interviewLevel) {
        this.interviewLevel = interviewLevel;
    }
    
    public InterviewType getInterviewType() {
        return this.interviewType;
    }
    
    public void setInterviewType(InterviewType interviewType) {
        this.interviewType = interviewType;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getTime() {
        return this.time;
    }
    
    public void setTime(Date time) {
        this.time = time;
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
}