package com.example.exercisetracker.security;

import com.example.exercisetracker.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
	private User user;

	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.getAccountExpiration().isAfter(LocalDateTime.now());
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.getActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.getPasswordExpiration().isAfter(LocalDateTime.now());
	}

	@Override
	public boolean isEnabled() {
		return this.user.getVerified();
	}

	public Long getId() {
		return this.user.getId();
	}

	public String getName() {
		return this.user.getFullName();
	}
}
