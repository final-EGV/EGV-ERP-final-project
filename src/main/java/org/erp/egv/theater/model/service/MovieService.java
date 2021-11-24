package org.erp.egv.theater.model.service;

import java.util.List;

import org.erp.egv.theater.model.dao.MovieDAO;
import org.erp.egv.theater.model.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		movieDAO.registMovie(movie);
	}

}
