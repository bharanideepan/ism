package com.ideas2it.ism.info;

import java.util.List;

import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.entity.Employee;

public class SchedulePagenationInfo {
	private int totalCount;
    private int lastPageNo;
    private int pageNo;
	private String date; 
	private String searchedDate; 
	private Technology technology;
	private List<Employee> interviewers;
	private List<ScheduleInfo> scheduleInfos;
	private List<Integer> pages;

	//Getters and Setters	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getLastPageNo() {
		return lastPageNo;
	}
	
	public void setLastPageNo(int lastPageNo) {
		this.lastPageNo = lastPageNo;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public List<Employee> getInterviewers() {
		return interviewers;
	}

	public void setInterviewers(List<Employee> interviewers) {
		this.interviewers = interviewers;
	}

	public List<ScheduleInfo> getScheduleInfos() {
		return scheduleInfos;
	}

	public void setScheduleInfos(List<ScheduleInfo> scheduleInfos) {
		this.scheduleInfos = scheduleInfos;
	}

    public List<Integer> getPages() {
		return pages;
	}
    
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
 
	public String getSearchedDate() {
		return searchedDate;
	}

	public void setSearchedDate(String searchedDate) {
		this.searchedDate = searchedDate;
	}
}
