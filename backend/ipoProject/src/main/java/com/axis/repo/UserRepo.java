package com.axis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axis.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
