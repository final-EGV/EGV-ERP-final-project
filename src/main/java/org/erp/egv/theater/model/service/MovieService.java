package org.erp.egv.theater.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.theater.entity.Movie;
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
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		List<Movie> movieEntityList = movieDAO.inquireAllMovieList();
		List<MovieDTO> movieDtoList = new ArrayList<>();
		
		for (Movie entity : movieEntityList) {
			movieDtoList.add(entity.toDto());
		}
		
		return movieDtoList;
	}
	
	@Transactional
	public List<MovieDTO> inquireOnlyYMovieList() {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		List<Movie> movieEntityList = movieDAO.inquireOnlyYMovieList();
		List<MovieDTO> movieDtoList = new ArrayList<>();
		
		for (Movie entity : movieEntityList) {
			movieDtoList.add(entity.toDto());
		}
		
		return movieDtoList;
	}
	
	@Transactional
	public MovieDTO inquireSingleMovieByCode(int movieCode) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		return movieDAO.inquireSingleMovieByCode(movieCode).toDto();
	}
	
	@Transactional
	public void registMovie(MovieDTO movieDto) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		movieDAO.registMovie(movieDto.toEntity());
	}

	@Transactional
	public void modifyMovie(MovieDTO movieToUpdate) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		movieDAO.modifyMovie(movieToUpdate.toEntityWithId());
	}

	@Transactional
	public void deleteMovieByCode(int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		movieDAO.deleteMovieByCode(code);
	}

}
