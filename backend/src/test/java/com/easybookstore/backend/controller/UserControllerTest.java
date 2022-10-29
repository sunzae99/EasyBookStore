package com.easybookstore.backend.controller;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.easybookstore.backend.request.UserRequest;
import com.easybookstore.backend.response.UserResponse;
import com.easybookstore.backend.service.UserService;

@RunWith(SpringRunner.class)
@SuppressWarnings("unused")
public class UserControllerTest {

	@InjectMocks
	UserController controller;

	@Mock
	UserService service;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	private UserRequest setCustomer() {
		UserRequest request = new UserRequest();
		request.setName("Jhon Doe");
		request.setEmail("jhondoe@gmail.com");
		request.setPassword("Password123");
		request.setRe_password("Password123");
		request.setPhone("1231231234");
		request.setAddress("123 Home");
		return request;
	}

	private UserResponse getReponse() {
		UserResponse response = new UserResponse();
		response.setName("Jhon Doe");
		response.setEmail("jhondoe@gmail.com");
		response.setPhone("1231231234");
		response.setAddress("123 Home");
		response.setId(123L);
		return response;
	}

	private UserResponse getEmptyResponse() {
		UserResponse response = new UserResponse();
		return response;
	}

	@Test
	public void getUsersTest() {
		List<UserResponse> responseList = new ArrayList<>();
		responseList.add(getReponse());
		Mockito.when(service.getUsers()).thenReturn(responseList);
		ResponseEntity<List<UserResponse>> finalResponse = controller.getUsers();
		assertNotNull(finalResponse);
	}

	@Test
	public void getUsersExceptionTest() {
		List<UserResponse> responseList = new ArrayList<>();
		Mockito.when(service.getUsers()).thenReturn(responseList);
		ResponseEntity<List<UserResponse>> finalResponse = controller.getUsers();
		assertNotNull(finalResponse);
	}

}
