package com.ideas2it.ism.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Role;
import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.dao.RoleRepository;
import com.ideas2it.ism.service.EmployeeService;
import com.ideas2it.ism.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}
	
	public Role getRoleById(int roleId) {
		return (Role) roleRepository.findById(roleId);
	}

	@Override
	public List<Employee> getEmployees() {
  		return employeeService.getNewEmployees();
	}
	
	
}