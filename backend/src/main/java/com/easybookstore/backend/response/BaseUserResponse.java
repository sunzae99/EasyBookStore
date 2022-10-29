package com.easybookstore.backend.response;

import lombok.Data;

@Data
public class BaseUserResponse {
	private UserResponse data;
	private String Message;

}
