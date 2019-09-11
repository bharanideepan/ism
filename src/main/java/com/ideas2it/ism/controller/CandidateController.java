package com.ideas2it.ism.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ideas2it.ism.common.CandidateStatus;
import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.common.Result;
import com.ideas2it.ism.common.ScheduleStatus;
import com.ideas2it.ism.entity.Candidate;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.info.CandidateFormInfo;
import com.ideas2it.ism.info.CandidatePagenationInfo;
import com.ideas2it.ism.service.CandidateService;


/**
 * Helps to access candidates informations. Allows recruiters to add new candidate
 * informations, view candidates information to assign to a interviewer. His 
 * complete track is maintained, whether he got selected or rejected or if interview
 * is cancelled. It helps to maintain the whole action performed based on candidiate.
 *  
 * @author M.Mani Bharathi.
 *
 */
@Controller
public class CandidateController {
	//private static final Logger logger = Logger.getLogger(CandidateController.class);
	
    @Autowired
	private CandidateService candidateService;
	
    /**
     * When the recruiters needs to add new candidate, this method is called to 
     * show createCandidate page. Where the recruiter can enter the informations 
     * of the candidate and save it.
     * 
     * @param model - Used to send candidate object along with request to jsp.
     * @return CREATE_CANDIDATE_JSP - Page shows the create candidate form to
     * enter the details and to store it.
     */
    @RequestMapping(value = Constant.ADD_CANDIDATE, method = RequestMethod.GET)  
    public String addCandidateForm(Model model) { 
    	//logger.info("logger");
        CandidateFormInfo candidateFormInfo = candidateService.getCandidateFormInfo(); 
        model.addAttribute(Constant.CANDIDATE, candidateFormInfo.getCandidate());  
        model.addAttribute(Constant.CANDIDATE_FORM_INFO, candidateFormInfo);  
        return Constant.CREATE_CANDIDATE_JSP;  
    }  
    
    /**
     * Recruiter entered informations are obtained as an object.
     * Then the object is passed to the DAO layer to store it in DB.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.SAVE_CANDIDATE, method = RequestMethod.POST)  
    private String saveCandidate(@ModelAttribute(Constant.CANDIDATE) Candidate 
            candidate, @RequestParam(name = Constant.RESUME, required = false) 
            MultipartFile resume, Model model) {
        try { 
            candidate = candidateService.saveCandidate(candidate, resume);
            model.addAttribute(Constant.STATUS, Constant.CREATED); 
            model.addAttribute(Constant.CANDIDATE, candidate); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.REDIRECT + Constant.SEARCH_BY_STATUS + "?" + Constant.RESULT + "=New";
    }
    
    /**
     * When the recruiter is willing to update the candidate information.
     * The information of the candidate is fetched for the given id and passed 
     * so that recruiter can modify the the required fields.
     * 
     * @param candidateId - Id of the candidate to be updated.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.VIEW_CANDIDATE_FOR_UPDATE, method = RequestMethod.GET)  
    private String viewCandidateForUpdate(@RequestParam(name = Constant.CANDIDATE_ID)
            long candidateId, Model model) {
        try { 
            Candidate candidate = candidateService.fetchCandidateById(candidateId);
            CandidateFormInfo candidateFormInfo = candidateService.getCandidateFormInfo(); 
            model.addAttribute(Constant.CANDIDATE_FORM_INFO, candidateFormInfo);  
            model.addAttribute(Constant.CANDIDATE, candidate); 
            model.addAttribute(Constant.ACTION, Constant.UPDATE); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.CREATE_CANDIDATE_JSP;
    }
 
    /**
     * When the recruiter is willing to update the candidate information.
     * The information of the candidate is fetched for the given id and passed 
     * so that recruiter can modify the the required fields.
     * 
     * @param candidateId - Id of the candidate to be updated.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.UPDATE_CANDIDATE, method = RequestMethod.POST)  
    private String updateCandidate(@ModelAttribute(Constant.CANDIDATE) Candidate candidate, 
    		@RequestParam(name = Constant.RESUME, required = false) MultipartFile resume,
    		Model model) {
        try { 
            candidate = candidateService.updateCandidate(candidate, resume);   
            model.addAttribute(Constant.CANDIDATE, candidate);
            System.out.println("inside");
            model.addAttribute(Constant.STATUS, Constant.UPDATED); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_CANDIDATE_JSP;
    }

 
    /**
     * All the candidate informations are fetched from DB.
     * 
     * @param model - Used to send candidate object along with request to jsp.
     * @return VIEW_CANDIDATES_JSP - Display all the fetched candidates information.
     */
    @RequestMapping(value = Constant.VIEW_CANDIDATES, method = RequestMethod.GET)  
    private String viewAllCandidate(Model model) {
        try { 
        	CandidatePagenationInfo pagenationInfo = 
        			candidateService.getPagenationInfo();
            model.addAttribute(Constant.PAGENATION_INFO, pagenationInfo); 
        	model.addAttribute(Constant.CANDIDATE_STATUSES, new ArrayList<CandidateStatus>(Arrays.asList(CandidateStatus.values())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_CANDIDATES_JSP;
    }
    
    /**
     * Recruiter entered informations are obtained as an object.
     * Then the object is passed to the DAO layer to store it in DB.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.SEARCH_BY_NAME, method = RequestMethod.POST)  
    private String searchByName(@RequestParam(name = Constant.NAME) String name, 
    		Model model) {
        try { 
        	CandidatePagenationInfo pagenationInfo = 
        			candidateService.searchByName(name);
            model.addAttribute(Constant.PAGENATION_INFO, pagenationInfo); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_CANDIDATES_JSP;
    }
    
    /**
     * Recruiter entered informations are obtained as an object.
     * Then the object is passed to the DAO layer to store it in DB.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.SEARCH_BY_STATUS, method = RequestMethod.POST)  
    private String searchByStatus(@RequestParam(name = Constant.RESULT) Result status,
    		Model model) {
        try { 
        	CandidatePagenationInfo pagenationInfo = 
        			candidateService.searchByStatus(status);
            model.addAttribute(Constant.PAGENATION_INFO, pagenationInfo); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_CANDIDATES_JSP;
    }
    
    /**
     * Recruiter entered informations are obtained as an object.
     * Then the object is passed to the DAO layer to store it in DB.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.VIEW_PROGRESS, method = RequestMethod.GET)  
    private String viewCandidateProgress(@RequestParam(name = Constant.ID) long candidateId,
    		Model model) {
        try { 
        	Candidate candidate = candidateService.getCandidateProgress(candidateId);
            model.addAttribute(Constant.CANDIDATE, candidate); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_CANDIDATE_JSP;
    }
    
    /**
     * Sets required information along with request.
     * Set the pageno  for pagenation along with request.
     * Forward the request to displayplayerspage.
     *
     * @param request - User request from server.
     * @param reponse - Response to server from servlet
     * @throws ServletException -
     *         Defines a general exception a servlet can throw when it 
     *         encounters difficulty.
     * @throws IOException - 
     *         general class of exceptions produced by failed or 
     *         interrupted I/O operations.
     */
    @RequestMapping(value = Constant.VIEW_ALL_CANDIDATES, method = RequestMethod.GET)  
    private void viewAllCandidates(HttpServletRequest request, HttpServletResponse 
            response) throws IOException {
    	try {
            int pageNo = Integer.parseInt(request.getParameter(Constant.PAGE_NO));
            Result candidateStatus = Result.valueOf(request.getParameter(Constant.RESULT));
            JSONArray playersInfo = candidateService.retrieveAllCandidates(pageNo, candidateStatus);
            response.setContentType(Constant.APPLICATION_JSON);
            response.getWriter().write(playersInfo.toString());
    	} catch(IsmException e) {
    	    System.out.println(e.getMessage());	
    	}
    }
   
    /**
     * Recruiter entered informations are obtained as an object.
     * Then the object is passed to the DAO layer to store it in DB.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.GET_CANDIDATES_BY_STATUS, method = RequestMethod.POST)  
    private String getCandidatesByStatus(@RequestParam(name = Constant.CANDIDATE_STATUS) CandidateStatus status, Model model) {
        try { 
            model.addAttribute(Constant.CANDIDATES, candidateService.getCandidatesByStatus(status)); 
        	model.addAttribute(Constant.CANDIDATE_STATUSES, new ArrayList<CandidateStatus>(Arrays.asList(CandidateStatus.values())));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_CANDIDATES_JSP;
    }
    
    /**
     * Recruiter entered informations are obtained as an object.
     * Then the object is passed to the DAO layer to store it in DB.
     * 
     * @param candidate - Created object.
     * @param model - Used to send candidate object along with request to jsp.
     * @return
     */
    @RequestMapping(value = Constant.SEARCH_BY_STATUS, method = RequestMethod.GET)  
    private String searchCandidateByStatus(@RequestParam(name = Constant.RESULT) Result status,
    		Model model) {
        try { 
        	CandidatePagenationInfo pagenationInfo = 
        			candidateService.searchByStatus(status);
            model.addAttribute(Constant.PAGENATION_INFO, pagenationInfo); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Constant.VIEW_CANDIDATES_JSP;
    }
}
