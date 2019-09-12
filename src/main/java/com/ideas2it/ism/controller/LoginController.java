package com.ideas2it.ism.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ism.entity.Role;
import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.service.RoleService;
import com.ideas2it.ism.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	
    @RequestMapping(value="/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
		                 throws ServletException, IOException {
        ModelAndView model = new ModelAndView();
        try {
        	List <Role> roles = roleService.getRoles();
            model.addObject("roles", roles);
            model.addObject("message", "Enter login details");
            model.setViewName("/login");
        } catch (Exception e) {
            model.addObject("errorMessage", e);
            model.setViewName("/error");
        }
    	return model;
    }

    @RequestMapping(value="/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session)
		                 throws ServletException, IOException {
        ModelAndView model = new ModelAndView();
        String role = (String) session.getAttribute("role");
        if(null != session.getAttribute("user")) {
        	long employeeId = (long) session.getAttribute("employee");
        	if(role.equals("ROLE_ADMIN")) {
        		model.setViewName("redirect:/newSchedules");
        		//model.setViewName("redirect:/index"); 
        	} else if(role.equals("ROLE_MANAGER")) {
        		model.setViewName("redirect:/viewSchedules");
        	} else if(role.equals("ROLE_RECRUITER")) {
        		model.setViewName("redirect:/viewCandidates");
        	} else {
        		model.setViewName("/viewNewSchedules?id="+employeeId);
        	}
        } else {
        	model.setViewName("redirect:/login");
        }
        return model;
    }
    
    @RequestMapping(value="/loginUser")
    public ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException, IsmException {
    	String role = request.getParameter("role");
    	User loginUser = userService.getUserByName(user.getName());
        ModelAndView model = new ModelAndView();
        try {
        	HttpSession session = request.getSession();
	        if(userService.checkUser(user.getName(), user.getPassword(), role)) {
	        	session.setAttribute("user", user.getName());
	        	session.setAttribute("role", request.getParameter("role"));
	        	session.setAttribute("employee", loginUser.getEmployee().getId());
	        }
	        model.setViewName("redirect:/");
        } catch (IsmException e) {
        	model.addObject("message", e.getMessage());
        	model.setViewName("redirect:/login");
        }
    	return model;
    }
    
    @RequestMapping(value="/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        System.out.println("session" +session);
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }
}