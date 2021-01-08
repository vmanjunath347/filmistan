package com.filmistan.webapp.controller;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.entity.User;
import com.filmistan.webapp.repository.UserRepository;
import com.filmistan.webapp.exception.UserAlreadyExistsException;


@RestController
@RequestMapping(path = "/filmistan")
public class userController {
	
	//inject dependancy
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		
			String username = user.getUsername();
			
			List<User> users =  this.userRepository.findAll();
			
			ListIterator<User> iterator = users.listIterator();
			
			while(iterator.hasNext()) {
				User tempUser = iterator.next();
				String tempUsername = tempUser.getUsername();
				if(tempUsername.equals(username)) {
					throw new UserAlreadyExistsException("User already exists with same name"); 
				}
				
			}
	
			return this.userRepository.save(user);
	}
	
	
	//homepage
	@GetMapping()
	public String getWelcomePage(){
		return "Welcome to Filmistan";
	}

}
