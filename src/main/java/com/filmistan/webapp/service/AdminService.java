package com.filmistan.webapp.service;

import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.entity.User;
import com.filmistan.webapp.repository.UserRepository;

@Service
public class AdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllUsers() {
		List<User> users =  this.userRepository.findAll();
		
		ListIterator<User> iterator = users.listIterator();
		
		JSONArray userArray = new JSONArray();
		
		while(iterator.hasNext()) {
			User tempUser = iterator.next();
			String username = tempUser.getUsername();
			userArray.add(username);
			
		}
		
		JSONObject njsonObject = new JSONObject();
		njsonObject.put("users", userArray);
		return njsonObject;
	}
	
	public String validateLogin() {
		return "Success";
	}
	
	

}
