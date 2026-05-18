package com.ideas2it.ism.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ideas2it.ism.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	@Query(value = "SELECT * FROM user WHERE name = :name", nativeQuery = true)
	public User findByName(@Param("name") String name);

	public User findById(int userId);
	

}