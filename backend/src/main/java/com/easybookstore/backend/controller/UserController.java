package com.easybookstore.backend.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easybookstore.backend.request.UserRequest;
import com.easybookstore.backend.response.BaseUserResponse;
import com.easybookstore.backend.response.UserResponse;
import com.easybookstore.backend.service.UserService;

@RestController
@RequestMapping(value = "/easybook/v1/api")
@CrossOrigin(origins = "http://localhost:3000") // CORS access for the Front-end
public class UserController {
	@Autowired
	UserService service;

	@GetMapping("/users")
	public ResponseEntity<List<UserResponse>> getUsers() {
		List<UserResponse> userList = new LinkedList<>();
		userList = service.getUsers();
		if (userList.isEmpty()) {
			return new ResponseEntity<>(userList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
		UserResponse response = service.createUser(request);
		if (response.getId() == null) {
			return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
		UserResponse response = service.getUser(id);
		if (response.getId() == null) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/users/email/{email}")
	public ResponseEntity<UserResponse> getUser(@PathVariable("email") String email) {
		UserResponse response = service.getUserByEmail(email);
		if (response.getId() == null) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/users/paged")
	public ResponseEntity<List<UserResponse>> getUsersPaged(@RequestParam("page_number") Integer page_number,
			@RequestParam("page_size") Integer page_size) {
		List<UserResponse> userList = service.getUsersWithpagination(page_number, page_size);
		if (userList.isEmpty()) {
			return new ResponseEntity<>(userList, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable("id") Long id, @RequestBody UserRequest request) {
		UserResponse response = service.update(id, request);
		if (response.getId() == null) {
			return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping("/users/email/{email}")
	public ResponseEntity<UserResponse> update(@PathVariable("email") String email, @RequestBody UserRequest request) {
		UserResponse response = service.updateByEmail(email, request);
		if (response.getId() == null) {
			return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<BaseUserResponse> update(@PathVariable("id") Long id) {
		BaseUserResponse response = service.delete(id);
		if (!response.getMessage().equals("User Sucessfully Deleted")) {
			return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/users/email/{email}")
	public ResponseEntity<BaseUserResponse> update(@PathVariable("email") String email) {
		BaseUserResponse response = service.deleteByEmail(email);
		if (!response.getMessage().equals("User Sucessfully Deleted")) {
			return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
		}
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

}
