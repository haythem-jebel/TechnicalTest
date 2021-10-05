package com.haythem.atos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haythem.atos.entity.User;
import com.haythem.atos.mapper.Mapper;
import com.haythem.atos.service.dto.UserDTO;

/**
 * 
 * @author HJEBEL
 *
 * This class is for testing the Mapper.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

	@Autowired
	private Mapper<User, UserDTO> userMapper;

	@Test
	void shouldMapUserToDto() throws ParseException {
		// given
		User user = new User("402881937c32fa89017c33c67d7a000c", "haythem",
				new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-09"), "France", "+33 6 01 44 49 20", "MALE");

		// when
		UserDTO userDto = userMapper.fromIToO(user);

		// then
		assertNotNull(userDto, "User DTO must be not null");
		assertEquals(userDto.getUsername(), user.getUsername());
		assertEquals(userDto.getCountryOfResidence(), user.getCountryOfResidence());
	}
}
