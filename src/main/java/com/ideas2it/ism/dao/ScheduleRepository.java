package com.ideas2it.ism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.entity.Schedule;

/**
 * This layer act as an intermediate between DB and 
 * our project. This layer interacts with the Candidate 
 * table. It enable us to perform all required actions 
 * to be done in DB.
 * 
 * @author Bharani Deepan K
 *
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
	/**
	 * Fetch List of schedules assigned for a particular candidate. Each schedule consist
	 * of the candidateId. With the help of the candidate Id.
	 *  
	 * @param candidateId
	 * 
	 * @return schedules - List of schedules of the candidate
	 */
	@Query("SELECT s FROM Schedule s WHERE candidate_id = :candidateId")
	List<Schedule> getSchedulesByCandidateId(long candidateId);
	
	/**
	 * Fetch List of schedules having the given status
	 * 
	 * @param status - Status of the schedule to view
	 * 
	 * @return schedules - List of schedules having the given status
	 */
	@Query("SELECT s FROM Schedule s WHERE status = :status")
	List<Schedule> getSchedulesByStatus(@Param("status")ScheduleStatus status);
}