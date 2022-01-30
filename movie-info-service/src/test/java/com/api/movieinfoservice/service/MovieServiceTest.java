package com.api.movieinfoservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.api.movieinfoservice.model.Entity.Movie;
import com.api.movieinfoservice.repo.MovieRepository;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

	@InjectMocks
	MovieService movieService;

	@Mock
	MovieRepository movieRepository;

	@Test
	public void testGetMovieInfoNotNull() {
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		Optional<Movie> value = Optional.of(movie);
		when(movieRepository.findById(Mockito.anyLong())).thenReturn(value);

		Movie movieInfo = this.movieService.getMovieInfo(123L);
		assertEquals(Long.valueOf(123), movieInfo.getMovieId());
		assertEquals("Timpy", movieInfo.getName());
		assertEquals(Integer.valueOf(0), movieInfo.getRating());
	}

	@Test
	public void testGetMovieInfoNull() {
		Optional<Movie> value = Optional.empty();
		when(movieRepository.findById(Mockito.anyLong())).thenReturn(value);
		Movie movieInfo = this.movieService.getMovieInfo(123L);
		assertNull(movieInfo);
	}

	@Test
	public void testAddMovie() {
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movie);

		Movie movieInfo = this.movieService.addMovie(movie);
		assertEquals(Long.valueOf(123), movieInfo.getMovieId());
		assertEquals("Timpy", movieInfo.getName());
		assertEquals(Integer.valueOf(0), movieInfo.getRating());
	}

	@Test
	public void testAddMovieNull() {
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(null);

		Movie movieInfo = this.movieService.addMovie(movie);
		assertNull(movieInfo);
	}

	@Test
	public void testBulkAddMovie() {
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		list.add(movie);
		list.add(movie);
		when(movieRepository.saveAll(Mockito.anyList())).thenReturn(list);

		List<Movie> movieInfo = this.movieService.bulkAddMovie(list);
		assertEquals(Long.valueOf(123), movieInfo.get(0).getMovieId());
		assertEquals("Timpy", movieInfo.get(0).getName());
		assertEquals(Integer.valueOf(0), movieInfo.get(0).getRating());
	}

	@Test
	public void testBulkAddMovieNull() {
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		list.add(movie);
		list.add(movie);
		when(movieRepository.saveAll(Mockito.anyList())).thenReturn(null);

		List<Movie> movieInfo = this.movieService.bulkAddMovie(list);
		assertNull(movieInfo);
	}

	@Test
	public void testPagingMovies() {
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		Movie movie2 = new Movie();
		movie2.setMovieId(12L);
		movie2.setName("Timpy");
		movie2.setRating(0);
		list.add(movie);
		list.add(movie2);
		Page<Movie> pagedTasks = new PageImpl(list);

		Page<Movie> pagedTasksEmpty = new PageImpl(new ArrayList<>());
		when(movieRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pagedTasksEmpty);

		Page<Movie> pagingMovies = this.movieService.pagingMovies(2);
		assertNotNull(pagingMovies);
	}

	@Test
	public void testPagingMoviesNotEmpty() {
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		Movie movie2 = new Movie();
		movie2.setMovieId(12L);
		movie2.setName("Timpy");
		movie2.setRating(0);
		list.add(movie);
		list.add(movie2);

		Page<Movie> tasks = Mockito.mock(Page.class);
		Mockito.when(this.movieRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(tasks);

		Mockito.when(tasks.isEmpty()).thenReturn(true);

		Page<Movie> pagingMovies = this.movieService.pagingMovies(2);
		assertNotNull(pagingMovies);
	}

	@Test
	public void testExtractNextMoviesNotEmpty() {
		Page<Movie> tasks = Mockito.mock(Page.class);
		Pageable pageable = tasks.getPageable();

		when(tasks.getNumber()).thenReturn(1);
		when(tasks.getSize()).thenReturn(2);
		when(tasks.getTotalElements()).thenReturn(7L);
		Page<Movie> extractNextPageData = this.movieService.extractNextPageData(pageable, tasks);

	}

	@Test
	public void testExtractNextMoviesMore() {
		Page<Movie> tasks = Mockito.mock(Page.class);
		Pageable pageable = tasks.getPageable();

		when(tasks.getNumber()).thenReturn(1);
		when(tasks.getSize()).thenReturn(2);
		when(tasks.getTotalElements()).thenReturn(3L);
		Page<Movie> extractNextPageData = this.movieService.extractNextPageData(pageable, tasks);

	}

	@Test
	public void testExtractNextMoviesMoreElements() {
		System.out.println("V");
		List<Movie> list = new ArrayList<>();
		Movie movie = new Movie();
		movie.setMovieId(123L);
		movie.setName("Timpy");
		movie.setRating(0);
		Movie movie2 = new Movie();
		movie2.setMovieId(12L);
		movie2.setName("Timpy");
		movie2.setRating(0);
		list.add(movie);
		list.add(movie2);
		Movie movie3 = new Movie();
		movie3.setMovieId(120L);
		movie3.setName("Timpy");
		movie3.setRating(0);
		list.add(movie3);
		Page<Movie> pagedTasks = new PageImpl(list);

		Page<Movie> pagedTasksEmpty = new PageImpl(new ArrayList<>());
		when(movieRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pagedTasksEmpty);

		Page<Movie> extractNextPageData = this.movieService.extractNextPageData(pagedTasks.getPageable(), pagedTasks);

	}

}
