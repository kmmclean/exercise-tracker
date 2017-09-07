package com.example.exercisetracker.service;

import com.example.exercisetracker.exception.AccountExistsException;
import com.example.exercisetracker.model.User;
import com.example.exercisetracker.model.VerificationToken;
import com.example.exercisetracker.repository.UserRepository;
import com.example.exercisetracker.util.transfer.RegisterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private MailService mailService;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.mailService = mailService;
	}

	public Optional<User> findUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}

	public User registerUser(RegisterDTO registerDTO) throws AccountExistsException {

		if (this.userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
			throw new AccountExistsException(String.format("An account is already registered for %s", registerDTO.getEmail()));
		}

		User user = new User();
		user.setEmail(registerDTO.getEmail());
		user.setPassword(this.passwordEncoder.encode(registerDTO.getPassword()));
		user.setFirstName(registerDTO.getFirstName());
		user.setLastName(registerDTO.getLastName());
		user.setVerified(false);
		user.setActive(false);
		user.setRoles(registerDTO.getRoles());

		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setUser(user);
		verificationToken.setValue(UUID.randomUUID());
		verificationToken.setExpires(LocalDateTime.now().plusHours(1));

		user.setVerificationToken(verificationToken);

		// Send the user a verification email address.
		this.mailService.sendAccountVerification(user);

		return this.userRepository.save(user);
	}

	public Optional<User> verifyUser(UUID token) {
		// Delete all of the expired tokens before checking.
		this.userRepository.deleteByVerificationTokenExpiresBefore(LocalDateTime.now());

		Optional<User> user = this.userRepository.findByVerificationTokenValueAndVerificationTokenExpiresAfter(
			token, LocalDateTime.now());

		user.ifPresent(u -> {
			u.setActive(true);
			u.setVerified(true);
			u.setAccountExpiration(LocalDateTime.now().plusDays(90));
			u.setPasswordExpiration(LocalDateTime.now().plusDays(60));
			u.getVerificationToken().setUser(null);
			u.setVerificationToken(null);
			this.userRepository.save(u);
		});

		return user;
	}
}
