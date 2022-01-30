package com.api.movieinfoservice.resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.api.movieinfoservice.model.Entity.Movie;
import com.api.movieinfoservice.service.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieResourceClass2Test {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationContext applicationContext;

	@MockBean
	private MovieService movieService;

	@BeforeEach
	void printApplicationContext() {
		Arrays.stream(applicationContext.getBeanDefinitionNames())
				.map(name -> applicationContext.getBean(name).getClass().getName()).sorted()
				.forEach(System.out::println);
	}

	@Test
	public void movieIdReturnsHttpStatusOk() throws Exception {
		when(movieService.getMovieInfo(Mockito.anyLong())).thenReturn(new Movie());
		this.mockMvc.perform(get("/movies/123").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void movieIdReturnsHttpStatusNoContent() throws Exception {
		when(movieService.getMovieInfo(Mockito.anyLong())).thenReturn(null);
		this.mockMvc.perform(get("/movies/110").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	public void addMovieReturnsHttpStatusCreated() throws Exception {
		when(movieService.addMovie(Mockito.any(Movie.class))).thenReturn(new Movie());
		this.mockMvc
				.perform(post("/movies/addMovie").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"Timpy\",\"rating\": 0}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void addMovietReturnsHttpStatusNoContent() throws Exception {
		when(movieService.addMovie(Mockito.any(Movie.class))).thenReturn(null);
		this.mockMvc
				.perform(post("/movies/addMovie").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"Timpy\",\"rating\": 0}").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void bulkAddMovieReturnsHttpStatusCreated() throws Exception {
		when(movieService.bulkAddMovie(Mockito.anyList())).thenReturn(new ArrayList<Movie>());
		this.mockMvc
				.perform(post("/movies/bulkAddMovie").contentType(MediaType.APPLICATION_JSON)
						.content("[{\"name\": \"Timpy\",\"rating\": 0}]").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void bulkAddMovieReturnsHttpStatusNoContent() throws Exception {
		when(movieService.bulkAddMovie(Mockito.anyList())).thenReturn(null);
		this.mockMvc
				.perform(post("/movies/bulkAddMovie").contentType(MediaType.APPLICATION_JSON)
						.content("[{\"name\": \"Timpy\",\"rating\": 0}]").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	public void pagingMovieReturnsHttpStatusCreated() throws Exception {
		List<Movie> tasks = new ArrayList<>();
		Page<Movie> pagedTasks = new PageImpl(tasks);
		when(movieService.pagingMovies(Mockito.anyInt())).thenReturn(pagedTasks);
		this.mockMvc.perform(get("/movies/paging/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void pagingMovietReturnsHttpStatusNoContent() throws Exception {
		when(movieService.getMovieInfo(Mockito.anyLong())).thenReturn(null);
		this.mockMvc.perform(get("/movies/paging/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
//

}
