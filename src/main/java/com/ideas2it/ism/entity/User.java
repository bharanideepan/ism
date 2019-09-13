package com.ideas2it.ism.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ideas2it.ism.entity.Role;

/**
 * Set the values to the corresponding variables for user which is given which includes
 * name, password and role.
 *
 * @author Arunkumar
 * @Date 04/09/19
 */
@Entity
@Table (name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
	
    @Column (name = "name")
    private String name;

    @Column (name = "password")
    private String password;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Employee employee;
    
    public String toString() {
        return name + password + role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
    public void setEmployee(Employee employee) {
    	this.employee = employee;
    }
    
    public Employee getEmployee( ) {
    	return employee;
    }
}
