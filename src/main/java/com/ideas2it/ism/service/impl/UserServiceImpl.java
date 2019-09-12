package com.ideas2it.ism.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.entity.Role;
import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.dao.UserDao;
import com.ideas2it.ism.dao.UserRepository;
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
	
    /**
     * Get the Role object by user name
     *
     * @param userId - A key to check existance of user
     * @throws IsmException 
     */
    public User getUserByName(String userName) throws IsmException {
        User user = userRepository.findByName(userName);
        if(null == user) {
        	throw new IsmException("User not found");
        }
        return user;
    }

	public void create(User user, String[] roleIds) {
        //user.setCreatedDate(new Date());
		System.out.println("user create");
        assignRole(roleIds, user);
        String password = encrypt(user.getPassword());
        System.out.println(password+"pass");
        user.setPassword(password);
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

    /**
     * Assign roles to particular user by roleId
     *
     * @param roleIds - A array of role id which is to be assigned to the user
     * @param user - Object to hold user details
     */
    public void assignRole(String[] roleIds, User user) {
        Set<Role> roles = new HashSet<Role>();
        for(String id : roleIds) {
            int roleId = Integer.parseInt(id);
            Role role = roleService.getRoleById(roleId);
            roles.add(role);
        }
        user.setRoles(roles);
    }

	public void create(User user, String roleIds) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean checkUser(String userName, String password, String role) throws IsmException {
		User user = getUserByName(userName);
		System.out.println("CHeck User");
		System.out.println(password);
		System.out.println(user.getPassword());
		List<String> roles = new ArrayList<String>(); 
		for(Role userRole : user.getRoles()) {
			roles.add(userRole.getName());
		}
 		if(encrypt(password).equals(user.getPassword()) && roles.contains(role)) {
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