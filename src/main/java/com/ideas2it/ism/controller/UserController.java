package com.ideas2it.ism.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.ism.common.Constant;
import com.ideas2it.ism.entity.Role;
import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.exception.IsmException;
//import com.ideas2it.ism.service.LoginService;
import com.ideas2it.ism.service.RoleService;
import com.ideas2it.ism.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    
    /**
     * Get all the users to display
     *
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     */
    @RequestMapping(value="/getUser")
    public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response)
		                 throws ServletException, IOException {
        ModelAndView model = new ModelAndView();
        try {
            List<User> users = userService.getUser();
            model.addObject("users", users);
            model.setViewName("/view-users");
        } catch (Exception e) {
            model.addObject("errorMessage", e);
            model.setViewName("/error");
        }
        return model;
    }
    
    /**
     * Create new user and get existing role for assigning to the user
     *
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     */
    @RequestMapping(value="/createUser")
    public ModelAndView createUser(HttpServletRequest request, HttpServletResponse response)
		                 throws ServletException, IOException {
        ModelAndView model = new ModelAndView();
    	try {
            List <Role> roles = roleService.getRoles();
            model.addObject("roles", roles);
            model.addObject(Constant.EMPLOYEES, roleService.getEmployees());
            model.addObject("user", new User());
            model.setViewName("/add-user");
    	} catch (Exception e) {
    		model.addObject("errorMessage", e);
            model.setViewName("/error");
    	}
        return model;
    }
    
    /**
     * Save the user details which is newly created
     *
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     */
    @RequestMapping(value="/saveUser")
    public ModelAndView saveUser(HttpServletRequest request, HttpServletResponse response, User user)
		                 throws ServletException, IOException {
        ModelAndView model = new ModelAndView();
        try {
        	String roleId = request.getParameter("userRoles");
        	long employeeId = Long.parseLong(request.getParameter(Constant.EMPLOYEE_ID));
            userService.create(user, roleId, employeeId);
            model.addObject(Constant.STATUS, Constant.CREATED);
            model.setViewName("/login");
        } catch (Exception e) {
            model.addObject("errorMessage", e);
            model.setViewName("/error");
        }
        return model;
    }
    
    /**
     * Get the user user Id for updating the details and redirect to update page
     *
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     */
    @RequestMapping(value="/editUser")
    public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response)
                         throws IOException, ServletException {
        ModelAndView model = new ModelAndView();
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            model.addObject("user", userService.getUserById(userId));
            model.setViewName("update-users");
        } catch (Exception e) {
            model.addObject("errorMessage", e);
            model.setViewName("/error");
        }
        return model;
    }
    
    /**
     * Get the user details by their user Id for updating the details
     *
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     */
    @RequestMapping(value="/updateUser")
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response, User user)
                         throws IOException, ServletException {
        ModelAndView model = new ModelAndView();
        try {
            userService.update(user);
            model.setViewName("redirect:/getUser");
        } catch (Exception e) {
            model.addObject("errorMessage", e);
            model.setViewName("/error");
        }
        return model;
    }
    
    /**
     * Soft deletes the user by user Id
     *
     * @param request - Process the user request
     * @param response - Send response back to user based on request
     */
    @RequestMapping("/deleteUser")
    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response)
                         throws IOException, ServletException {
        ModelAndView model = new ModelAndView();
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            userService.delete(userId);
            model.setViewName("redirect:/getUser");
        } catch (Exception e) {
            model.addObject("errorMessage", e);
            model.setViewName("/error");
        }
        return model;
    }
}