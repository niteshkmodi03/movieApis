package com.api.movieinfoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.movieinfoservice.model.Entity.Movie;
import com.api.movieinfoservice.repo.MovieRepository;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepo;

	public Movie getMovieInfo(Long movieId) {
        System.out.println(movieRepo.findAll());
		Optional<Movie> movie = movieRepo.findById(movieId);
		if (movie.isPresent()) {
			return movie.get();
		} else {
			return null;
		}
	}

	public Movie addMovie(Movie movie) {
		return movieRepo.save(movie);
	}

	public List<Movie> bulkAddMovie(List<Movie> movies) {
		return movieRepo.saveAll(movies);
	}

	public Page<Movie> pagingMovies(int num) {
		Pageable pageable = PageRequest.of(0, num, Sort.by("name").descending());
		Page<Movie> page = movieRepo.findAll(pageable);
		
		while(!page.isEmpty()) {
			pageable = pageable.next();
			page = extractNextPageData(pageable, page);
		}
		return page;
		}

	
	protected Page<Movie> extractNextPageData(Pageable pageable, Page<Movie> page) {
		System.out.println("Initial Value:" + page.getNumber()*page.getSize());
		long finalValue = page.getNumber()*page.getSize() + page.getSize() < page.getTotalElements()
				? page.getNumber()*page.getSize() + page.getSize()
				: page.getTotalElements();
		System.out.println("Final Value:" + finalValue);
		System.out.println(page.getNumberOfElements());
		page.forEach(s->{
			System.out.println(s.getMovieId());
			System.out.println(s.getName());
		});
		
		page = movieRepo.findAll(pageable);
		return page;
	}
		
//		Pageable previousPageable = page.previousPageable();
//		System.out.println(previousPageable);
//		Pageable nextPageable = page.nextPageable();
//		while (nextPageable.isPaged()) {
//			System.out.println("pageNumber" + nextPageable.getPageNumber());
//			
//			// display no calculation
//			// 0-2 -> pagenumber*size -> 0 + size
//			// 2-4 -> pageNumber*size -> 2
//			
//			if (nextPageable.isPaged()) {
//				System.out.println(nextPageable);
//			} else {
//				System.out.println("Disable next buttion");
//			}
//			
//			System.out.println("Initial Value:" + nextPageable.getPageNumber()*nextPageable.getPageSize());
//			long initialValue = nextPageable.getPageNumber()*nextPageable.getPageSize();
//			long finalValue = nextPageable.getPageNumber()*nextPageable.getPageSize() + nextPageable.getPageSize() < page.getTotalElements()
//					? nextPageable.getPageNumber()*nextPageable.getPageSize() + nextPageable.getPageSize()
//					: page.getTotalElements();
//			System.out.println("Final Value:" + finalValue);
//			nextPageable= nextPageable.next();
//			if(initialValue>=finalValue) {
//				System.out.println("Manually loop broken");
//				System.out.println(nextPageable.isPaged()+":"+nextPageable.isUnpaged());
//				break;
//			}else {
//				System.out.println(nextPageable.isPaged()+":"+nextPageable.isUnpaged());	
//			}
//		//	page = new Page<Movie>(page,nextPageable,page.getSize());
//		}
//		return page;
//	}

}
