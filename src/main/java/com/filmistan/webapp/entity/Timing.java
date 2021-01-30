package com.filmistan.webapp.entity;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Timing")
public class Timing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "showTime")
	private LocalTime showTime;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "theatre_id")
	private Theatre theatre;
	
	@OneToMany(mappedBy = "timing" , cascade = CascadeType.ALL)
	private Set<Show> shows = new HashSet<>();

	public Timing(LocalTime showTime, Theatre theatre) {
		super();
		this.showTime = showTime;
		this.theatre = theatre;
	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Set<Show> getShows() {
		return shows;
	}



	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}



	public Timing() {}

	public LocalTime getShowTime() {
		return showTime;
	}

	public void setShowTime(LocalTime showTime) {
		this.showTime = showTime;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	
	

}
