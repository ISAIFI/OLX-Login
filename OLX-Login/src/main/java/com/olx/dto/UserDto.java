package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("User Model")
public class UserDto {

	@ApiModelProperty("Unique Identifier of User")
	private int id;
	@ApiModelProperty("Username of User")
	private String username;
	@ApiModelProperty("Password of User")
	private String password;
	@ApiModelProperty("First Name of User")
	private String firstName;
	@ApiModelProperty("Last Name of User")
	private String lastName;
	@ApiModelProperty("Email of User")
	private String email;
	@ApiModelProperty("Mobile no of User")
	private String phone;
	@ApiModelProperty("Role of User")
	private String roles;
	@ApiModelProperty("A User is Active or Inactive")
	private boolean active;
	
	public UserDto() {}

	public UserDto(int id, String username, String password, String firstName, String lastName, String email,
			String phone, String roles, boolean active) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
		this.active = active;
	}
	
	public UserDto(String username, String password, String firstName, String lastName, String email,
			String phone, String roles, boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", roles=" + roles + ", active="
				+ active + "]";
	}

}
