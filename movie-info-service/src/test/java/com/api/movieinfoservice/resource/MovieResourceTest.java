package com.api.movieinfoservice.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.movieinfoservice.model.Entity.Movie;
import com.api.movieinfoservice.service.MovieService;

@RunWith(MockitoJUnitRunner.class)
public class MovieResourceTest {

	@InjectMocks
	MovieResource movieResource;

	@Mock
	MovieService movieService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetMoviesWithIdStatusOk() {
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		when(movieService.getMovieInfo(Mockito.anyLong())).thenReturn(movie);
		ResponseEntity<Movie> movies = this.movieResource.getMovies(123L);
		assertNotNull(movies);

		assertEquals(Long.valueOf(123), movies.getBody().getMovieId());
		assertEquals("Timpy", movies.getBody().getName());
		assertEquals(Integer.valueOf(0), movies.getBody().getRating());
		assertEquals(HttpStatus.OK, movies.getStatusCode().OK);
	}

	@Test
	public void testGetMoviesWithIdStatusNoContent() {
		when(movieService.getMovieInfo(Mockito.anyLong())).thenReturn(null);
		ResponseEntity<Movie> movies = this.movieResource.getMovies(123L);
		assertNull(movies.getBody());
		assertEquals(HttpStatus.NO_CONTENT, movies.getStatusCode().NO_CONTENT);
	}

	@Test
	public void testAddMoviesWithIdStatusCreated() {
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		when(movieService.addMovie(Mockito.any(Movie.class))).thenReturn(movie);
		ResponseEntity<Movie> movies = this.movieResource.addMovies(movie);
		assertNotNull(movies);

		assertEquals(Long.valueOf(123), movies.getBody().getMovieId());
		assertEquals("Timpy", movies.getBody().getName());
		assertEquals(Integer.valueOf(0), movies.getBody().getRating());
		assertEquals(HttpStatus.CREATED, movies.getStatusCode().CREATED);
	}

	@Test
	public void testAddMoviesWithIdStatusNoContent() {
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		when(movieService.addMovie(Mockito.any(Movie.class))).thenReturn(null);
		ResponseEntity<Movie> movies = this.movieResource.addMovies(movie);
		assertNotNull(movies);
		assertNull(movies.getBody());
		assertEquals(HttpStatus.NO_CONTENT, movies.getStatusCode().NO_CONTENT);
	}

	@Test
	public void testBulkAddMoviesWithIdStatusCreated() {
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		list.add(movie);
		list.add(movie);
		when(movieService.bulkAddMovie(Mockito.anyList())).thenReturn(list);
		ResponseEntity<List<Movie>> movies = this.movieResource.addBulkMovies(list);
		assertNotNull(movies);

		assertEquals(Long.valueOf(123), movies.getBody().get(0).getMovieId());
		assertEquals("Timpy", movies.getBody().get(0).getName());
		assertEquals(Integer.valueOf(0), movies.getBody().get(0).getRating());
		assertEquals(HttpStatus.CREATED, movies.getStatusCode().CREATED);
	}

	@Test
	public void testBulkAddMoviesWithIdStatusNoContent() {
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		list.add(movie);
		list.add(movie);
		when(movieService.bulkAddMovie(Mockito.anyList())).thenReturn(null);
		ResponseEntity<List<Movie>> movies = this.movieResource.addBulkMovies(list);
		assertNotNull(movies);
		assertEquals(HttpStatus.NO_CONTENT, movies.getStatusCode().NO_CONTENT);
	}

	@Test
	public void testPagingMoviesWithIdStatusCreated() {
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		list.add(movie);
		list.add(movie);
		Page<Movie> pagedTasks = new PageImpl(list);
		when(movieService.pagingMovies(Mockito.anyInt())).thenReturn(pagedTasks);
		ResponseEntity<Page<Movie>> movies = this.movieResource.fixedMovie(2);
		assertNotNull(movies);

		movies.getBody().get().forEach(s -> {
			assertEquals(Long.valueOf(123), s.getMovieId());
			assertEquals("Timpy", s.getName());
			assertEquals(Integer.valueOf(0), s.getRating());
		});

		assertEquals(HttpStatus.CREATED, movies.getStatusCode().CREATED);
	}

	@Test
	public void testPagingMoviesWithIdStatusNoContent() {
		when(movieService.pagingMovies(Mockito.anyInt())).thenReturn(null);
		ResponseEntity<Page<Movie>> movies = this.movieResource.fixedMovie(2);
		assertNotNull(movies);
		assertEquals(HttpStatus.NO_CONTENT, movies.getStatusCode().NO_CONTENT);
	}

}
