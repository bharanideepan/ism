package com.ideas2it.ism.service;
/**
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ideas2it.ism.entity.Role;
import com.ideas2it.ism.exception.IsmException;
import com.ideas2it.ism.service.UserService;

/**
 * Gets the userName which user entered and authendicate the username and password
 *
 * @author - Arunkumar
 * @date - 05/09/19
 *
//@Service("userDetailsService")
//@RequestMapping("/loginSecure")
//@Component("customLogin")
//@Service
public class LoginService implements UserDetailsService {
	
    @Autowired
    private UserService userService;


    /**
     * Get the userName and check if the user exist
     *
     * @param userName - The name which user entered for login
     *
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	UserDetails userDetails = null;
    	try {
            com.ideas2it.ism.entity.User domainUser = userService.getUserByName(userName);
            System.out.println("DomainUser..."+domainUser);
            List<SimpleGrantedAuthority> authorities = getAuthorities(domainUser);
            userDetails = (UserDetails)new User(domainUser.getName(), 
				    domainUser.getPassword(), authorities);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return userDetails;
    }

    /**
     * Assign authority for user based on their role
     *
     *
    private List<SimpleGrantedAuthority> getAuthorities(com.ideas2it.ism.entity.User domainUser) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        System.out.println(domainUser.getRoles());
        for(Role role : domainUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println("roel naem"+role.getName());
        }
        return authorities;
    }
}*/
