package com.ideas2it.ism.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ideas2it.ism.common.Department;
import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.dao.CandidateDAO;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.info.CandidateFormInfo;
import com.ideas2it.ism.info.CandidatePagenationInfo;
import com.ideas2it.ism.service.CandidateService;
import com.ideas2it.ism.service.ScheduleService;



/**
 * All the request passed from jsp are received at controller,
 * the action between controller and DAO layer is controlled here
 * any modifications , logics required are performed here.
 * 
 * @author M.Mani Bharathi.
 *
 */
@Service
public class CandidateServiceImpl implements CandidateService {
	
    @Autowired
    private CandidateDAO candidateDAO;
    @Autowired
    private ScheduleService scheduleService;
    
    private final String UPLOAD_DIRECTORY = "/home/ubuntu/resume/";
    private final String PROFILE_PIC_PATH = "http://localhost:8080/resume/";
    
	public CandidateFormInfo getCandidateFormInfo() {
		CandidateFormInfo candidateFormInfo = new CandidateFormInfo();
		List<Department> departments = new ArrayList<Department>(Arrays.asList(Department.values()));
		List<Technology> technologies = new ArrayList<Technology>(Arrays.asList(Technology.values()));
		Candidate candidate = new Candidate();
		candidateFormInfo.setCandidate(candidate);
		candidateFormInfo.setDepartments(departments);
		candidateFormInfo.setTechnologies(technologies);
		return candidateFormInfo;
	}
   
	public Candidate saveCandidate(Candidate candidate, MultipartFile resume) 
			throws IOException {
		System.out.println(candidate);
		candidate = saveCandidateResume(candidate, resume);
		return candidateDAO.save(candidate);
	}
	
    public Candidate fetchCandidateById(long candidateId) {   
    	return candidateDAO.getOne(candidateId);
    }
    
    public Candidate getCandidateProgress(long candidateId) {
    	Candidate candidate = candidateDAO.getOne(candidateId);
    	candidate.setSchedules(scheduleService.fetchSchedulesByCandidateId(candidateId));
		return candidate;   	
    }
    
	public CandidatePagenationInfo getPagenationInfo() {
		CandidatePagenationInfo pagenationInfo = new CandidatePagenationInfo();
		int count = totalCount();
		pagenationInfo.setCandidates(fetchCandidates());
		pagenationInfo.setTotalCount(count);
		return pagenationInfo;
	}
	
	public CandidatePagenationInfo searchByName(String name) {
		CandidatePagenationInfo pagenationInfo = new CandidatePagenationInfo();
		int count = this.totalCount();
		pagenationInfo.setCandidates(candidateDAO.findCandidateByName(name));
		pagenationInfo.setTotalCount(count);
		return pagenationInfo;	
	}
	
    /**
     * Resume uploaded by the recruiter is passed as multipart file then it is converted as
     * bytes and stored in the local directory and the path is stored in DB. To save the
     * name unique for each candidate the emailId is concatenated with resume name.
     * 
     * @param candidate - Object in which the path to be saved.
	 * @param resume - Resume uploaded by the recruiter is saved to the local 
	 * directory and the path is saved in the DB. 
     * @return candidate - Path updated object to be stored.
     * @throws IOException
     */
    private Candidate saveCandidateResume(Candidate candidate, MultipartFile resume)
            throws IOException {   
        String filename = resume.getOriginalFilename();      
        byte[] bytes = resume.getBytes();  
        BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
                new File(UPLOAD_DIRECTORY + File.separator + candidate.getEmailId() + filename)));  
        stream.write(bytes);  
        stream.flush();  
        stream.close();  
        if (!(resume.getOriginalFilename().isEmpty())) {
            String path = PROFILE_PIC_PATH + candidate.getEmailId() + resume.getOriginalFilename();
            candidate.setResumeFilePath(path);
        }
        return candidate;
    }
    
    /**
     * List of all candidate object is fetched from DB.
     * 
     * @return candidates - List of entities fetched from DB.
     */
    private List<Candidate> fetchCandidates() {   
    	return candidateDAO.findAll();
    }
    
    /**
     * Total count of the entities present is returned.
     * 
     * @return count - COunt of entities present.
     */
    private int totalCount() {   
    	return (int) candidateDAO.count();
    }
}
