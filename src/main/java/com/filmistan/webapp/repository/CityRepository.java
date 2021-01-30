package com.filmistan.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmistan.webapp.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
