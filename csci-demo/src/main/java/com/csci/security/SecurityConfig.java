package com.csci.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security Configuration
 * @author seth.ellison
 * 
 * Spring Security is an extremely powerful feature of Spring, which
 * you can leverage to secure your application. It allows you to secure
 * URLs against the public, set up a user authentication scheme, and
 * manage how login forms work.
 * 
 * Additionally, it allows you to easily implement a Remember Me behavior
 * for your site's authentication system, and more.
 * 
 * In this example, we simply create a secured URL /admin (which requires an ADMIN role to access)
 * and then specify that any other URL is free and open to access by unauthenticated users.
 * 
 * We also define an in-memory (not linked to a database!) definition of a user "user" with the password, "password."
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		// For simplicity of demonstrating some web calls, I've disabled the Cross-Site Request Forgery protection.
		http.csrf().disable(); 
		
		// This allows us to secure specific URLs, limiting access to pages.
        http
        	.authorizeRequests()
        		.antMatchers("/admin*").access("hasRole('ADMIN')")
        		.antMatchers("/**").permitAll()
        		.and()
        	.formLogin();
        
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	// This creates a user in memory with the ADMIN role. This definition allows us to login
    	// as the user: cooluser, with the password: coolpassword
    	// and be granted the ADMIN permission.
        auth
        	.inMemoryAuthentication()
        		.withUser("cooluser")
        		.password("coolpassword")
        		.roles("ADMIN");
    }
}
