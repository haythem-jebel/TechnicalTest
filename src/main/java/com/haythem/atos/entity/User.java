package com.haythem.atos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author HJEBEL
 * 
 *         This model of user entity. I can use lombok with the lines comments.
 * 
 * @Data
 * @AllArgsConstructor
 * @NoArgsConstructor
 *
 */
@Table(name = "user")
@Entity
public class User {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@NotEmpty(message = "Username should not be empty")
	@Column(unique = true, nullable = false)
	private String username;

	/**
	 * Format date YYYY-MM-DD
	 */
	
	@NotNull(message = "Birthdate should not be null")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date birthdate;

	@NotEmpty(message = "Country Of Residence should not be empty")
	@NotNull(message = "Country Of Residence should not be null")
	private String countryOfResidence;

	/**
	 * example phone number = +33 6 01 44 49 20
	 */
	
	@Pattern(regexp = "^(0|\\+33 )[1-9]([-. ]?[0-9]{2} ){3}([-. ]?[0-9]{2})$")
	@Column(nullable = true)
	private String phoneNumber;

	private String gender;

	public User() {
		
	}

	public User(String username, Date birthdate, String countryOfResidence, String phoneNumber, String gender) {
		super();
		this.username = username;
		this.birthdate = birthdate;
		this.countryOfResidence = countryOfResidence;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public User(String id, String username, Date date, String countryOfResidence, String phoneNumber, String gender) {
		super();
		this.id = id;
		this.username = username;
		this.birthdate = date;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthdate=" + birthdate + ", countryOfResidence="
				+ countryOfResidence + ", phoneNumber=" + phoneNumber + ", gender=" + gender + "]";
	}

}
