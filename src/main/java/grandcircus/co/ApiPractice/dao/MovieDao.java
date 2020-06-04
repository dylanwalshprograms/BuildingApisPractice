package grandcircus.co.ApiPractice.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import grandcircus.co.ApiPractice.entity.Movie;

public interface MovieDao extends JpaRepository <Movie, Long>{

	List<Movie> findByTitleContaining(String keyword);
	
	List<Movie> findByCategoryContaining(String keyword);
	
	@Query(nativeQuery=true, value="SELECT *  FROM movies ORDER BY RAND() LIMIT 1")
	Movie findRandom();
	
	@Query(nativeQuery=true, value="SELECT *  FROM movies WHERE category LIKE :category ORDER BY RAND() LIMIT 1")
	Movie findRandomByCategoryContaining(@Param("category") String category);
	
	@Query(nativeQuery=true, value="SELECT *  FROM movies ORDER BY RAND() LIMIT :size")
	List<Movie> findRandomList(@Param("size") Long size);
	
	@Query(nativeQuery=true, value="SELECT category FROM movies;")
	Set<Object> findCategories();

}
