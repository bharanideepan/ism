package com.ideas2it.ism.entity;

import com.ideas2it.ism.entity.Schedule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ideas2it.ism.entity.Employee;

/**
 * Each schedule rejection track will be created whenever an employee
 * rejects a schedule. And also it has the comment about the rejection
 * which is entered by the employee
 *
 * @author-Bharani Deepan K
 * @date-4/09/19
 */
@Entity
@Table(name="SCHEDULE_REJECTION_TRACK")
public class ScheduleRejectionTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;
    
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
    
    @Column(name = "COMMENT")
    private String comment;
    
    public ScheduleRejectionTrack(Employee employee,
    		Schedule schedule, String comment) {
        this.employee = employee;
        this.schedule = schedule;
        this.comment = comment;
    }
    
    public ScheduleRejectionTrack() {
    }
    
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Schedule getSchedule() {
        return this.schedule;
    }
    
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    public Employee getEmployee() {
        return this.employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
}