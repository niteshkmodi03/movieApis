package com.api.movieinfoservice.model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

	@Column(name = "name")
	private String name;

	@Id()
	// @Column(name="movie_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;

	@Column(name = "rating")
	private Integer rating;

//	public Movie(String name, String movieId) {
//		super();
//		this.name = name;
//		this.movieId = movieId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", movieId=" + movieId + ", rating=" + rating + "]";
	}

}
