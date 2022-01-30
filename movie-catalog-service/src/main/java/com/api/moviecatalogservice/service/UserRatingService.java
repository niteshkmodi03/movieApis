package com.api.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.api.moviecatalogservice.model.CatalogueItem;
import com.api.moviecatalogservice.model.Rating;
import com.api.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getUserFallbackRating")
	public  UserRating getUserRating(String userId) {
	UserRating ratings = restTemplate.getForObject("http://movie-rating-service/ratingdata/users/" + userId,
			UserRating.class);
	return ratings;
}
	
	public UserRating getUserFallbackRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(new Rating("0",0)));
		return userRating;
	}
}
