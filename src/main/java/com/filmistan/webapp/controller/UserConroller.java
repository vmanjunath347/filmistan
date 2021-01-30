package com.filmistan.webapp.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmistan.webapp.dto.BookingRequest;
import com.filmistan.webapp.service.MovieService;
import com.filmistan.webapp.service.ShowService;
import com.filmistan.webapp.service.UserService;

@RestController
@RequestMapping(path = "/filmistan/user")
public class UserConroller {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	ShowService showService;
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("show/all")
	public JSONObject getAllShows() {
		return showService.getallShows();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("city/all")
	public JSONObject getAllCities(){
		return userService.getAllCities();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("city/{id}")
	public JSONObject getMoviesCity(@PathVariable long id){
		return userService.getMoviesInCities(id);
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/{city_id}/{movie_id}")
	public JSONObject getMovietimings(@PathVariable long city_id, @PathVariable long movie_id){
		return userService.getMoviestiming(city_id, movie_id);
	}
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping("show/book")
	public JSONObject bookTicket(@RequestBody BookingRequest bookingRequest) {
		return userService.bookTicket(bookingRequest);
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/validate")
	public String validateUSerLogin(){
	 return userService.validateLogin();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/movie/all")
	public  JSONObject getAllMovie() {
		return movieService.getAllMovies();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/movie/{id}")
	public  JSONObject getMovieById(@PathVariable long id) {
		return movieService.getMovieById(id);
	}
}
