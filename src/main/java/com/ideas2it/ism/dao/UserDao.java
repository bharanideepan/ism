package com.ideas2it.ism.dao;

import com.ideas2it.ism.entity.User;

public interface UserDao {
	public User findByName(String name);
}
