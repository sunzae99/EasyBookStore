package com.easybookstore.backend.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybookstore.backend.dao.UserDao;
import com.easybookstore.backend.model.Users;
import com.easybookstore.backend.request.UserRequest;
import com.easybookstore.backend.response.UserResponse;


@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public  UserResponse createUser(UserRequest request) {
		Users users = new Users();
		UserResponse response = new UserResponse();

		try {
			users.setUserName(request.getName());
			users.setAddress(request.getAddress());
			users.setEmail(request.getEmail());
			if(request.getPassword().equals(request.getRe_password())) {
				users.setPassword(request.getPassword());
			}
			if(request.getPhone() != "" && request.getPhone() != null)
				users.setPhone(request.getPhone());
			
			users = userDao.save(users);
			
			response = usersToResponse(users);
			return response;
			
		}catch (Exception e) {
			System.out.println("Exception: "+ e);
			
			return  response;
		}
	}
	
	public List<Users> getUsers() {
		List<Users> response = new LinkedList<>();
		try {
			response = userDao.findAll();
			return  response;
			
		}catch(Exception e) {
			System.out.println("Exception: "+ e);
			return  response;
			
		}	
	}
	
	public UserResponse usersToResponse(Users users) {
		UserResponse response = new UserResponse();
		response.setId(users.getUserId());
		response.setName(users.getUserName());
		response.setEmail(users.getEmail());
		response.setAddress(users.getAddress());
		response.setPhone(users.getPhone());
		return  response;
	}
	
	

}
