package com.api.movieratingservice.resource;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movieratingservice.model.Rating;
import com.api.movieratingservice.model.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getRatingByUser(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(new Rating("1234",4),new Rating("5678",3)));
		return userRating;
	}
}
