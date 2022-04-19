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
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		String jpql = "SELECT m FROM Movie m ORDER BY m.openingDate";
		
		List<Movie> movieEntityList = em.createQuery(jpql, Movie.class)
										.getResultList();
		
		return movieEntityList;
	}
	
	public List<Movie> inquireOnlyYMovieList() {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		String jpql = "SELECT m FROM Movie m WHERE m.openingYn = 'Y' ORDER BY m.openingDate";
		
		List<Movie> movieEntityList = em.createQuery(jpql, Movie.class)
									.getResultList();
		
		return movieEntityList;
	}
	
	public Movie inquireSingleMovieByCode(int movieCode) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		return em.find(Movie.class, movieCode);
	}

	public void registMovie(Movie movie) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		em.persist(movie);
	}

	public void modifyMovie(Movie movieToUpdate) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
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
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		Movie movie = em.find(Movie.class, code);
		
		if (movie == null) {
			System.out.println("[Error] " + code + "번 영화는 현재 데이터베이스에 존재하지 않습니다.");
			
			return;
		}
		
		em.remove(em.contains(movie) ? movie : em.merge(movie));
	}

}
