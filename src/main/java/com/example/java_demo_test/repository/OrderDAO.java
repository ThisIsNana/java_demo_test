package com.example.java_demo_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.Menu;

//�ƾڳX�ݼh
@Repository
public interface OrderDAO extends JpaRepository<Menu, String> {
	
}
