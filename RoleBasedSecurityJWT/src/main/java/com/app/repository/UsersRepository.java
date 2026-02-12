package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUserName(String userName);
}
