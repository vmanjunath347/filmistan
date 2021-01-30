package com.filmistan.webapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimingRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "hour")
	private int hour;
	
	@Column(name = "minute")
	private int minute;
	
	@Column(name = "theatreId")
	private long theatreId;
	
	public TimingRequest() {}

	public TimingRequest(int hour, int minute, long theatreId) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.theatreId = theatreId;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(long theatreId) {
		this.theatreId = theatreId;
	}
	
	
	

}
