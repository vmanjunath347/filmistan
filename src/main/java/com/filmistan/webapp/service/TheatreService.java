package com.filmistan.webapp.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.dto.Theatrerequest;
import com.filmistan.webapp.entity.City;
import com.filmistan.webapp.entity.Theatre;
import com.filmistan.webapp.exception.CityNotSpecifiedException;
import com.filmistan.webapp.exception.NotFoundException;
import com.filmistan.webapp.repository.CityRepository;
import com.filmistan.webapp.repository.TheatreRepository;

@Service
public class TheatreService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@SuppressWarnings("unchecked")
	public JSONObject addTheatre(Theatrerequest theatreRequest) {
		String theatreName = theatreRequest.getName();
		int seatCount = theatreRequest.getSeatCount();
		long cityId = theatreRequest.getCityId();
		
		Optional<City> city = cityRepository.findById(cityId);
		
		if (!city.isPresent()) {
            throw new NotFoundException("No City with given Id");
        }
		
		City savedCity = city.get();

        Theatre theatre = new Theatre(theatreName, seatCount, savedCity);

		theatreRepository.save(theatre);
		
		JSONObject outputJson = new JSONObject();
		outputJson.put("name", theatreName);
		outputJson.put("seat count", seatCount);
		outputJson.put("city", savedCity.getName());
		
		return outputJson; 
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllTheatres() {
		List<Theatre> theatres= theatreRepository.findAll();
		
		if(theatres.size()<0)
			throw new CityNotSpecifiedException("No theatres in the database");
		
        Iterator<Theatre> iterator = theatres.iterator();
        
        JSONArray theatreArray = new JSONArray();
        		
		while(iterator.hasNext()) {
			 Theatre tempTheatre = iterator.next(); 
			 JSONObject tempTheatreJSon = new JSONObject();
			 tempTheatreJSon.put("id", tempTheatre.getId());
			 tempTheatreJSon.put("name", tempTheatre.getName());
			 tempTheatreJSon.put("seat count", tempTheatre.getSeatCount());
			 tempTheatreJSon.put("city", tempTheatre.getCity().getName());
			 
			 theatreArray.add(tempTheatreJSon);
		}
		JSONObject outputJson = new JSONObject();
		outputJson.put("theatres", theatreArray);
		
		return outputJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getTheatreById(long id) {
		Optional<Theatre> theatre = theatreRepository.findById(id);
		
		if(!theatre.isPresent())
			throw new NotFoundException("No City found by ID");
		
		JSONObject tempTheatreJSon = new JSONObject();
		Theatre tempTheatre =  theatre.get();
		tempTheatreJSon.put("name", tempTheatre.getName());
		tempTheatreJSon.put("seat count", tempTheatre.getSeatCount());
		tempTheatreJSon.put("city", tempTheatre.getCity().getName());
		return tempTheatreJSon;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject editTheatreById(Theatre tempTheatre,long id) {
		Optional<Theatre> theatre = theatreRepository.findById(id);
		
		if(!theatre.isPresent()) {
		 throw new NotFoundException("There are no theatre by given Id");
		}
		
		Theatre foundTheatre = theatre.get();
		
		String name = tempTheatre.getName();
		int seatCount = tempTheatre.getSeatCount();
		
		foundTheatre.setName(name);
		foundTheatre.setSeatCount(seatCount);
		
		theatreRepository.save(foundTheatre);
		JSONObject tempTheatreJSon = new JSONObject();
		tempTheatreJSon.put("name", foundTheatre.getName());
		tempTheatreJSon.put("seat count", foundTheatre.getSeatCount());
		tempTheatreJSon.put("city", foundTheatre.getCity().getName());
		return tempTheatreJSon;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject deleteTheatreById(long id) {
		
		Optional<Theatre> theatre = theatreRepository.findById(id);
		
		if(!theatre.isPresent()) {
		 throw new NotFoundException("There are no theatre by given Id");
		}
		
		Theatre foundTheatre = theatre.get();
		
		theatreRepository.delete(foundTheatre);
		JSONObject tempTheatreJSon = new JSONObject();
		tempTheatreJSon.put("name", foundTheatre.getName());
		tempTheatreJSon.put("seat count", foundTheatre.getSeatCount());
		tempTheatreJSon.put("city", foundTheatre.getCity().getName());
		return tempTheatreJSon;
	}
	
	
}
