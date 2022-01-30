package com.api.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.moviecatalogservice.model.CatalogueItem;
import com.api.moviecatalogservice.model.UserRating;
import com.api.moviecatalogservice.service.MovieInfoService;
import com.api.moviecatalogservice.service.UserRatingService;

@RestController
@RequestMapping("/catalogue")
public class MovieCatalogueResource {

	@Autowired
	private UserRatingService usrRatingService;
	
	@Autowired
	private MovieInfoService movieInfoService;
	

//	@Autowired
//	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userid}")
	public List<CatalogueItem> getMovieCatalogue(@PathVariable("userid") String userId) {

		UserRating ratings = usrRatingService.getUserRating(userId);
		
//		HashMap<Integer,Integer> map = new HashMap<>(); 
//		map.entrySet().stream().max((e1,e2)->e1.getValue()>e2.getValue()?1:-1).get().getValue();
//
//		Entry<Integer, Integer> max = Collections.max(map.entrySet(), Map.Entry.comparingByValue(Collections.reverseOrder()));
		
		return ratings.getUserRating().stream().map(rating -> {
			return movieInfoService.getCatalogItem(rating);
		}).collect(Collectors.toList());

	}

	

}

//Movie movie = webClientBuilder
//.build()
//.get()
//.uri("http://localhost:8081/movies/" + rating.getRating())
//.retrieve().bodyToMono(Movie.class).block();

//return ratings.stream().
//map(rating->{new CatalogueItem("Titanic", "test", 4);}
//.collect(Collectors.toList()));
//return Collections.singletonList(new CatalogueItem("Titanic", "test", 4));
