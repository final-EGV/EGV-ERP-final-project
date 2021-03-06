package org.erp.egv.theater.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.theater.model.dto.MovieDTO;
import org.erp.egv.theater.model.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/theater/movie")
public class MovieController {
	
	private MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/list")
	public ModelAndView inquireAllMovieList(ModelAndView mv) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/movieList");
		
		return mv;
	}
	
	@GetMapping("/regist")
	public String registMovie() throws UnsupportedEncodingException {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return "theater/movieRegist";
	}
	
	@PostMapping("/regist")
	public ModelAndView registMovie(HttpServletRequest request,
								@RequestParam("posterImg") MultipartFile posterImg,
								ModelAndView mv,
								RedirectAttributes rAttr) throws UnsupportedEncodingException, ParseException {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		request.setCharacterEncoding("UTF-8");
		
		/* 1. String type input parameters */
		String movieName = request.getParameter("movieName");
		
		String openingDateString = request.getParameter("openingDate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = format.parse(openingDateString);
		java.sql.Date openingDate = new java.sql.Date(utilDate.getTime());
		
		int runningTime = Integer.valueOf(request.getParameter("runningTime"));
		String grade = request.getParameter("grade");
		String genre = request.getParameter("genre");
		String distributor = request.getParameter("distributor");
		String director = request.getParameter("director");
		String country = request.getParameter("country");
		String openingYn = request.getParameter("openingYn");
		
		/* 1-1. test print for requested parameters */
		System.out.println("movieName : " + movieName);
		System.out.println("openingDate : " + openingDate);
		System.out.println("runningTime : " + runningTime);
		System.out.println("grade : " + grade);
		System.out.println("genre : " + genre);
		System.out.println("distributor : " + distributor);
		System.out.println("director : " + director);
		System.out.println("country : " + country);
		System.out.println("openingYn : " + openingYn);
		
		// get root path based on this project
		String root = this.getClass().getResource("/").getPath();
		String rootPath = root.replace("/", "\\")
							  .substring(0, root.length() - 15)
							  .substring(1)
							  .concat("src\\main\\resources\\static\\");
		// set save path based on this project
		String posterImgPath = rootPath + "img/poster/";	// TODO ???????????? ?????? ??? /static ?????? ???????????? ????????? ?????? ??????
		System.out.println("?????? ?????? : " + posterImgPath);
		
		// create folder if doesn't exist
		File mkdir = new File(posterImgPath);
		if (!mkdir.exists()) {
			System.out.println("?????? ?????? : " + mkdir.mkdir());
		}
		
		// file name randomization
		String posterOrigName = posterImg.getOriginalFilename();
		System.out.println("?????? ????????? : " + posterOrigName);
		
		String extension = posterOrigName.substring(posterOrigName.lastIndexOf("."));
		String posterUuidName = UUID.randomUUID().toString().replace("-", "") + extension;
		System.out.println("?????? ????????? : " + posterUuidName);
		
		String pathToRedirect = "";
		
		// save file & prepare DTO object
		try {
			
			// save file
			posterImg.transferTo(new File(posterImgPath + "\\" + posterUuidName));
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			// delete file
			new File(posterImgPath + "\\" + posterUuidName).delete();
			
			/* 
			 * FIXME 2021-11-24 Wed 01:45 ?????????
			 * ?????? ?????? ????????? ????????? ??????????????? ?????? ?????? ????????? ?????? ??????????????? ????????? ??????.
			 * ????????? ???????????? ?????? ?????? ???????????? ??????????????? ????????? ??????.
			 */
			pathToRedirect = "list";
			rAttr.addFlashAttribute("flashMessage", "[Error] ?????? ?????? ?????? ?????????"
													+ " ????????? ????????? ????????????????????? ????????? ??????????????????."
													+ " ?????? ????????? ?????????.");
		}
		
		// prepare DTO
		MovieDTO movie = new MovieDTO();
		
		movie.setName(movieName);
		movie.setOpeningDate(openingDate);
		movie.setRunningTime(runningTime);
		movie.setGrade(grade);
		movie.setGenre(genre);
		movie.setDistributor(distributor);
		movie.setDirector(director);
		movie.setCountry(country);
		movie.setPosterOrigName(posterOrigName);
		movie.setPosterUuidName(posterUuidName);
		movie.setPosterImgPath(posterImgPath);
		movie.setOpeningYn(openingYn);
		System.out.println(movie);
		
		movieService.registMovie(movie);
		
		pathToRedirect = "list";
		rAttr.addFlashAttribute("flashMessage", "[Success] ?????? ?????? ?????? ????????? ??????????????????.");
		
		mv.setViewName("redirect:" + pathToRedirect);
		
		return mv;
	}
	
	@GetMapping("/details")
	public ModelAndView getDetailsOfSingleMovie(ModelAndView mv, @RequestParam int code) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		MovieDTO movie = movieService.inquireSingleMovieByCode(code);
		
		mv.addObject("movie", movie);
		mv.setViewName("theater/movieDetails");
		
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modifyMovie(HttpServletRequest request,
									@RequestParam("posterImg") MultipartFile posterImg,
									ModelAndView mv,
									RedirectAttributes rAttr)
											throws UnsupportedEncodingException, ParseException {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		request.setCharacterEncoding("UTF-8");
		
		/* 1. String type input parameters */
		int movieCode = Integer.valueOf(request.getParameter("movieCode"));
		String movieName = request.getParameter("movieName");
		
		String openingDateString = request.getParameter("openingDate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = format.parse(openingDateString);
		java.sql.Date openingDate = new java.sql.Date(utilDate.getTime());
		
		int runningTime = Integer.valueOf(request.getParameter("runningTime"));
		String grade = request.getParameter("grade");
		String genre = request.getParameter("genre");
		String distributor = request.getParameter("distributor");
		String director = request.getParameter("director");
		String country = request.getParameter("country");
		String openingYn = request.getParameter("openingYn");
		
		/* 1-1. test print for requested parameters */
		System.out.println("movieName : " + movieName);
		System.out.println("openingDate : " + openingDate);
		System.out.println("runningTime : " + runningTime);
		System.out.println("grade : " + grade);
		System.out.println("genre : " + genre);
		System.out.println("distributor : " + distributor);
		System.out.println("director : " + director);
		System.out.println("country : " + country);
		System.out.println("openingYn : " + openingYn);
		
		/* prepare DTO object */
		MovieDTO movieNew = new MovieDTO();
		movieNew.setCode(movieCode);
		movieNew.setName(movieName);
		movieNew.setOpeningDate(openingDate);
		movieNew.setRunningTime(runningTime);
		movieNew.setGrade(grade);
		movieNew.setGenre(genre);
		movieNew.setDistributor(distributor);
		movieNew.setDirector(director);
		movieNew.setCountry(country);
		movieNew.setOpeningYn(openingYn);
		
		String pathToRedirect = "";
		
		/* find the entity already exists in persistence context */
		MovieDTO movieOrigin = movieService.inquireSingleMovieByCode(movieCode);
		
		if (!posterImg.isEmpty()) {
			/*
			 * When user uploads file
			 */
			
			if (posterImg.getOriginalFilename() != movieOrigin.getPosterOrigName()) {
				/*
				 * When user uploads a different file from the original file
				 *   1. upload new poster image file.
				 *   2. delete the former image file, if above task is successfully done.
				 */
				
				/* 1. upload new poster image file */
				// get root path based on this project
				String root = this.getClass().getResource("/").getPath();
				String rootPath = root.replace("/", "\\")
									  .substring(0, root.length() - 15)
									  .substring(1)
									  .concat("src\\main\\resources\\static\\");
				
				// set save path based on this project
				String posterImgPath = rootPath + "img/poster/";	// TODO ???????????? ????????? /static ?????? ???????????? ????????? ?????? ??????
				System.out.println("?????? ?????? : " + posterImgPath);
				
				// create folder if doesn't exist
				File mkdir = new File(posterImgPath);
				if (!mkdir.exists()) {
					System.out.println("?????? ?????? : " + mkdir.mkdir());
				}
				
				// randomize file name
				String posterOrigName = posterImg.getOriginalFilename();
				System.out.println("?????? ????????? : " + posterOrigName);
				
				String extension = posterOrigName.substring(posterOrigName.lastIndexOf("."));
				String posterUuidName = UUID.randomUUID().toString().replace("-", "") + extension;
				System.out.println("?????? ????????? : " + posterUuidName);
				
				try {
					
					// save file
					posterImg.transferTo(new File(posterImgPath + "\\" + posterUuidName));
					
					movieNew.setPosterOrigName(posterOrigName);
					movieNew.setPosterUuidName(posterUuidName);
					movieNew.setPosterImgPath(posterImgPath);
					
				} catch (IllegalStateException | IOException e) {
					/*
					 * When any exception occurs during saving file
					 */
					
					e.printStackTrace();
					
					// delete file
					new File(posterImgPath + "\\" + posterUuidName).delete();
					
					rAttr.addFlashAttribute("flashMessage", "[Error] ????????? ????????? ????????? ??????????????? ??????"
															+ " ????????? ??????????????????."
															+ " ?????? ????????? ?????????.");
					
					/* 
					 * FIXME 2021-11-26 Fri 02:15 ?????????
					 * ?????? ????????? ????????? ????????? ??????????????? ?????? ????????? ??? ???????????? ?????? ??????????????? ????????? ??????.
					 * ????????? ???????????? ?????? ?????? ???????????? ??????????????? ????????? ??????.
					 */
//					pathToRedirect = "backToDetailsPage";
					
				}
				
				/* 2. delete the former image file, if above task is successfully done. */
				new File(movieOrigin.getPosterImgPath() + "\\" + movieOrigin.getPosterUuidName()).delete();
				
			}
			
		} else {
			/* 
			 * When user doesn't upload any file, current poster image file info should be remain.
			 * Find the entity already exists in persistence context,
			 * get file info, and then apply to new entity.
			 */
			
			movieNew.setPosterOrigName(movieOrigin.getPosterOrigName());
			movieNew.setPosterUuidName(movieOrigin.getPosterUuidName());
			movieNew.setPosterImgPath(movieOrigin.getPosterImgPath());
			
		}
		
		movieService.modifyMovie(movieNew);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] ?????? ?????? ????????? ??????????????????.");
		
		/* 
		 * FIXME 2021-11-26 Fri 02:15 ?????????
		 * ?????? ????????? ????????? ????????? ??????????????? ?????? ????????? ??? ???????????? ?????? ??????????????? ????????? ??????.
		 * ????????? ???????????? ?????? ?????? ???????????? ??????????????? ????????? ??????.
		 */
		pathToRedirect = "list";
		
		mv.setViewName("redirect:" + pathToRedirect);
		
		return mv;
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteMovieByCode(ModelAndView mv,
										  RedirectAttributes rAttr,
										  @RequestParam int code) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		movieService.deleteMovieByCode(code);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] " + code + "??? ?????? ????????? ??????????????????.");
		mv.setViewName("redirect:list");
		
		return mv;
	}
	
}
