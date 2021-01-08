package com.filmistan.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.entity.User;
import com.filmistan.webapp.repository.UserRepository;

@RestController
@RequestMapping(path = "/filmistan/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepository;
	
	//getAllusers
	@GetMapping("/user/all")
	public List<String> getAllUsers(){
			List<User> users =  this.userRepository.findAll();
			List<String> userNames = new ArrayList<String>(); 
			
			ListIterator<User> iterator = users.listIterator();
			
			while(iterator.hasNext()) {
				User tempUser = iterator.next();
				String username = tempUser.getUsername();
				userNames.add(username); 
				
			}
			
			return userNames;
	}
	
}