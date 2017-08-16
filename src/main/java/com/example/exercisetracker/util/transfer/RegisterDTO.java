package com.example.exercisetracker.util.transfer;

import com.example.exercisetracker.model.Role;
import com.example.exercisetracker.util.validation.PasswordMatch;
import com.example.exercisetracker.util.validation.StrongPassword;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@PasswordMatch
public class RegisterDTO {
	@NotBlank(message = "Email address cannot be blank")
	@Email(message = "Email address must be in a valid format")
	private String email;
	@NotBlank(message = "First name cannot be blank")
	private String firstName;
	@NotBlank(message = "Last name cannot be blank")
	private String lastName;
	@StrongPassword
	private String password;
	@NotBlank(message = "Password confirmation cannot be blank")
	private String passwordConfirm;
	private List<Role> roles;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
