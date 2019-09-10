package com.ideas2it.ism.info;

import java.util.List;

import com.ideas2it.ism.common.Department;
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.entity.Candidate;

public class CandidateFormInfo {
	private List<Department> departments;
	private List<Result> results;
	private List<Technology> technologies;
	private Candidate candidate;
	
	// Getters and Setters
	public List<Department> getDepartments() {
		return this.departments;
	}
	
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}
