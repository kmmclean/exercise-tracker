package com.example.exercisetracker.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;
	private LocalDateTime accountExpiration;
	private LocalDateTime passwordExpiration;
	private Boolean verified;
	private Boolean active;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private VerificationToken verificationToken;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Role> getRoles() {
		return roles;
	}

	public LocalDateTime getAccountExpiration() {
		return accountExpiration;
	}

	public void setAccountExpiration(LocalDateTime accountExpiration) {
		this.accountExpiration = accountExpiration;
	}

	public LocalDateTime getPasswordExpiration() {
		return passwordExpiration;
	}

	public void setPasswordExpiration(LocalDateTime passwordExpiration) {
		this.passwordExpiration = passwordExpiration;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public VerificationToken getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(VerificationToken verificationToken) {
		this.verificationToken = verificationToken;
	}

	public String getFullName() {
		return String.format("%s %s", this.firstName, this.lastName);
	}
}
