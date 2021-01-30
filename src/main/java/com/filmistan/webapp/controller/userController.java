package com.filmistan.webapp.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.entity.User;
import com.filmistan.webapp.service.BasicService;

@RestController
@RequestMapping(path = "/filmistan")
public class userController {
	
	@Autowired 
	private BasicService basicService;
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/register")
	public JSONObject register(@RequestBody User user) {
		
			return basicService.register(user);
	}
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping("/adminregister")
	public JSONObject registerAsAdmin(@RequestBody User user) {
		
			return basicService.registerAdmin(user);
	}
	
	@SuppressWarnings("unchecked")
	@CrossOrigin("http://localhost:4200")
	@GetMapping()
	public JSONObject getWelcomePage(){
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("welcome message", "Welcome to Filmistan");
		return outputJson;
	}

}
