package com.filmistan.webapp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.entity.Show;
import com.filmistan.webapp.repository.ShowRepository;



@Service
public class ShowService {

	@Autowired
	private ShowRepository showRepository;
	
	@SuppressWarnings("unchecked")
	public JSONObject getallShows() {
		
		List<Show> shows = showRepository.findAll();
		
		JSONArray showArray= new JSONArray();
		for(Show show : shows) {
			long tempShowId = show.getId();
			int tempBookedSeats = show.getBookedSeats();
			long tempMovieId = show.getMovie().getId();
			LocalDate tempShowDate= show.getShowDate();
			long tempShowTimeId = show.getTiming().getId();
			int tempTotalSeats = show.getTotalSeats();
			LocalTime tempShowTimeTime = show.getTiming().getShowTime();
			long theatreId = show.getTiming().getTheatre().getId();
			String theatreName = show.getTiming().getTheatre().getName();
			long cityId= show.getTiming().getTheatre().getCity().getId();
			String cityName = show.getTiming().getTheatre().getCity().getName();
			
				
			JSONObject tempJosn = new JSONObject();
			tempJosn.put("showId", tempShowId);
			tempJosn.put("bookedSeats", tempBookedSeats);
			tempJosn.put("movieId", tempMovieId);
			tempJosn.put("date", tempShowDate);
			tempJosn.put("time", tempShowTimeId);
			tempJosn.put("totalSeats", tempTotalSeats);
			tempJosn.put("theatreId", theatreId);
			tempJosn.put("theatreName", theatreName);
			tempJosn.put("cityId", cityId);
			tempJosn.put("cityName", cityName);
			tempJosn.put("showTime", tempShowTimeTime);
			
			showArray.add(tempJosn);
		}
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("shows", showArray);
		return outputJson;
	}
}
