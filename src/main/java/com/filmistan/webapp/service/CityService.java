package com.filmistan.webapp.service;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmistan.webapp.entity.City;
import com.filmistan.webapp.entity.Theatre;
import com.filmistan.webapp.exception.CityNotSpecifiedException;
import com.filmistan.webapp.exception.NotFoundException;
import com.filmistan.webapp.exception.UserAlreadyExistsException;
import com.filmistan.webapp.repository.CityRepository;
import com.filmistan.webapp.repository.TheatreRepository;

@Service
public class CityService {
	
	@Autowired 
	private CityRepository cityRepository;
	
	@Autowired 
	private TheatreRepository theatreRepository;
	
	
	
	public City addCity(City city) {
		String cityName = city.getName();
		List<City> cities= cityRepository.findAll();
		
		ListIterator<City> iterator = cities.listIterator();
		
		while(iterator.hasNext()) {
		 City tempCity = iterator.next();
		 String tempCityName = tempCity.getName();
		 
		 if(tempCityName.equals(cityName))
			 throw new UserAlreadyExistsException("This city already exists in Database");
		}
		
		return cityRepository.save(city);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getCity(Long id) {
		Optional<City> city = cityRepository.findById(id);
		
		if(!city.isPresent())
			throw new NotFoundException("No City found by ID");
		
		City tempCity = city.get();
		
		JSONObject cityJson  = new JSONObject();
		
		JSONArray thearesJson = new JSONArray();
		
		
		List<Theatre> theatres = theatreRepository.findAll();
	
		 String tempCityName = tempCity.getName();
		 
		 for(Theatre theatre : theatres) {
				if(theatre.getCity() == tempCity) {
					thearesJson.add(theatre.getName());
					
				}
			}
		 cityJson.put(tempCityName, thearesJson);
	
		
		return cityJson;
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getAllCity() {
		List<City> cities= cityRepository.findAll();
		List<Theatre> theatres = theatreRepository.findAll();
		
		if(cities.size()<0)
			throw new CityNotSpecifiedException("No Cities in the database");
		
        Iterator<City> iterator = cities.iterator();
        
        JSONObject cityJson  = new JSONObject();
        
		while(iterator.hasNext()) {
			 City tempCity = iterator.next(); 
			 String tempCityName = tempCity.getName();
			 long cityId = tempCity.getId();
			 JSONArray thearesJson = new JSONArray();
			 
			 for(Theatre theatre : theatres) {
					if(theatre.getCity() == tempCity) {
						thearesJson.add(theatre.getName());
						
					}
				}
			 JSONObject thearesJson2 = new JSONObject();
			 thearesJson2.put("theatres", thearesJson);
			 thearesJson2.put("id", cityId);
			 cityJson.put(tempCityName, thearesJson2);
		}
		
		return cityJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject editCity(long id, City tempcity) {
		Optional<City> city = cityRepository.findById(id);
		
		if(!city.isPresent()) {
		 throw new NotFoundException("There are no city by given Id");
		}
		
		City foundCity = city.get();
		
		String name = tempcity.getName();
		foundCity.setName(name);
		
		cityRepository.save(foundCity);
		
		
		JSONObject cityJson  = new JSONObject();
		
		JSONArray thearesJson = new JSONArray();
		
		
		List<Theatre> theatres = theatreRepository.findAll();
		 
		 for(Theatre theatre : theatres) {
				if(theatre.getCity() == foundCity) {
					thearesJson.add(theatre.getName());
					
				}
			}
		 cityJson.put(name, thearesJson);
		 
		 return cityJson;
	}
	
	public String deleteCity(long id) {
		Optional<City> city = cityRepository.findById(id);
		
		if(!city.isPresent())
			throw new NotFoundException("No City found by ID");
		
		cityRepository.delete(city.get());
		return "success";
	}
	
}
