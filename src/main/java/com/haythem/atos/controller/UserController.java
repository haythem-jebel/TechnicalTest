package com.haythem.atos.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haythem.atos.constant.URI;
import com.haythem.atos.entity.User;
import com.haythem.atos.mapper.Mapper;
import com.haythem.atos.service.UserService;
import com.haythem.atos.service.dto.UserDTO;

/**
 * 
 * @author HJEBEL
 * 
 *         This is the controller.
 *
 *         Instead of using the generic spring annotation which is @Controller,
 *         and the annotation @ResponseBody, spring offers @RestController that
 *         includes both @Controller and @ResponseBody.
 * 
 * 
 *         We can add @RequestMapping for specific value at class level as well
 *         so, no need to add at each function.
 * 
 */

@RestController
@RequestMapping(value = URI.USERS)
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;
	
	@Autowired
	private Mapper<User, UserDTO> userMapper;

	public UserController(UserService service) {
		this.service = service;
	}

	/**
	 * This service returns list of users. {@code GET /users} get all the users
	 * 
	 * @return the {@link ResponseEntity} with status {@code 200(ok)} and the list
	 *         of users in body.
	 */
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> findAll() {
		logger.info("Return all the Users");
		List<UserDTO> findAll = service.findAll().stream().map(data ->{
			return userMapper.fromIToO(data);
		}).collect(Collectors.toList());
		return new ResponseEntity<>(findAll, HttpStatus.OK);
	}

	/**
	 * This service returns the user based on his id. {@code GET /users/{id}} get
	 * the id user.
	 * 
	 * @param userId 
	 * @return the {@link ResponseEntity} with status {@code 200(ok)}
	 *               and the user in body or with status {@code 404(Not Found)}.
	 */

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findOne(@PathVariable("id") String userId) {
		logger.info("User id to return {} ", userId);
		UserDTO findOne = userMapper.fromIToO(service.findOne(userId));
		return new ResponseEntity<>(findOne, HttpStatus.OK);
	}

	/**
	 * This service stores the user's information. {@code POST /users/} create a new
	 * user
	 * 
	 * @param user
	 * @return the {@link ResponseEntity} with status {@code 201(created)} and with
	 *         body the new user or with status {@code 400(Bad Request)}if the user
	 *         has already an ID or the user is not authorized to create an account.
	 */

	@PostMapping("/")
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO user) {
		if (user.getId() != null) {
			return ResponseEntity.badRequest().header("X-validation-error", "error.idexists").build();
		}
		User newUser = service.create(userMapper.fromOToI(user));
		UserDTO create = userMapper.fromIToO(newUser) ;
		String userToString = create.toString();
		logger.info("User saved : {} ", userToString);
		return new ResponseEntity<>(create, HttpStatus.CREATED);
	}

	/**
	 * This service deletes the user based on his id. {@code DELETE /users/{id}}
	 * delete user by id.
	 * 
	 * @param id
	 * @param userId
	 * @return the {@link ResponseEntity} with status {@code 200(ok)} or with status
	 *         {@code 404(Not Found)}.
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		logger.info("user id to remove : {}", id);
		service.delete(id);
		return new ResponseEntity<>("user deleted", HttpStatus.OK);
	}
}
