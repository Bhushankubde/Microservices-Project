package com.bk.coder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bk.coder.entities.User;

public interface UserRepositories extends JpaRepository<User, String>{
	
	
}
