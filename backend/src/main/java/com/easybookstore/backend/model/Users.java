package com.easybookstore.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.easybookstore.backend.enums.authority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  userId;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(name = "email", nullable = false, unique = true, length = 30)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	private String phone;
	
	@Column(nullable=false)
	private String address;
	
	@Column(nullable=false)
	private authority role;
	
}
