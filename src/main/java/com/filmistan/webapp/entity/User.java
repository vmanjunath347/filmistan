package com.filmistan.webapp.entity;

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
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	
	@Column(name = "role")
	private String role;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private Set<Bookings> bookings = new HashSet<>();

	

	public User(String username, String password, String name, String role, Set<Bookings> bookings) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.role = role;
		this.bookings = bookings;
	}



	public String getName() {
		return name;
	}



	public Set<Bookings> getBookings() {
		return bookings;
	}



	public void setBookings(Set<Bookings> bookings) {
		this.bookings = bookings;
	}



	public void setName(String name) {
		this.name = name;
	}



	public User() {}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



}
