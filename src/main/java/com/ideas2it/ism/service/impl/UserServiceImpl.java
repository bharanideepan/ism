package com.ideas2it.ism.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ism.entity.Role;
import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.dao.UserRepository;
import com.ideas2it.ism.service.RoleService;
import com.ideas2it.ism.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
    /**
     * Get the Role object by user name
     *
     * @param userId - A key to check existence of user
     * @throws IsmException 
     */
    public User getUserByName(String userName) throws IsmException {
        User user = userRepository.findByName(userName);
        if(null == user) {
        	throw new IsmException("User not found for user name "+userName);
        }
        return user;
    }

	public void create(User user, String roleId) throws IsmException, NumberFormatException {
		System.out.println("user create");
		int id = Integer.parseInt(roleId);
        Role role = roleService.getRoleById(id);
        user.setRole(role);
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

	public List<User> getUser() {
		return userRepository.findAll();
	}


	@Override
	public boolean checkUser(String userName, String password) throws IsmException {
		User user = getUserByName(userName);
 		if(encrypt(password).equals(user.getPassword())) {
 			System.out.println(password);
			return Boolean.TRUE;
		}
		throw new IsmException("Incorrect Password");
	}
	
    public String encrypt(String input) throws IsmException {
		String password = null;
		if(null == input) return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			password = new BigInteger(1, digest.digest()).toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			throw new IsmException("Unable to encrypt");
		}
		return password;
    }
}