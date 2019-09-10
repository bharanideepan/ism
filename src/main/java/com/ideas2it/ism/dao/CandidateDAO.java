package com.ideas2it.ism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ideas2it.ism.entity.Candidate;

/**
 * This layer act as an intermediate between DB and 
 * our project. This layer interacts with the Candidate 
 * table. It enable us to perform all required actions 
 * to be done in DB.
 * 
 * @author M.Mani Bharathi
 *
 */
public interface CandidateDAO extends JpaRepository<Candidate, Long> {
	
	/**
     * Finds a candidate by using the name as a search criteria.
     * @param lastName
     * @return  A list of persons whose name is an exact match with the given name.
     *          If no persons is found, this method returns an empty list.
     */
    @Query("SELECT c FROM Candidate c WHERE LOWER(c.name) like LOWER(:name)")
    public List<Candidate> findCandidateByName(String name);
}
