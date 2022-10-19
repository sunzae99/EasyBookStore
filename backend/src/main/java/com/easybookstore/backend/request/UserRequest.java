package com.easybookstore.backend.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	private String name;
	private String email;
	private String password;
	private String re_password;
	private String phone;
	private String address;

}
