package org.erp.egv.theater.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.theater.model.dao.MovieDAO;
import org.erp.egv.theater.model.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
	
	private MovieDAO movieDAO;
	
	@Autowired
	public MovieService(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	@Transactional
	public List<MovieDTO> inquireAllMovieList() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return movieDAO.inquireAllMovieList();
	}

	@Transactional
	public void registMovie(MovieDTO movie) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());

		movieDAO.registMovie(movie);
	}

	@Transactional
	public MovieDTO inquireSingleMovieByCode(int movieCode) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return movieDAO.inquireSingleMovieByCode(movieCode);
	}

	@Transactional
	public void modifyMovie(MovieDTO movieNew) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		movieDAO.modifyMovie(movieNew);
	}

	@Transactional
	public void deleteMovieByCode(int code) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		movieDAO.deleteMovieByCode(code);
	}

	@Transactional
	public MovieDTO inquireSingleMovieByName(String movieName) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());

		return movieDAO.inquireSingleMovieByName(movieName);
	}

}
