package com.api.movieinfoservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.api.movieinfoservice.resource.MovieResource;

@EnableJpaRepositories(basePackages = { "com.api.movieinfoservice.repo" })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EntityScan
public class MovieInfoServiceApplicationTests {

	@Autowired
	MovieResource movieResource;

	@Test
	public void contextLoads() {
		assertThat(movieResource).isNotNull();
	}

//	@BeforeEach
//	void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
//	}
//	
//	 

}
