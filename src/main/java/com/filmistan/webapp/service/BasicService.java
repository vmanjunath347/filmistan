package com.filmistan.webapp.service;

import java.util.List;
import java.util.ListIterator;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.entity.User;
import com.filmistan.webapp.exception.UserAlreadyExistsException;
import com.filmistan.webapp.repository.UserRepository;

@Service
public class BasicService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@SuppressWarnings("unchecked")
	public JSONObject register(User user) {
		String username = user.getUsername();
		user.setRole("USER");
		List<User> users =  this.userRepository.findAll();
		
		ListIterator<User> iterator = users.listIterator();
		
		while(iterator.hasNext()) {
			User tempUser = iterator.next();
			String tempUsername = tempUser.getUsername();
			
			if(tempUsername.equals(username)) {
				throw new UserAlreadyExistsException("User already exists with same name"); 
			}
			
		}
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		this.userRepository.save(user);
		
		JSONObject outputJson = new JSONObject();
		
		outputJson.put("name", user.getName());
		outputJson.put("username", user.getUsername());
		outputJson.put("role", user.getRole());
		
		return outputJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject registerAdmin(User user) {
		String username = user.getUsername();
		user.setRole("ADMIN");
		List<User> users =  this.userRepository.findAll();
		
		ListIterator<User> iterator = users.listIterator();
		
		while(iterator.hasNext()) {
			User tempUser = iterator.next();
			String tempUsername = tempUser.getUsername();
			
			if(tempUsername.equals(username)) {
				throw new UserAlreadyExistsException("User already exists with same name"); 
			}
			
		}
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		this.userRepository.save(user);
		
		JSONObject outputJson = new JSONObject();
		
		outputJson.put("name", user.getName());
		outputJson.put("username", user.getUsername());
		outputJson.put("role", user.getRole());
		
		return outputJson;
	}
	
	
}
