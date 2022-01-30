package com.api.movieinfoservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.movieinfoservice.model.Entity.Movie;
import com.api.movieinfoservice.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Autowired
	MovieService service;

	@RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
	public ResponseEntity<Movie> getMovies(@PathVariable("movieId") Long movieId) {
		Movie movieInfo = service.getMovieInfo(movieId);
		if (movieInfo != null) {
			System.out.println(movieInfo);
			return new ResponseEntity<Movie>(movieInfo, HttpStatus.OK);
		} else {
			System.out.println("movieInfo null:" + movieInfo);
			return new ResponseEntity<Movie>(movieInfo, HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "addMovie", method = RequestMethod.POST)
	public ResponseEntity<Movie> addMovies(@RequestBody Movie movie) {
		Movie addMovie = service.addMovie(movie);
		if (addMovie == null) {
			return new ResponseEntity<Movie>(addMovie, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Movie>(addMovie, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "bulkAddMovie", method = RequestMethod.POST)
	public ResponseEntity<List<Movie>> addBulkMovies(@RequestBody List<Movie> movies) {
		List<Movie> bulkAddMovie = service.bulkAddMovie(movies);
		if (bulkAddMovie == null) {
			return new ResponseEntity<List<Movie>>(bulkAddMovie, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Movie>>(bulkAddMovie, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/paging/{num}", method = RequestMethod.GET)
	public ResponseEntity<Page<Movie>> fixedMovie(@PathVariable("num") int num) {
		Page<Movie> pagingMovies = service.pagingMovies(num);
		if (pagingMovies == null) {
			return new ResponseEntity<Page<Movie>>(pagingMovies, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Page<Movie>>(pagingMovies, HttpStatus.OK);
		}
	}

}
