package com.haythem.atos;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.haythem.atos.entity.User;
import com.haythem.atos.repository.UserRepository;

/**
 * 
 * @author HJEBEL 
 * This class is for integration testing that includes some
 *         testing scenarios.
 */
@ContextConfiguration(classes = TechnicalTestAtosApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * This scenario will be used to test a user based on their id.
	 * @throws Exception
	 */
	@Test
	void verifyUserById() throws Exception {
		User user = new User("haythem", new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-09"), "France",
				"+33 6 01 44 49 20", "MALE");
		User save = userRepository.save(user);

		mockMvc.perform(MockMvcRequestBuilders.get("/users/" + save.getId()).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.username").exists())
				.andExpect(jsonPath("$.birthdate").exists()).andExpect(jsonPath("$.countryOfResidence").exists())
				.andExpect(jsonPath("$.phoneNumber").exists()).andExpect(jsonPath("$.gender").exists())
				.andExpect(jsonPath("$.id").value(save.getId()))
				.andExpect(jsonPath("$.username").value(save.getUsername()))
				.andExpect(jsonPath("$.countryOfResidence").value(save.getCountryOfResidence()))
				.andExpect(jsonPath("$.phoneNumber").value(save.getPhoneNumber()))
				.andExpect(jsonPath("$.gender").value(save.getGender())).andDo(print());
	}

	/**
	 * This scenario will be used to verify the existence of a user.
	 * @throws Exception with status not found.
	 */
	@Test
	void verifyGetUserNotExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/402881937c3290bf017c32ca4536000d")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value())).andDo(print());
	}

	/**
	 * This scenario will be used to save user(success case).
	 * @throws Exception
	 */
	@Test
	void verifySaveUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/").contentType(MediaType.APPLICATION_JSON).content(
				"{\"username\" : \"hachem\", \"birthdate\" : \"2000-08-09\", \"countryOfResidence\" : \"france\" }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.username").exists()).andExpect(jsonPath("$.birthdate").exists())
				.andExpect(jsonPath("$.countryOfResidence").exists()).andExpect(jsonPath("$.username").value("hachem"))
				.andExpect(jsonPath("$.birthdate").value("2000-08-09"))
				.andExpect(jsonPath("$.countryOfResidence").value("france")).andDo(print());
	}

	/**
	 * This scenario will be used to save user(failed case).
	 * @throws Exception
	 */
	@Test
	void verifySavedUserFailed() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/users/").contentType(MediaType.APPLICATION_JSON).content(
				"{\"username\" : \"hajer\", \"birthdate\" : \"2004-08-09\", \"countryOfResidence\" : \"tunisie\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value())).andDo(print());
	}

}
