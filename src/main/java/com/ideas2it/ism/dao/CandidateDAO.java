package com.ideas2it.ism.dao;

import java.util.List;

import com.ideas2it.ism.entity.Candidate;

public interface CandidateDAO {
	
	/**
	 * Retrieves a limit of candidate objects from DB from the starting id.
	 * @param startId - For pagenation to get each page candidates.
	 * @return candidates - List of candidates within the page. 
	 */
	public List<Candidate> fetchCandidatesByLimit(int startId);

}
