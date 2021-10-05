package com.haythem.atos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.haythem.atos.entity.User;
import com.haythem.atos.exception.BadRequest;
import com.haythem.atos.repository.UserRepository;
import com.haythem.atos.service.impl.UserServiceImpl;

/**
 * 
 * @author HJEBEL
 *
 *         This class is for Unit tests that includes some scenarios.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * This scenario will be used to test a user based on their id (success case).
	 * @throws ParseException
	 */
	@Test
	void testGetUserByIdSuccessTest() throws ParseException {
		User user = new User("402881937c32fa89017c33c67d7a000c", "haythem",
				new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-09"), "France", "+33 6 01 44 49 20", "MALE");
		when(userRepository.findOneById("402881937c32fa89017c33c67d7a000c")).thenReturn(Optional.of(user));
		Optional<User> resultOpt = Optional.ofNullable(userService.findOne("402881937c32fa89017c33c67d7a000c"));
		User result = resultOpt.get();
		assertEquals("402881937c32fa89017c33c67d7a000c", result.getId());
		assertEquals("haythem", result.getUsername());
		assertNotNull(result.getUsername(), "username is required");
		assertEquals(true, result.getCountryOfResidence().toLowerCase().equals("france"));
	}

	/**
	 * This scenario will be used to save user Not Autorised (failed case).
	 * @throws ParseException
	 */
	@Test
	void TestsaveUserNotAutorisedTest() throws ParseException {
		User user = new User("402881937c32fa89017c33c67d7a000c", "haythem",
				new SimpleDateFormat("yyyy-MM-dd").parse("2004-08-09"), "tunisie", "+33 6 01 44 49 20", "MALE");
		when(userRepository.save(user)).thenReturn(user);
		assertThrows(BadRequest.class, () -> userService.create(user),
				"This User " + user.getUsername() + "  has not authorised to create an account! ");
	}

	/**
	 * This scenario will be used to save user (success case).
	 * @throws ParseException
	 */
	@Test
	void TestsaveUserSuccessTest() throws ParseException {
		User user = new User("402881937c32fa89017c33c67d7a000c", "haythem",
				new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-09"), "France", "+33 6 01 44 49 20", "MALE");
		when(userRepository.save(user)).thenReturn(user);
		User result = userService.create(user);
		assertEquals("402881937c32fa89017c33c67d7a000c", result.getId());
		assertEquals("haythem", result.getUsername());
		assertEquals(true, result.getCountryOfResidence().toLowerCase().equals("france"));
	}

}
