package com.haythem.atos.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haythem.atos.entity.User;
import com.haythem.atos.exception.BadRequest;
import com.haythem.atos.exception.NotFound;
import com.haythem.atos.repository.UserRepository;
import com.haythem.atos.service.UserService;

/**
 * 
 * @author HJEBEL
 *
 *         This class implements the different methods of the userService.
 *         Service layer.
 *
 */

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<User> findAll() {
		logger.info("Fetch all the Users");
		return repository.findAll();
	}

	@Override
	public User findOne(String id) {
		logger.info("Fetch user by ID : {} ", id);
		return repository.findOneById(id).orElseThrow(() -> new NotFound("User with id " + id + " is not found"));
	}

	@Override
	public User create(User user) {
		logger.info("saving user .....");
		Optional<User> mayExists = repository.findOneByUsername(user.getUsername());
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int birthdayyear = user.getBirthdate().getYear() + 1900;
		int age = currentyear - birthdayyear;
		logger.info("calculate user's age : {} ", age);
		if (mayExists.isPresent())
			throw new BadRequest("User with Username " + user.getUsername() + " already exists");
		if (age < 20)
			throw new BadRequest("This User " + user.getUsername()
					+ "  is not authorised to create an account becouse he is not adult ! ");
		if (user.getCountryOfResidence() == null || !user.getCountryOfResidence().equalsIgnoreCase("france"))
			throw new BadRequest("This User " + user.getUsername()
					+ "  is not authorised to create an account becouse he is not French resident ! ");

		return repository.save(user);
	}

	@Override
	public void delete(String id) {
		User found = repository.findOneById(id).orElseThrow(() -> new NotFound("User with id " + id + "is not found"));
		repository.delete(found);
	}

}
