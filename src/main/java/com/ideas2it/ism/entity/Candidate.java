package com.ideas2it.ism.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;      
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.Department;
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.Technology;

/**
 * Informations of the candidate who applied for an interview. Informations such
 * as personal details, contact details, experience and position for which he 
 * applied are maintained. Then the result of the interview is also maintained.
 *
 * @author-M.Mani Bharathi.
 * @date-04/09/19
 */
@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private long phoneNumber;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "position")
    private String position;

    @Column(name = "experience")
    private String experience;

    @Column(name = "resume_file_path")
    private String resumeFilePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(name = "technology")
    private Technology technology;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Result status;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="candidate_id")
	private List<Schedule> schedules;

    public Candidate() {
        this.status = Result.New;
    }
    
    @Override
    public int hashCode() {
        return  (int) this.id;
    }
    
    @Override
    public String toString() {
        String candidateInfo = new StringBuilder().append(Constant.ID)
                .append(this.id).append("    ").append(Constant.NAME)
                .append(this.name).append("    ").toString();
       return candidateInfo;
    }

    /**
     * GETTERS AND SETTERS
     */
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getResumeFilePath() {
        return this.resumeFilePath;
    }

    public void setResumeFilePath(String resumeFilePath) {
        this.resumeFilePath = resumeFilePath;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Technology getTechnology() {
        return this.technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public Result getStatus() {
        return this.status;
    }

    public void setStatus(Result status) {
        this.status = status;
    }
        
    public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
}