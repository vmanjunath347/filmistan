package com.filmistan.webapp.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "fromDate")
	private LocalDate fromDate;
	
	@Column(name = "toDate")
	private LocalDate toDate;
	
	@OneToMany(mappedBy = "movie" , cascade = CascadeType.ALL)
	private Set<Show> shows = new HashSet<>();

	
	
	public Movie(String name, LocalDate fromDate, LocalDate toDate, Set<Show> shows) {
		super();
		this.name = name;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.shows = shows;
	}
	

	public Movie() {}
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public Set<Show> getShows() {
		return shows;
	}

	public void setShows(Set<Show> shows) {
		this.shows = shows;
	}
	
	
	
}
