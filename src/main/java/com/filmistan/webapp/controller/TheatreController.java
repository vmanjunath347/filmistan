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

import com.filmistan.webapp.dto.Theatrerequest;
import com.filmistan.webapp.entity.Theatre;
import com.filmistan.webapp.service.TheatreService;

@RestController
@RequestMapping(path = "/filmistan/admin/theatre")
public class TheatreController {
	
	@Autowired
	private TheatreService theatreService;
	
	@CrossOrigin("http://localhost:4200")
	@PostMapping()
    public JSONObject create(@RequestBody Theatrerequest theatreRequest) {  
		return theatreService.addTheatre(theatreRequest);
		
    }
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/all")
	public JSONObject getAllTheatres(){
		
		return theatreService.getAllTheatres();
	}
	
	@CrossOrigin("http://localhost:4200")
	@GetMapping("/{id}" )
	public JSONObject getTheatreById(@PathVariable long id){
		return theatreService.getTheatreById(id);
	}
	
	@CrossOrigin("http://localhost:4200")
	@PutMapping("/{id}")
	public JSONObject editTheatre(@PathVariable long id, @RequestBody Theatre tempTheatre) {
		return theatreService.editTheatreById(tempTheatre, id);
	}
	
	@CrossOrigin("http://localhost:4200")
	@DeleteMapping("/{id}")
	public JSONObject deleteTheatre(@PathVariable long id) {
		return theatreService.deleteTheatreById(id);
	}
	
	
}
