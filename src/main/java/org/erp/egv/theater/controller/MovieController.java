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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		String movieName = new String(request.getParameter("movieName").getBytes("ISO-8859-1"), "UTF-8");
		
		String openingDateString = new String(request.getParameter("openingDate").getBytes("ISO-8859-1"), "UTF-8");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = format.parse(openingDateString);
		java.sql.Date openingDate = new java.sql.Date(utilDate.getTime());
		
		int runningTime = Integer.valueOf(new String(request.getParameter("runningTime").getBytes("ISO-8859-1"), "UTF-8"));
		String grade = new String(request.getParameter("grade").getBytes("ISO-8859-1"), "UTF-8");
		String genre = new String(request.getParameter("genre").getBytes("ISO-8859-1"), "UTF-8");
		String distributor = new String(request.getParameter("distributor").getBytes("ISO-8859-1"), "UTF-8");
		String director = new String(request.getParameter("director").getBytes("ISO-8859-1"), "UTF-8");
		String country = new String(request.getParameter("country").getBytes("ISO-8859-1"), "UTF-8");
		String openingYn = new String(request.getParameter("openingYn").getBytes("ISO-8859-1"), "UTF-8");
		
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
		String rootPath = root.replace("/", "\\").substring(0, root.length() - 15).substring(1).concat("src\\main\\resources\\static\\");
		String posterImgPath = rootPath + "img/poster/";
		System.out.println("저장 경로 : " + posterImgPath);
		
		// create folder
		File mkdir = new File(posterImgPath);
		if (!mkdir.exists()) {
			System.out.println("폴더 생성 : " + mkdir.mkdir());
		}
		
		// file name randomization
		String posterOrigName = new String(posterImg.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("원본 파일명 : " + posterOrigName);
		
		String extension = posterOrigName.substring(posterOrigName.lastIndexOf("."));
		String posterUuidName = UUID.randomUUID().toString().replace("-", "") + extension;
		System.out.println("변경 파일명 : " + posterUuidName);
		
		String pathToRedirect = "";
		
		// save file & prepare DTO
		try {
			
			// save file
			posterImg.transferTo(new File(posterImgPath + "\\" + posterUuidName));
			
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
			rAttr.addFlashAttribute("flashMessage", "[Success] 신규 영화 정보 등록을 성공했습니다.");
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			// delete file
			new File(posterImgPath + "\\" + posterUuidName).delete();
			
			/* 
			 * TODO 2021-11-24 Wed 01:45 송언석
			 * 영화 등록 실패시 작성한 파라미터를 다시 등록 폼으로 갖고 돌아가도록 설정할 예정.
			 * 현재는 실패하도 다시 영화 리스트로 리다이렉트 되도록 설정.
			 */
			pathToRedirect = "list";
			rAttr.addFlashAttribute("flashMessage", "[Error] 신규 영화 정보 등록을 실패했습니다.");
		}
		
		mv.setViewName("redirect:" + pathToRedirect);
		
		return mv;
	}
	
}
