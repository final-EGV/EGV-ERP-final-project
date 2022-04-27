package org.erp.egv.theater.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.entity.Movie;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Movie> inquireAllMovieList() {
		
		String jpql = "SELECT m FROM Movie m ORDER BY m.openingDate";
		
		List<Movie> movieEntityList = em.createQuery(jpql, Movie.class)
										.getResultList();
		
		return movieEntityList;
	}
	
	public List<Movie> inquireOnlyYMovieList() {
		
		String jpql = "SELECT m FROM Movie m WHERE m.openingYn = 'Y' ORDER BY m.openingDate";
		
		List<Movie> movieEntityList = em.createQuery(jpql, Movie.class)
									.getResultList();
		
		return movieEntityList;
	}
	
	public Movie inquireSingleMovieByCode(int movieCode) {
		
		return em.find(Movie.class, movieCode);
	}

	public void registMovie(Movie movie) {
		
		em.persist(movie);
	}

	public void modifyMovie(Movie movieToUpdate) {
		
		Movie movieFromPC = em.find(Movie.class, movieToUpdate.getCode());
		
		movieFromPC.update(movieToUpdate.getName(),
							movieToUpdate.getOpeningDate(),
							movieToUpdate.getRunningTime(),
							movieToUpdate.getGrade(),
							movieToUpdate.getGenre(),
							movieToUpdate.getDistributor(),
							movieToUpdate.getDirector(),
							movieToUpdate.getCountry(),
							movieToUpdate.getPosterOrigName(),
							movieToUpdate.getPosterUuidName(),
							movieToUpdate.getPosterImgPath(),
							movieToUpdate.getOpeningYn());
	}

	public void deleteMovieByCode(int code) {
		
		Movie movie = em.find(Movie.class, code);
		
		/*
		 * Return and terminate current transaction, if there is no entity in
		 * the persistence context with given @Id. No futhur process is required
		 * if entity to delete does not exist.
		 */
		if (movie == null) {
			return;
		}
		
		em.remove(em.contains(movie) ? movie : em.merge(movie));
	}

}
