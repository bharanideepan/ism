package com.ideas2it.ism.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

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
@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

}
