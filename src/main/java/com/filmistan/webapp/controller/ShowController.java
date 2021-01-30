package com.filmistan.webapp.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.service.ShowService;

@RestController
@RequestMapping(path = "/filmistan/admin/show")
public class ShowController {
	
	@Autowired
	ShowService showService;
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("all")
	public JSONObject getAllShows() {
		return showService.getallShows();
	}
}
