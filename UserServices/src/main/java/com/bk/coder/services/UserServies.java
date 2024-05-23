package com.bk.coder.services;

import java.util.List;

import com.bk.coder.entities.User;

public interface UserServies {

	//user operation create
	
	User saveuser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId);
	
	/*
	 * //delete
	 * 
	 * void deleteUserId(String userId);
	 * 
	 * //update
	 * 
	 * User updateId(String userLId);
	 */
}
