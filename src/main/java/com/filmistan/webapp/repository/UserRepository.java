package com.filmistan.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmistan.webapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
