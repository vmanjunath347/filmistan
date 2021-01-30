package com.filmistan.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.dto.BookingRequest;
import com.filmistan.webapp.entity.Bookings;
import com.filmistan.webapp.entity.City;
import com.filmistan.webapp.entity.Movie;
import com.filmistan.webapp.entity.Show;
import com.filmistan.webapp.entity.Theatre;
import com.filmistan.webapp.entity.Timing;
import com.filmistan.webapp.entity.User;
import com.filmistan.webapp.exception.InvalidInputExcerption;
import com.filmistan.webapp.exception.NotFoundException;
import com.filmistan.webapp.repository.BookingRepository;
import com.filmistan.webapp.repository.CityRepository;
import com.filmistan.webapp.repository.ShowRepository;
import com.filmistan.webapp.repository.UserRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Service
public class UserService {
	
	
	@Autowired 
	private CityRepository cityRepository;
	
	@Autowired 
	private ShowRepository showRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private BookingRepository bookingRepository;
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllCities(){
		List<City> cities = cityRepository.findAll();
		
		JSONArray cityJson = new JSONArray();
		JSONObject outputJson = new JSONObject();
		
		for(City city : cities) {
			String cityName = city.getName();
			cityJson.add(cityName);
		}
		outputJson.put("Cities", cityJson);
		
		return outputJson;
		
	}
	
	public String validateLogin() {
		return "Success";
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMoviesInCities(long id) {
		Optional<City> tempCity = cityRepository.findById(id);
		
		if(!tempCity.isPresent())
			throw new NotFoundException("No City found by ID");
		
		City city = tempCity.get();
		
		List<Show> shows= showRepository.findAll();
		
		LocalDate today = LocalDate.now();
		
		JSONArray movieJson = new JSONArray();
		
		for(Show show : shows) {
			LocalDate showdate = show.getShowDate();
			if(showdate.compareTo(today)>=0) {
				Timing timing = show.getTiming();
				Theatre theatre = timing.getTheatre();
				City tempCity2 = theatre.getCity();
				
				if(tempCity2== city) {
					String movieName = show.getMovie().getName();
					if(!(movieJson.contains(movieName)))
						movieJson.add(movieName);
				}
				
			}
			
		}
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("movies", movieJson);
		return outputJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMoviestiming(long id,long movie_id) {
		Optional<City> tempCity = cityRepository.findById(id);
		
		if(!tempCity.isPresent())
			throw new NotFoundException("No City found by ID");
		
		City city = tempCity.get();
		
		List<Show> shows= showRepository.findAll();
		
		LocalDate today = LocalDate.now();
		
		JSONArray movieJson = new JSONArray();
		
		for(Show show : shows) {
			LocalDate showdate = show.getShowDate();
			if(showdate.compareTo(today)>=0) {
				Timing timing = show.getTiming();
				Theatre theatre = timing.getTheatre();
				City tempCity2 = theatre.getCity();
				Movie movie = show.getMovie();
				
				if((tempCity2== city)&&(movie.getId()==movie_id)&&(show.getTotalSeats()-show.getBookedSeats()>0)) {
					JSONObject showJson = new JSONObject();
					showJson.put("Date", showdate);
					showJson.put("theatre", theatre.getName());
					showJson.put("Time", show.getTiming().getShowTime());
					showJson.put("Seats Available", show.getTotalSeats() - show.getBookedSeats());
					movieJson.add(showJson);
				}
				
			}
			
		}
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("movies", movieJson);
		return outputJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject bookTicket(BookingRequest bookingRequest) {
		
		int numberOfTickets = bookingRequest.getTicketCount();
		if(numberOfTickets>10)
				throw new InvalidInputExcerption("CAnnot Book more than 10 Tickets");
		
		long showId = bookingRequest.getShowId();
		
				Optional<Show> tempShow = showRepository.findById(showId);
				if(!tempShow.isPresent())
					throw new NotFoundException("No Show found by ID");
				
				Show show = tempShow.get();
				
				int availableSeat = show.getTotalSeats()-show.getBookedSeats();
				
				if(availableSeat<numberOfTickets)
					throw new InvalidInputExcerption("Try a lesser number of ticket");
				
				 String username =  bookingRequest.getUsername();
				 
				 List<User> users = userRepository.findAll();
				 System.out.println("username "+username);
				 long userId = 0;
				 
				 for(User user : users) {
					 System.out.println(user.getUsername());
					 if(user.getUsername().equals(username)) {
						 userId = user.getId();
						 break;
					 }
				 }
				 
				 if(userId==0)
					  throw new NotFoundException("UserNotFound");
					
					User usser = userRepository.findById(userId).get();
					Bookings booking = new Bookings();
					booking.setShow(show);
					booking.setTicketCount(numberOfTickets);
					booking.setUser(usser);
					show.setBookedSeats(show.getBookedSeats()+numberOfTickets);
					showRepository.save(show);
					bookingRepository.save(booking);
					
					String movieName = show.getMovie().getName();
					LocalDate showDate = show.getShowDate();
					LocalTime showTime = show.getTiming().getShowTime();
					String theatreName = show.getTiming().getTheatre().getName();
					String cityName = show.getTiming().getTheatre().getCity().getName();
					JSONObject outputJson = new JSONObject();
					
					outputJson.put("Movie", movieName);
					outputJson.put("City", cityName);
					outputJson.put("Theatre", theatreName);
					outputJson.put("Date", showDate);
					outputJson.put("Time", showTime);
					outputJson.put("TicketCount", numberOfTickets);
					
					return outputJson;
					
	}

}
