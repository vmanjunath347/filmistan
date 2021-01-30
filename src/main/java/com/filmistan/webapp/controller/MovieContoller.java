package com.filmistan.webapp.controller;

import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.dto.MovieRequest;
import com.filmistan.webapp.service.MovieService;


@RestController
@RequestMapping(path = "/filmistan/admin/movie")
public class MovieContoller {
	
	@Autowired
	private MovieService movieService;
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping()
	public JSONObject addMovie(@RequestBody MovieRequest movieRequest) {
		return movieService.AddMovie(movieRequest);
	}
	
	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/{id}")
	public String removeMovie(@PathVariable long id) {
		return movieService.deleteMovie(id);
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/all")
	public  JSONObject getAllMovie() {
		return movieService.getAllMovies();
	}
		
}
