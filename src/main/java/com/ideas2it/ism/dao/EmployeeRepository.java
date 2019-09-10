package com.ideas2it.ism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ideas2it.ism.common.Technology;
import com.ideas2it.ism.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/**
	 * Employees working in the particular technology who are interested to take 
	 * interview is fetched as list and passed.
	 *  
	 * @param technology - Employees corresponding to this technology is to be fetched.
	 * @return employees - List of employees working in the particular technology.
	 */
	@Query("SELECT e FROM Employee e WHERE technology = :technology and is_interviewer = 1")
	List<Employee> fetchEmployeesByTechnology(Technology technology);

}
