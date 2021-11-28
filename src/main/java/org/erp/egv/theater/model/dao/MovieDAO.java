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

	public void modifyMovie(MovieDTO movieNew) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		MovieDTO movieOrigin = em.find(MovieDTO.class, movieNew.getCode());
		
		movieOrigin.setName(movieNew.getName());
		movieOrigin.setOpeningDate(movieNew.getOpeningDate());
		movieOrigin.setRunningTime(movieNew.getRunningTime());
		movieOrigin.setGrade(movieNew.getGrade());
		movieOrigin.setGenre(movieNew.getGenre());
		movieOrigin.setDistributor(movieNew.getDistributor());
		movieOrigin.setDirector(movieNew.getDirector());
		movieOrigin.setCountry(movieNew.getCountry());
		movieOrigin.setPosterOrigName(movieNew.getPosterOrigName());
		movieOrigin.setPosterUuidName(movieNew.getPosterUuidName());
		movieOrigin.setPosterImgPath(movieNew.getPosterImgPath());
		
	}

	public void deleteMovieByCode(int code) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		MovieDTO movie = em.find(MovieDTO.class, code);
		
		if (movie == null) {
			System.out.println("[Error] " + code + "번 영화는 현재 데이터베이스에 존재하지 않습니다.");
			
			return;
		}
		
		em.remove(em.contains(movie) ? movie : em.merge(movie));
	}

	public MovieDTO inquireSingleMovieByName(String movieName) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		MovieDTO movie = em.createQuery("SELECT m FROM MovieDTO m WHERE m.name = :movieName", MovieDTO.class)
						   .setParameter("movieName", movieName)
						   .getSingleResult();

		return movie;
	}

}
