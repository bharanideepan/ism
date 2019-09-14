package com.ideas2it.ism.info;

import java.util.List;

import com.ideas2it.ism.common.Result;

public class SchedulePagenationInfo {
	private int totalCount;
    private int lastPageNo;
    private int pageNo;
	private String date; 
	private String searchedDate; 
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
