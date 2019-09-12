package com.ideas2it.ism.service;

import java.util.List;

import com.ideas2it.ism.entity.User;
import com.ideas2it.ism.exception.IsmException;

public interface UserService {
    public User getUserByName(String userName) throws IsmException;

    /**
     * Create new user along with their details which user registered.
     * 
     * @param user - A object which has new user details to be saved
     */
	public void create(User user, String[] roleIds);

	/**
	 * Get the existing user by using the user Id
	 * 
	 * @param userId - A id to find the user and retrieving
	 * @return user - A user object which is retrieved by the id.
	 */
	public User getUserById(int userId);

	/**
	 * Get the user details for existing user and save it.
	 * 
	 * @param user - A object which has new details for existing user which is to be updated
	 */
	public void update(User user);

	/**
	 * Soft deletes the user by changin the status
	 * 
	 * @param userId
	 */
	public void delete(int userId);

	/**
	 * Get all the existing user which are registerd.
	 * 
	 * @return
	 */
	public List<User> getUser();
	
    public String encrypt(String input);
	
	//public void assignassignRole(String[] roleIds, User user);

	public boolean checkUser(String userName, String password, String role) throws IsmException;
}