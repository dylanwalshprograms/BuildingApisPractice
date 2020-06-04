package grandcircus.co.ApiPractice;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grandcircus.co.ApiPractice.dao.MovieDao;
import grandcircus.co.ApiPractice.entity.Movie;

@RestController
public class MovieApiController {
	
	@Autowired
	private MovieDao repo;
	
	@GetMapping("/movies")
	List<Movie> all() {
		
		return repo.findAll();
	}
	
	@GetMapping("/movies/{id}")
	Movie one(@PathVariable("id") Long id) {
		
		return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such Movie."));
	}
	
	@GetMapping("/movies/by-title")
	List<Movie> findByTitle(@RequestParam(value = "title", required = false) String title) {
		
		return repo.findByTitleContaining(title);
	}
	@GetMapping("/movies/by-category")
	List<Movie> findByCategory(@RequestParam(value = "category", required = false) String category) {
		
		return repo.findByCategoryContaining(category);
	}
	@GetMapping("/movies/random")
	Movie random(@RequestParam(value = "category", required = false) String category) {
		if (category == null || category.isEmpty()) {
			return repo.findRandom();
		}
		else {
			return repo.findRandomByCategoryContaining(category);
		}
	}
	@GetMapping("/movies/random-list") 
	List<Movie> randomList(@RequestParam(value = "size", required = false) Long size){
		
		return repo.findRandomList(size);
		
	}
	@GetMapping("movies/categories")
	Set<Object> allCategories() {
		
		return repo.findCategories();
	}

}
