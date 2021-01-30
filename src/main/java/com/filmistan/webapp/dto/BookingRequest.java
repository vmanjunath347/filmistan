package com.filmistan.webapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookingRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "ticketCount")
	private int ticketCount;
	
	@Column(name = "showId")
	private long showId;
	
	@Column(name = "username")
	private String username;
	
	
	public long getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BookingRequest(int ticketCount, long showId, String username) {
		super();
		this.ticketCount = ticketCount;
		this.showId = showId;
		this.username = username;
	}

	public BookingRequest() {}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public int getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	
	
}
