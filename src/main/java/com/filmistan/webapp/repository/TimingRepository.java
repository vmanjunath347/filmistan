package com.filmistan.webapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.filmistan.webapp.entity.Timing;

@Repository
public interface TimingRepository extends JpaRepository<Timing,Long> {
	
}
