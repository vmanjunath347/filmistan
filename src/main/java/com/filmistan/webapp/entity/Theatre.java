package com.filmistan.webapp.entity;

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
@Table(name = "Theatre")
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "seatCount")
	private int seatCount;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "city_id")
	private City city;

	@OneToMany(mappedBy = "theatre" , cascade = CascadeType.ALL)
	private Set<Timing> showTimes = new HashSet<>();
	
	public Set<Timing> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(Set<Timing> showTimes) {
		this.showTimes = showTimes;
	}
	

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Theatre() {}

	public Theatre(String name, int seatCount, City city) {
		super();
		this.name = name;
		this.seatCount = seatCount;
		this.city = city;
	}

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
