package com.haythem.atos.service.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author HJEBEL
 * This class is the DTO of the class user(entity user).
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
	private String id;
	private String username;
	@NotNull(message = "Birthdate should not be null")
	private String birthdate;
	private String countryOfResidence;
	private String phoneNumber;
	private String gender;

	public UserDTO() {
		super();
	}

	public UserDTO(String username, String birthdate, String countryOfResidence, String phoneNumber, String gender) {
		super();
		this.username = username;
		this.birthdate = birthdate;
		this.countryOfResidence = countryOfResidence;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public UserDTO(String id, String username, String birthdate, String countryOfResidence, String phoneNumber,
			String gender) {
		super();
		this.id = id;
		this.username = username;
		this.birthdate = birthdate;
		this.countryOfResidence = countryOfResidence;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountryOfResidence() {
		return countryOfResidence;
	}

	public void setCountryOfResidence(String countryOfResidence) {
		this.countryOfResidence = countryOfResidence;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
