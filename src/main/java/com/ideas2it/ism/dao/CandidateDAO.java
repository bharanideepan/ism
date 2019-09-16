package com.ideas2it.ism.dao;

import java.util.List;

import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.exception.IsmException;


/**
 * This layer act as an intermediate between DB and 
 * our project. This layer interacts with the Candidate 
 * table. It enable us to perform all required actions 
 * to be done in DB.
 * 
 * @author M.Mani Bharathi
 *
 */
public interface CandidateDAO {
	
	/**
	 * Retrieves a limit of candidate objects from DB from the starting id.
	 * @param startId - For pagenation to get each page candidates.
	 * @return candidates - List of candidates within the page. 
	 * @throws IsmException - Hibernate exceptions are catched and re thrown 
	 * as IsmException custom exception.
	 */
	public List<Candidate> fetchCandidatesByLimit(int startId) throws IsmException;

	/**
	 * Retrieves a limit of candidate objects from DB from the starting id for the corresponding Id.
	 * 
	 * @param startId - For pagenation to get each page candidates.
	 * @param candidateResult - Status for which the candidate to be fetched.
	 * @return candidates - List of candidates within the page. 
	 * @throws IsmException - Hibernate exceptions are catched and re thrown 
	 * as IsmException custom exception.
	 */
	public List<Candidate> fetchCandidatesByStatus(int pageNo, Result candidateResult) throws IsmException;

	/**
	 * get Total count of records for the particular status within DB.
	 * 
	 * @param status - Candidates count corresponding status is required.
	 * @return totalCount - Count of candidates present.
	 */
	public int getTotalCount(Result status) throws IsmException;
}
