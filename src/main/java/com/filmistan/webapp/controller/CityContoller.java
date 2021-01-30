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

import com.filmistan.webapp.entity.City;
import com.filmistan.webapp.service.CityService;

@RestController
@RequestMapping(path = "/filmistan/admin/city")
public class CityContoller {
	
	@Autowired
	private CityService cityService;
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping()
	public City addCity(@RequestBody City city) {
			return cityService.addCity(city);
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/all")
	public JSONObject getAllCities(){
		
		return cityService.getAllCity();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/{id}" )
	public JSONObject getCitiesById(@PathVariable long id){
		return cityService.getCity(id);
	}
	
	@CrossOrigin("http://localhost:4200")
	@PutMapping("/{id}")
	public JSONObject editCity(@PathVariable long id, @RequestBody City tempcity) {
		return cityService.editCity(id, tempcity);
	}
	
	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/{id}")
	public String deleteCity(@PathVariable long id) {
		return cityService.deleteCity(id);
	}
	

}
