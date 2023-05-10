package com.example.java_demo_test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.entity.NewMenu2Id;

public interface NewMenu2DAO extends JpaRepository<NewMenu2, NewMenu2Id> {

}
