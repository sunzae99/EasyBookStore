package com.easybookstore.backend.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.easybookstore.backend.dao.UserDao;
import com.easybookstore.backend.enums.authority;
import com.easybookstore.backend.model.Users;
import com.easybookstore.backend.request.UserRequest;
import com.easybookstore.backend.response.BaseUserResponse;
import com.easybookstore.backend.response.UserResponse;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public UserResponse createUser(UserRequest request) { // create new user, save it to database and return the created
															// user as response.
		Users users = new Users();
		UserResponse response = new UserResponse();

		try {
			users.setUserName(request.getName());
			users.setAddress(request.getAddress());
			users.setEmail(request.getEmail());
			users.setRole(authority.user);
			if (request.getPassword().equals(request.getRe_password())) {
				users.setPassword(request.getPassword());
			}
			if (request.getPhone() != "" && request.getPhone() != null)
				users.setPhone(request.getPhone());

			users = userDao.save(users);

			response = usersToResponse(users);
			return response;

		} catch (Exception e) {
			System.out.println("Exception: " + e);

			return response;
		}
	}

	public List<UserResponse> getUsers() { // queries the database and return all the users in the users table in
											// response model
		List<UserResponse> supplied_res = new LinkedList<>();
		try {
			List<Users> response = userDao.findAll();
			for (Users res : response) {
				supplied_res.add(usersToResponse(res));
			}
			return supplied_res;

		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return supplied_res;

		}
	}

	public List<UserResponse> getUsersWithpagination(Integer page_number, Integer page_size) {// returns the number of
																								// user according to
																								// page size and page
																								// number passed.
		List<UserResponse> supplied_res = new LinkedList<>();
		try {
			Page<Users> response = userDao.findAll(PageRequest.of(page_number, page_size));
			for (Users res : response) {
				supplied_res.add(usersToResponse(res));
			}
			return supplied_res;

		} catch (Exception e) {
			System.out.println("Exception: " + e);
			return supplied_res;

		}
	}

	public UserResponse getUser(Long id) { // find user with "id" and return if the user exists, if user does not exists
											// it return empty model.
		UserResponse response = new UserResponse();
		try {
			return usersToResponse(userDao.findByUserId(id));
		} catch (Exception e) {
			System.out.println(e);
			return response;
		}
	}

	public UserResponse getUserByEmail(String email) { // find user with "email" and return if the user exists, if user
														// does not exists it return empty model.
		UserResponse response = new UserResponse();
		try {
			return usersToResponse(userDao.findByEmail(email));
		} catch (Exception e) {
			System.out.println(e);
			return response;
		}
	}

	public UserResponse usersToResponse(Users users) { // changes users object to userResponse object
		UserResponse response = new UserResponse();
		response.setId(users.getUserId());
		response.setName(users.getUserName());
		response.setEmail(users.getEmail());
		response.setAddress(users.getAddress());
		response.setPhone(users.getPhone());
		return response;
	}

	public UserResponse update(Long id, UserRequest request) { // update user password and/or phone and/or address by
																// user id.
		UserResponse response = new UserResponse();
		try {
			if (userDao.existsById(id)) {
				Users users = userDao.findByUserId(id);
				if (request.getPassword() != null && request.getPassword() != "") {
					if (request.getRe_password() != null && request.getRe_password() != "") {
						if (request.getPassword().equals(request.getRe_password())) {
							users.setPassword(request.getPassword());
						}

					}
				}
				if (request.getPhone() != null && request.getPhone() != "") {
					users.setPhone(request.getPhone());
				}
				if (request.getAddress() != null && request.getAddress() != "") {
					users.setAddress(request.getAddress());
				}
				response = usersToResponse(userDao.save(users));
			}

			return response;
		} catch (Exception e) {
			System.out.println(e);
			return response;
		}

	}

	public UserResponse updateByEmail(String email, UserRequest request) { // update user password and/or phone and/or
																			// address by user email.
		UserResponse response = new UserResponse();
		try {
			if (userDao.existsByEmail(email)) {
				Users users = userDao.findByEmail(email);
				if (request.getPassword() != null && request.getPassword() != "") {
					if (request.getRe_password() != null && request.getRe_password() != "") {
						if (request.getPassword().equals(request.getRe_password())) {
							users.setPassword(request.getPassword());
						}

					}
				}
				if (request.getPhone() != null && request.getPhone() != "") {
					users.setPhone(request.getPhone());
				}
				if (request.getAddress() != null && request.getAddress() != "") {
					users.setAddress(request.getAddress());
				}
				response = usersToResponse(userDao.save(users));
			}

			return response;
		} catch (Exception e) {
			System.out.println(e);
			return response;
		}

	}

	public BaseUserResponse delete(Long id) { // deletes user by their id.
		BaseUserResponse response = new BaseUserResponse();
		try {
			if (userDao.existsById(id)) {
				userDao.deleteById(id);
				response.setData(null);
				response.setMessage("User Sucessfully Deleted");
			} else {
				response.setData(null);
				response.setMessage("User with user id: " + id + " not found!!");
			}

			return response;
		} catch (Exception e) {
			System.out.println(e);
			return response;
		}

	}

	public BaseUserResponse deleteByEmail(String email) { // deletes user by their email.
		BaseUserResponse response = new BaseUserResponse();
		try {
			if (userDao.existsByEmail(email)) {
				userDao.deleteByEmail(email);
				response.setData(null);
				response.setMessage("User Sucessfully Deleted");
			} else {
				response.setData(null);
				response.setMessage("User with user email: " + email + " not found!!");
			}

			return response;
		} catch (Exception e) {
			System.out.println(e);
			return response;
		}

	}

}
