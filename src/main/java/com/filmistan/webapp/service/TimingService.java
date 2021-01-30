package com.filmistan.webapp.service;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.dto.TimingRequest;
import com.filmistan.webapp.entity.Theatre;
import com.filmistan.webapp.entity.Timing;
import com.filmistan.webapp.exception.CityNotSpecifiedException;
import com.filmistan.webapp.exception.InvalidInputExcerption;
import com.filmistan.webapp.exception.NotFoundException;
import com.filmistan.webapp.repository.TheatreRepository;
import com.filmistan.webapp.repository.TimingRepository;

@Service
public class TimingService {
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Autowired
	private TimingRepository timingRepository;
	
	
	@SuppressWarnings("unchecked")
	public JSONObject createTiming(TimingRequest timingRequest) {
		
		int hour = timingRequest.getHour();
		int minute = timingRequest.getMinute();
		
		if((hour<0)||(hour>23)||(minute<0)||(minute>59))
			throw new InvalidInputExcerption("Invalid Time");
		
		LocalTime showtime = LocalTime.of(hour, minute);
		System.out.println("hour "+hour);
		System.out.println("min "+minute);
		System.out.println(showtime);
		long theatreId = timingRequest.getTheatreId();
		
		Optional<Theatre> optionalTheatre = theatreRepository.findById(theatreId);
		
		if(!optionalTheatre.isPresent()) {
			throw new NotFoundException("No Theatre with given Id");
		}
		
		Theatre theatre = optionalTheatre.get();
		
		Timing timing = new Timing(showtime, theatre);
		timingRepository.save(timing);
		
		JSONObject outputJson = new JSONObject();
		
		outputJson.put("time", timing.getShowTime());
		outputJson.put("theatre", timing.getTheatre().getName());
		outputJson.put("theatreId", timing.getTheatre().getId());
		outputJson.put("city", timing.getTheatre().getCity().getName());
		outputJson.put("id", timing.getId());
		
		return outputJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAlltimings() {
		List<Timing> timings= timingRepository.findAll();
		
		if(timings.size()<0)
			throw new CityNotSpecifiedException("No Timings in the database");
		
        Iterator<Timing> iterator = timings.iterator();
        
        
        JSONArray tmingJsonArray = new JSONArray();
        
		while(iterator.hasNext()) {
			 Timing tempTiming = iterator.next(); 
			 JSONObject timingJSon = new JSONObject();
			 
			 timingJSon.put("theatre", tempTiming.getTheatre().getName());
			 timingJSon.put("theatreId",tempTiming.getTheatre().getId());
			 timingJSon.put("city", tempTiming.getTheatre().getCity().getName());
			 timingJSon.put("id", tempTiming.getId());
			 timingJSon.put("time", tempTiming.getShowTime());
			 tmingJsonArray.add(timingJSon);
		}
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("timings", tmingJsonArray);
		return outputJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getTimeByID(long id) {
		Optional<Timing> timing = timingRepository.findById(id);
		
		if(!timing.isPresent())
			throw new NotFoundException("No Showtime found by ID");
		
		Timing tempTiming =  timing.get();
		
		JSONObject timingJSon = new JSONObject();
		
		timingJSon.put("theatre", tempTiming.getTheatre().getName());
		timingJSon.put("time", tempTiming.getShowTime());
		
		return timingJSon;
		
	}

	@SuppressWarnings("unchecked")
	public JSONObject editTiming(long id, TimingRequest tempTiming) {
		Optional<Timing> timing = timingRepository.findById(id);
		
		if(!timing.isPresent()) {
		 throw new NotFoundException("There are no showtime by given Id");
		}
		
		Timing foundtiming = timing.get();
		
		int hour = tempTiming.getHour();
		int minute = tempTiming.getMinute();
		

		if((hour<0)||(hour>23)||(minute<0)||(minute>59))
			throw new InvalidInputExcerption("Invalid Time");
		
		LocalTime showtime = LocalTime.of(hour, minute);
		
		foundtiming.setShowTime(showtime);
		
		timingRepository.save(foundtiming);
		
		JSONObject timingJSon = new JSONObject();
		
		timingJSon.put("theatre", foundtiming.getTheatre().getName());
		timingJSon.put("time", foundtiming.getShowTime());
		
		return timingJSon;
	}
	
	public String deleteTiming(long id) {
		Optional<Timing> timing = timingRepository.findById(id);
		
		if(!timing.isPresent()) {
		 throw new NotFoundException("There are no showtime by given Id");
		}
		
		timingRepository.deleteById(id);
		
		return "success";
	}
}

