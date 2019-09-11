package com.ideas2it.ism.info;

import java.util.List;

import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.entity.Candidate;

public class CandidatePagenationInfo {
    private int totalCount;
    private int lastPageNo;
    private int pageNo;
	private List<Candidate> candidates;
	private List<Result> results;
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
	
	public List<Candidate> getCandidates() {
		return candidates;
	}
	
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	
	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

    public List<Integer> getPages() {
		return pages;
	}
    
	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
}
