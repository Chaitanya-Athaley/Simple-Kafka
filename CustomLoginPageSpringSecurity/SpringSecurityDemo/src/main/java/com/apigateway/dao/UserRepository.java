package com.apigateway.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apigateway.entites.USER;

public interface UserRepository extends JpaRepository<USER, Integer>{

	public USER findByName(String username);
}
