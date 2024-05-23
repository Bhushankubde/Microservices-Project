package com.bk.coder.entities;

public class Rating {

	private String ratingId;
	private String hotelId;
	private int rating;
	private String userId;
	private String feedback;
	
	private Hotel hotel;
	
	public Rating() {
		
	}
	
	public Rating(String hotelId) {
		
		this.hotelId = hotelId;
	}

	public Rating(String ratingId, String hotelId, int rating, String userId, String feedback) {
	
		this.ratingId = ratingId;
		this.hotelId = hotelId;
		this.rating = rating;
		this.userId = userId;
		this.feedback = feedback;
	}

	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
