package com.filmistan.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmistan.webapp.entity.Bookings;

@Repository
public interface BookingRepository  extends JpaRepository<Bookings, Long>{

}
