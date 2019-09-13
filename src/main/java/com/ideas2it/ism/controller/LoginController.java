package com.ideas2it.ism.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.service.UserService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * Redirect to the login request url.
	 * 
     * @param request - Process the user request
     * @param response - Send response back to user based on request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
    @RequestMapping(value=Constant.LOGIN)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
		                 throws ServletException, IOException {
    	return new ModelAndView(Constant.LOGIN);
    }

    /**
     * Check role and redirect to page based on user role
     * 
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     *
     * @param session - A object to hold user details
     * @return
     */
    @RequestMapping(value="/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session)
		                 throws ServletException, IOException {
        ModelAndView model = new ModelAndView();
        String role = (String) session.getAttribute(Constant.ROLE);
        if(null != session.getAttribute(Constant.USER)) {
        	if(role.contains(Constant.ADMIN)) {
        		model.setViewName(Constant.REDIRECT+Constant.NEW_SCHEDULES);
        	} else if(role.contains(Constant.MANAGER)) {
        		model.setViewName(Constant.REDIRECT+Constant.VIEW_SCHEDULES_MANAGER);
        	} else if(role.contains(Constant.RECRUITER)) {
        		model.setViewName(Constant.REDIRECT+Constant.VIEW_CANDIDATES);
        	} else {
        		model.setViewName(Constant.REDIRECT+Constant.NEW_SCHEDULES);
        	}
        } else {
        	model.setViewName(Constant.REDIRECT+Constant.LOGIN);
        }
        return model;
    }
    
    /**
     * Process the user entered login details and direct to autherized page
     * 
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     */
    @RequestMapping(value=Constant.LOGIN_USER)
    public ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException, IsmException {
        ModelAndView model = new ModelAndView();
    	HttpSession session = request.getSession();
    	User loginUser = userService.getUserByName(user.getName());
	    session.setAttribute(Constant.ROLE, loginUser.getRole().getName());
        try {
	        if(userService.checkUser(user.getName(), user.getPassword())) {
	        	session.setAttribute(Constant.USER, loginUser.getName());
	        	session.setAttribute(Constant.EMPLOYEE, loginUser.getEmployee().getId());
	        }
	        model.setViewName(Constant.REDIRECT);
        } catch (IsmException e) {
        	logger.error(e.getMessage() + "for" + user.getName());
        	model.addObject(Constant.MESSAGE, e.getMessage());
        	model.setViewName(Constant.REDIRECT+Constant.LOGIN);
        }
    	return model;
    }
    
    /**
     * Invalidate the logged in user session
     * 
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     * 
     * @return - Redirect login page
     */
    @RequestMapping(value=Constant.LOGOUT)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return new ModelAndView(Constant.REDIRECT+Constant.LOGIN);
    }
}