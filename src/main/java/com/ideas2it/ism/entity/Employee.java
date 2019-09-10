package com.ideas2it.ism.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ideas2it.ism.common.Department;
import com.ideas2it.ism.common.InterviewType;

@Entity
@Table(name="employee")
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "is_interviewer")
	private boolean isInterviewer;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "interview_type")
	private InterviewType interviewType;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "department")
	private Department department;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsInterviewer() {
		return isInterviewer;
	}
	public void setIsInterviewer(boolean isInterviewer) {
		this.isInterviewer = isInterviewer;
	}
	public InterviewType getInterviewType() {
		return interviewType;
	}
	public void setInterviewType(InterviewType interviewType) {
		this.interviewType = interviewType;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}