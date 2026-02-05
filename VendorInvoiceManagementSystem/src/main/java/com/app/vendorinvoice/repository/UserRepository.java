package com.app.vendorinvoice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.vendorinvoice.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String username);
}
