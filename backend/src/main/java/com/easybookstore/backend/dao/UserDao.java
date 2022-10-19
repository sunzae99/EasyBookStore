package com.easybookstore.backend.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.easybookstore.backend.model.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Long>{
	Users findByUserId(Long id); 
	Users findByEmail(String email);
	boolean existsByEmail(String email);
	void deleteByEmail(String email);
	
}


