package com.filmistan.webapp;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.filmistan.webapp.controller.UserConroller;

public class BookingUnit {
	
	@Autowired
	UserConroller userController = new UserConroller();
	@Test
	public void test() {
		
		JSONObject output =  this.userController.getAllCities();
		
		assertEquals("Bengaluru", output);
	}

}
