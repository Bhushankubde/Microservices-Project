package com.bk.coder.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bk.coder.entities.Hotel;
import com.bk.coder.entities.Rating;
import com.bk.coder.entities.User;
import com.bk.coder.exception.ResourceNotFoundException;
import com.bk.coder.repositories.UserRepositories;
import com.bk.coder.services.UserServies;

@Service
public class UserServicesImpl implements UserServies {

	@Autowired
	private UserRepositories userRepositories;
	
	
	  @Autowired
	  private RestTemplate restTemplate;
	 
	
	@Override
	public User saveuser(User user) {
		//generate unique id
		 
		String randomuserId = UUID.randomUUID().toString();
		user.setUserId(randomuserId);
		return userRepositories.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepositories.findAll();
	}

	@Override
	public User getUser(String userId) {
	
		User user = userRepositories.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found on server!!"+userId));
	   
		//USER TO CALLING RATING API
	   //http://localhost:8083/rating/users/6fdf3db3-9b5a-4570-842a-bee9d88dee21
	
	   Rating[] RObject = restTemplate.getForObject(
				/* "http://localhost:8083/rating/users/"+user.getUserId(), */
	  //we can change host name
			   "http://RATING-SERVICE/rating/users/"+user.getUserId(),
	  Rating[].class); 
	  
	  
	  //RATING TO CALLLING HOTEL API
	  //http://localhost:8082/hotels/4922a33c-2cf1-4a1f-a668-1116d33070d9
	  List<Rating> ratings = Arrays.stream(RObject).toList();
	  
	  List<Rating> ratingList =  ratings.stream().map(rating->{
		  System.out.println(rating.getHotelId());
		 ResponseEntity<Hotel> forEntity =  restTemplate.getForEntity(
			"http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
	           Hotel hotel = forEntity.getBody();
	           
	           rating.setHotel(hotel);
		 
		       return rating;
	  }).collect(Collectors.toList());
	 
	  
	  user.setRatings(ratingList);	
		
		return user;
	}

	/*@Override
	public void deleteUserId(String userId) {
		
		userRepositories.deleteById(userId);
		
	}

	@Override
	public User updateId(String userLId) {
		
		 User user = userRepositories.findById(userLId).get();
		 user.setName(userLId);
		 user.setEmail(userLId);
         user.setAbout(userLId);
  
         return this.userRepositories.save(user);
		
	} */

}
