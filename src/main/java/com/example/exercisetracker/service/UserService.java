package com.example.exercisetracker.service;

import com.example.exercisetracker.model.User;
import com.example.exercisetracker.repository.UserRepository;
import com.example.exercisetracker.util.transfer.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Optional<User> findUserByEmail(String email) {
		return this.userRepository.findUserByEmail(email);
	}

	public User saveUser(RegisterDTO registerDTO) {
		User user = new User();
		user.setEmail(registerDTO.getEmail());
		user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
		user.setFirstName(registerDTO.getFirstName());
		user.setLastName(registerDTO.getLastName());
		user.setRoles(registerDTO.getRoles());

		return this.userRepository.save(user);
	}
}
