package com.api.movieinfoservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.movieinfoservice.model.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
