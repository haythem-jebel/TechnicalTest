package com.haythem.atos.mapper.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.haythem.atos.entity.User;
import com.haythem.atos.exception.BadRequest;
import com.haythem.atos.mapper.Mapper;
import com.haythem.atos.service.dto.UserDTO;

/**
 * 
 * @author HJEBEL
 * This class implements the class Mapper.
 *
 */
@Component
public class UserMapper implements Mapper<User, UserDTO> {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	String dateStr = null;
	Date date = null;

	/**
	 * Transform user to user DTO.
	 */

	@Override
	public UserDTO fromIToO(User i) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(i.getId());
		userDTO.setUsername(i.getUsername());
		dateStr = formatter.format(i.getBirthdate());
		userDTO.setBirthdate(dateStr);
		userDTO.setCountryOfResidence(i.getCountryOfResidence());
		userDTO.setPhoneNumber(i.getPhoneNumber());
		userDTO.setGender(i.getGender());
		return userDTO;
	}

	/**
	 * Transform user DTO to user.
	 */

	@Override
	public User fromOToI(UserDTO i) {
		User user = new User();
		user.setId(i.getId());
		if (i.getUsername() == null || i.getUsername().isEmpty()) {
			throw new BadRequest("username should be not empty and not null ");
		} else {
			user.setUsername(i.getUsername());
		}
		try {
			date = formatter.parse(i.getBirthdate());
		} catch (ParseException e) {
			throw new BadRequest("Birthdate format should like YYYY-MM-DD");
		}
		user.setBirthdate(date);
		user.setCountryOfResidence(i.getCountryOfResidence());
		user.setPhoneNumber(i.getPhoneNumber());
		user.setGender(i.getGender());
		return user;
	}

}
