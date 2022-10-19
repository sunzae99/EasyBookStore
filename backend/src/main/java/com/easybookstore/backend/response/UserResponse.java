package com.easybookstore.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String address;
}
