package com.filmistan.webapp.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class MovieRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "startDay")
	private int startDay;
	
	@Column(name = "startMonth")
	private int startMonth;
	
	@Column(name = "startYear")
	private int startYear;
	
	@Column(name = "endDay")
	private int endDay;
	
	@Column(name = "endMonth")
	private int endMonth;
	
	@Column(name = "endYear")
	private int endYear;
	
	@Column(name = "timingId")
	@ElementCollection(targetClass = Long.class)
	private List<Long> timingId;


	public MovieRequest(String name, int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear,
			List<Long> timingId) {
		super();
		this.name = name;
		this.startDay = startDay;
		this.startMonth = startMonth;
		this.startYear = startYear;
		this.endDay = endDay;
		this.endMonth = endMonth;
		this.endYear = endYear;
		this.timingId = timingId;
	}



	public MovieRequest() {}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getStartDay() {
		return startDay;
	}



	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}



	public int getStartMonth() {
		return startMonth;
	}



	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}



	public int getStartYear() {
		return startYear;
	}



	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}



	public int getEndDay() {
		return endDay;
	}



	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}



	public int getEndMonth() {
		return endMonth;
	}



	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}



	public int getEndYear() {
		return endYear;
	}



	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}



	public List<Long> getTimingId() {
		return timingId;
	}



	public void setTimingId(List<Long> timingId) {
		this.timingId = timingId;
	}


	
	
	
	
}
