package com.example.java_demo_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_demo_test.entity.Users;

public interface UserDAO extends JpaRepository<Users,Integer> {
	
	
	
}
