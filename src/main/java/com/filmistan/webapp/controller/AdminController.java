package com.filmistan.webapp.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.service.AdminService;

@RestController
@RequestMapping(path = "/filmistan/admin")
public class AdminController {
	
	
	@Autowired 
	private AdminService AdminService;
	
	//getAllusers
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/user/all")
	public JSONObject getAllUsers(){
		return AdminService.getAllUsers();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/validate")
	public String validateAdminLogin(){
		return AdminService.validateLogin();
	}
	
	
}