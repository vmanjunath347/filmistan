package com.filmistan.webapp.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.dto.TimingRequest;
import com.filmistan.webapp.service.TimingService;

@RestController
@RequestMapping(path = "/filmistan/admin/timing")
public class TimingController {
	
	@Autowired
	private TimingService timingService;
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping()
	public JSONObject createTiming(@RequestBody TimingRequest timingRequest) {
		return timingService.createTiming(timingRequest);		
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/all")
	public JSONObject getAllTimings(){
		return timingService.getAlltimings();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/{id}" )
	public JSONObject getTimingsById(@PathVariable long id){
		
		return timingService.getTimeByID(id);
		
	}
	
	@CrossOrigin("http://localhost:4200")
	@PutMapping("/{id}")
	public JSONObject editTiming(@PathVariable long id, @RequestBody TimingRequest tempTiming) {
		return timingService.editTiming(id, tempTiming);
	}
	
	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/{id}")
	public String editTiming(@PathVariable long id) {
		return timingService.deleteTiming(id);
	}
}
