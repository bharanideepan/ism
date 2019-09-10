package com.ideas2it.ism.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.info.CandidateFormInfo;
import com.ideas2it.ism.info.CandidatePagenationInfo;

/**
 * All the request passed from jsp are recieved at controller,
 * the action between controller and DAO layer is controlled here
 * any modifications , logics required are performed here.
 * 
 * @author M.Mani Bharathi
 *
 */
public interface CandidateService {
	
	/**
	 * To show the list of departments and technologies to select for the candidate.
	 * The enum class list is set into the candidateFormInfo object and passed to show the
	 * list dynamically. 
	 *  
	 * @return candidateFormInfo - Object consist of departments and technologies list.
	 */
	CandidateFormInfo getCandidateFormInfo();
	
	/**
	 * Recruiter entered candidate informations are obtained
	 * as an candidate object.Then the object is passed to 
	 * DAO layer to store it in DB.
	 * 
	 * @param candidate - Recruiter entered informations are passed 
	 * as candidate object. 
	 * @param resume - Resume uploaded by the recruiter is saved to the local 
	 * directory and the path is saved in the DB. 
	 * @return candidate - Saved candidate object is fetched from DB
	 * from the generated id.
	 */
	Candidate saveCandidate(Candidate candidate, MultipartFile resume) throws IOException;
	
	/**
	 * For the given id corresponding candidate object is fetch from DB.
	 * 
	 * @param candidateId - Id of the object to fetched. It should not be null.
	 * @return candidate - Object fetched for the corresponding id.
	 */
	Candidate fetchCandidateById(long candidateId);
	
	/**
	 * Informations required for showing details in multiple pages. Informations like total
	 * number of records in DB, then the pages count is called and set into a pagenationInfo
	 * object and passed.
	 * 
	 * @return pagenationInfo - Informations such as list of candidates, list of pages are passed. 
	 */
	CandidatePagenationInfo getPagenationInfo();

	/**
	 * List of candidate details of the given name is fetch from DB to display. 
	 * 
	 * @return pagenationInfo - Informations such as list of candidates, list of pages are passed. 
	 */
	CandidatePagenationInfo searchByName(String name);

	/**
	 * The schedules assigned for the particular candidate is fetched from DB and updated in 
	 * the candidate. 
	 * @param candidateId - Id of the candidate whose schedules to be fetched. 
	 * @return candidate - Candidate object with updated schedule.
	 */
	Candidate getCandidateProgress(long candidateId);
}
