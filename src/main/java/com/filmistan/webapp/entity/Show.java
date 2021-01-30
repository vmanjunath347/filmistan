package com.filmistan.webapp.entity;

import java.time.LocalDate;
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
@Table(name = "MovieShows")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "totalSeats")
	private int totalSeats;
	
	@Column(name = "bookedSeats")
	private int bookedSeats;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@Column(name = "showDate")
	private LocalDate showDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "timing_id")
	private Timing timing;
	
	@OneToMany(mappedBy = "show" , cascade = CascadeType.ALL)
	private Set<Bookings> bookings = new HashSet<>();
	
	
	
	public Show(int totalSeats, int bookedSeats, Movie movie, LocalDate showDate, Timing timing,
			Set<Bookings> bookings) {
		super();
		this.totalSeats = totalSeats;
		this.bookedSeats = bookedSeats;
		this.movie = movie;
		this.showDate = showDate;
		this.timing = timing;
		this.bookings = bookings;
	}

	public Show() {}

	public Set<Bookings> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Bookings> bookings) {
		this.bookings = bookings;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public Timing getTiming() {
		return timing;
	}

	public void setTiming(Timing timing) {
		this.timing = timing;
	}
	
	

}
