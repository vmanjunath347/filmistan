package com.filmistan.webapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.dto.MovieRequest;
import com.filmistan.webapp.entity.Movie;
import com.filmistan.webapp.entity.Show;
import com.filmistan.webapp.entity.Theatre;
import com.filmistan.webapp.entity.Timing;
import com.filmistan.webapp.exception.InvalidInputExcerption;
import com.filmistan.webapp.exception.NotFoundException;
import com.filmistan.webapp.exception.UserAlreadyExistsException;
import com.filmistan.webapp.repository.MovieRepository;
import com.filmistan.webapp.repository.ShowRepository;
import com.filmistan.webapp.repository.TimingRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TimingRepository timingRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@SuppressWarnings("unchecked")
	public JSONObject AddMovie(MovieRequest movieRequest) {
		
		String movieName = movieRequest.getName();
		int startDay = movieRequest.getStartDay();
		int startMonth = movieRequest.getStartMonth();
		int startYear = movieRequest.getStartYear();
		int endDay = movieRequest.getEndDay();
		int endMonth = movieRequest.getEndMonth();
		int endYear = movieRequest.getEndYear();
		
		List<Long> timingIds = movieRequest.getTimingId();
		
		if((startYear>endYear)||((startYear==endYear)&&(startMonth>endMonth))||((startYear==endYear)&&(startMonth==endMonth)&&(startDay>endDay)))
			throw new InvalidInputExcerption("Start date cannot be after end date");
			
		LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
		LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
		
		
		Movie movie = new Movie();
		movie.setName(movieName);
		movie.setFromDate(startDate);
		movie.setToDate(endDate);
	
		movieRepository.save(movie);
		
		while(endDate.compareTo(startDate)!=0) {
			
			for(Long timingId : timingIds) {
				
				Optional<Timing> optipnalTiming= timingRepository.findById(timingId);
				
				if(!optipnalTiming.isPresent())
					throw new NotFoundException("Timing by ID "+timingId+" is not found");
				Timing timing = optipnalTiming.get();
				
				List<Show> shows = showRepository.findAll();
				for(Show show : shows) {
					Timing tempTiming = show.getTiming();
					LocalDate tempDate = show.getShowDate();
					
					if((tempDate.compareTo(startDate)==0)&&(timing == tempTiming))
						throw new UserAlreadyExistsException("Showtimes overlap with other movies");
				}
				
				
				Show tempShow = new Show();
				tempShow.setMovie(movie);
				tempShow.setShowDate(startDate);
				tempShow.setBookedSeats(0);
				tempShow.setTiming(timing);
				Theatre theatre = timing.getTheatre();
				int totalSeats = theatre.getSeatCount();
				tempShow.setTotalSeats(totalSeats);
				showRepository.save(tempShow);
			}
			startDate = startDate.plusDays(1);
		}
		
		
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("Name", movieName);
		outputJson.put("From Date", startDate);
		outputJson.put("To Date", endDate);
		
		return outputJson;
	}
	
	public String deleteMovie(long id) {
		Optional<Movie> optipnalMovie= movieRepository.findById(id);
		if(!optipnalMovie.isPresent())
			throw new NotFoundException("Movie not found");
		movieRepository.deleteById(id);
		
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllMovies() {
		List<Movie> movies = movieRepository.findAll();
		
		JSONArray movieArray = new JSONArray();
		for(Movie movie : movies) {
			long tempId = movie.getId();
			String tempName = movie.getName();
			JSONObject tempJson = new JSONObject();
			tempJson.put("id", tempId);
			tempJson.put("name", tempName);
			movieArray.add(tempJson);
		}
		JSONObject finalJson = new JSONObject();
		finalJson.put("movies", movieArray);
		return finalJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getMovieById(long id) {
		Optional<Movie> optipnalMovie= movieRepository.findById(id);
		if(!optipnalMovie.isPresent())
			throw new NotFoundException("Movie not found");
		
		Movie movie = optipnalMovie.get();
		JSONObject finalJson = new JSONObject();
		
		finalJson.put("id", movie.getId());
		finalJson.put("name", movie.getName());
		
		JSONArray movieArray = new JSONArray();
		movieArray.add(finalJson);
		
		JSONObject finalJson2 = new JSONObject();
		finalJson2.put("movies", movieArray);
		
		return finalJson2;
	}
}
