package com.ideas2it.ism.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.entity.Employee;
import com.ideas2it.ism.entity.Role;
import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.dao.UserDao;
import com.ideas2it.ism.dao.UserRepository;
import com.ideas2it.ism.service.EmployeeService;
import com.ideas2it.ism.service.RoleService;
import com.ideas2it.ism.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private EmployeeService employeeService;
	
    /**
     * Get the Role object by user name
     *
     * @param userId - A key to check existence of user
     * @throws IsmException 
     */
    public User getUserByName(String userName) throws IsmException {
        User user = userRepository.findByName(userName);
        if(null == user) {
        	throw new IsmException("User not found");
        }
        return user;
    }

	public void create(User user, String roleId, long employeeId) {
		System.out.println("user create");
		Employee employee = employeeService.getEmployeeById(employeeId);
		int id = Integer.parseInt(roleId);
        Role role = roleService.getRoleById(id);
        user.setRole(role);
        String password = encrypt(user.getPassword());
        user.setPassword(password);
        user.setEmployee(employee);
        employee.setUser(user);
        userRepository.save(user);
	}

	public User getUserById(int userId) {
		return (User) userRepository.findById(userId);
		//return user;
	}

	public void update(User user) {
		User existingUser = userRepository.findById(user.getId());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
	}

	public void delete(int userId) {
		// TODO Auto-generated method stub
		
	}

	public List<User> getUser() {
		return userRepository.findAll();
	}


	@Override
	public boolean checkUser(String userName, String password) throws IsmException {
		User user = getUserByName(userName);
 		if(this.encrypt(password).equals(user.getPassword())) {
 			System.out.println(password);
			return Boolean.TRUE;
		}
		throw new IsmException("Bad User Credentials");
	}
	
    public String encrypt(String input) {
		String password = null;
		if(null == input) return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			password = new BigInteger(1, digest.digest()).toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			System.out.println("Unable to encrypt");
		}
		return password;
    }
}