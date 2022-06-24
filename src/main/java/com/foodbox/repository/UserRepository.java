package com.foodbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.foodbox.model.User;

@Component
public interface UserRepository extends JpaRepository <User, Integer> {
	
	public User findByUsername(String username);
	
	public User findByUserId(int userId);
	

}
