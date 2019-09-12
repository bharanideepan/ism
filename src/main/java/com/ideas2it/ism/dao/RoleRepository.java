package com.ideas2it.ism.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.ism.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

	Role findById(int roleId);

}