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
     * @throws IsmException 
     */
	public void create(User user, String roleId) throws IsmException;

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
	 * Get all the existing user which are registerd.
	 * 
	 * @return
	 */
	public List<User> getUser();
	
	/**
	 * Encrypt the user entered password using MD5 Algorithm.
	 * 
	 * @param input - User entered password to encrypt
	 * @return Encrypted password
	 * @throws IsmException
	 */
    public String encrypt(String input) throws IsmException;
	
    /**
     * Check the user exist for the given user name and password
     * 
     * @param userName - Name which user entered for authenticate
     * @param password - Password for authenticate
     * @return If user credentials are true
     * @throws IsmException - If credentials are not correct
     */
	public boolean checkUser(String userName, String password) throws IsmException;
}