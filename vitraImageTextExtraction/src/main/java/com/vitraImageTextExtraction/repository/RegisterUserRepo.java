package com.vitraImageTextExtraction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitraImageTextExtraction.entities.RegisterUser;

@Repository
public interface RegisterUserRepo extends JpaRepository<RegisterUser, Integer> {
	boolean existsByEmail(String email);

	Optional<RegisterUser> getByEmail(String email);
}
