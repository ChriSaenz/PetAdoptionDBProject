package com.sprinboot.backend.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.backend.model.UserProfile;

public interface UserRepository extends JpaRepository<UserProfile, Long> {
	@Query("select u from UserProfile u where u.username = ?1")
	Optional<UserProfile> getByUsername(String id);

	@Transactional
	@Modifying
	@Query("update UserProfile u "
		 + "set u.nickname = ?2, u.securityAnswer = ?3, u.securityQuestion = ?4 "
		 + "where u.username = ?1")
	void updateProfile(String username, String nickname, String securityAnswer, String securityQuestion);

	@Transactional
	@Modifying
	@Query("update UserProfile u "
		 + "set u.password = ?2, u.passwordLastReset = ?3 "
		 + "where u.username = ?1")
	void resetPassword(String username, String encode, LocalDate now);
	
	
}
