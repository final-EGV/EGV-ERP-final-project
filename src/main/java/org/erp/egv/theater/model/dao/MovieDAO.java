package org.erp.egv.theater.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.model.dto.MovieDTO;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<MovieDTO> inquireAllMovieList() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		String jpql = "SELECT m FROM MovieDTO m ORDER BY m.openingDate";
		
		List<MovieDTO> movieList = em.createQuery(jpql, MovieDTO.class)
									 .getResultList();
		
		for (MovieDTO movie : movieList) {
			System.out.println(movie);
		}
		
		return movieList;
	}

	public void registMovie(MovieDTO movie) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		em.persist(movie);
	}

	public MovieDTO inquireSingleMovieByCode(int movieCode) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		MovieDTO movie = em.find(MovieDTO.class, movieCode);
		
		System.out.println("movie(" + movieCode + ") : " + movie);
		
		return movie;
	}

}
