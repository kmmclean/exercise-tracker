package com.example.exercisetracker.repository;

import com.example.exercisetracker.model.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByVerificationTokenValueAndVerificationTokenExpiresAfter(UUID token, LocalDateTime now);

	Long deleteByVerificationTokenExpiresBefore(LocalDateTime now);
}
