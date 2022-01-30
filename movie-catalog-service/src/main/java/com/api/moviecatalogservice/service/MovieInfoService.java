package com.api.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.api.moviecatalogservice.model.CatalogueItem;
import com.api.moviecatalogservice.model.Movie;
import com.api.moviecatalogservice.model.Rating;
import com.api.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfoService {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public CatalogueItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getRating(), Movie.class);
		return new CatalogueItem(movie.getName(), "desc", rating.getRating());
	}

	public CatalogueItem getFallbackCatalog(Rating rating) {
		return new CatalogueItem("Movie not found", "", rating.getRating());
	}

	
}
