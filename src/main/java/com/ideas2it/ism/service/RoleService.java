package com.ideas2it.ism.service;

import java.util.List;

import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Role;

public interface RoleService {
	
    /**
     * Get the list of all availabe roles in list
     *
     * @param roles - Holds roles for user
     */
    public List<Role> getRoles();
    
    /**
     * Get the role having the details by Id
     *
     * @param roleId - A key to get the role details
     *
     * @return role
     * throws exception when there is no role
     */
    public Role getRoleById(int roleId);
    
    /**
     * Get list of employees to show while creating a new user. Employees
     * who does not have an user account are fetched.
     * 
     * @return employees - List of employees who dont have an user account.
     */
    public List<Employee> getEmployees();
}