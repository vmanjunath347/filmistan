package com.filmistan.webapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Theatrerequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long Id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "seatCount")
	int seatCount;
	
	@Column(name = "cityId")
	long cityId;
	
	
	public Theatrerequest(String name, int seatCount, long cityId) {
		super();
		this.name = name;
		this.seatCount = seatCount;
		this.cityId = cityId;
	}
	
	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}



	public Theatrerequest() {}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	
	
	
}
