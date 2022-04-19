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
	
	private static final String PATH_TO_SAVE_POSTER_IMG = "static/img/poster/";
	
	private MovieService movieService;
	
	private boolean isHostOsWindows = false;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
		this.isHostOsWindows = isWindows();
	}

	@GetMapping("/list")
	public ModelAndView inquireAllMovieList(ModelAndView mv) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
							+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/movieList");
		
		return mv;
	}
	
	@GetMapping("/details")
	public ModelAndView getDetailsOfSingleMovie(ModelAndView mv, @RequestParam int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		MovieDTO movieDto = movieService.inquireSingleMovieByCode(code);
		
		mv.addObject("movie", movieDto);
		mv.setViewName("theater/movieDetails");
		
		return mv;
	}
	
	@GetMapping("/regist")
	public String registMovie() throws UnsupportedEncodingException {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		return "theater/movieRegist";
	}
	
	@PostMapping("/regist")
	public ModelAndView registMovie(HttpServletRequest request,
								@RequestParam("posterImg") MultipartFile posterImg,
								ModelAndView mv,
								RedirectAttributes rAttr) throws UnsupportedEncodingException, ParseException {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		request.setCharacterEncoding("UTF-8");
		
		/* 1. Verify String type of requested parameters from client browser */
		/* 1.1 Get requested parameters */
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
		
		/* 1-2. Test print */
		System.out.println("--------------- Check Requested Parameters ---------------");
		System.out.println("movieName : " + movieName);
		System.out.println("openingDate : " + openingDate);
		System.out.println("runningTime : " + runningTime);
		System.out.println("grade : " + grade);
		System.out.println("genre : " + genre);
		System.out.println("distributor : " + distributor);
		System.out.println("director : " + director);
		System.out.println("country : " + country);
		System.out.println("openingYn : " + openingYn);
		
		/* 2. Get the saving path */
		/* 2-1. Get relative root path based on this project */
		System.out.println("--------------- File system analyzation ---------------");
		String root = this.getClass().getResource("/").getPath();
		System.out.println("root : " + root);
		
		String rootPath = root.concat(PATH_TO_SAVE_POSTER_IMG);
		
		/*
		 * (Alternative)
		 * Apply proper file separator for Windows server environment, but not recommended.
		 */
		if (isHostOsWindows) {
			rootPath = rootPath.replace("/", "\\");
		}
		
		/* 2-2. Set path where a file would be saved, based on this project */
		String posterImgPath = rootPath;
		System.out.println("posterImgPath : " + posterImgPath);
		
		/* 2-3. Create directory if saving path doesn't exist */
		File mkdir = new File(posterImgPath);
		
		if (!mkdir.exists()) {
			System.out.println("Since there is no directory to store the image file, "
					+ "the directory was created and result is : " + mkdir.mkdirs());
		}
		
		/* 2-4. Randomize file name */
		String posterOrigName = posterImg.getOriginalFilename();
		System.out.println("Original file name : " + posterOrigName);
		
		String extension = posterOrigName.substring(posterOrigName.lastIndexOf("."));
		String posterUuidName = UUID.randomUUID().toString().replace("-", "") + extension;
		System.out.println("Randomized file name : " + posterUuidName);
		
		/* 3. Save or delete a file */
		String pathToRedirect = "";
		
		try {
			/* 3-1. Save the file, if no exceptions occur */
			posterImg.transferTo(new File(posterImgPath + posterUuidName));
			System.out.println("File is saved into -> " + posterImgPath + posterUuidName);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			/* 3-2. Delete a file, if any exceptions occur */
			new File(posterImgPath + posterUuidName).delete();
			
			// redirect to movie list if any exceptions occur while saving a file
			pathToRedirect = "list";
			rAttr.addFlashAttribute("flashMessage", "[Error] 신규 영화 정보를 등록하는 도중에"
													+ " 이미지 파일을 업로드하는데에 문제가 발생했습니다."
													+ " 다시 시도해 주세요. 불편을 드려 죄송합니다.");
			
			mv.setViewName("redirect:" + request.getHeader("Referer"));
			
			return mv;
		}
		
		/* 4. Instantiate MovieDTO */
		MovieDTO movieDto = new MovieDTO();
		
		movieDto.setName(movieName);
		movieDto.setOpeningDate(openingDate);
		movieDto.setRunningTime(runningTime);
		movieDto.setGrade(grade);
		movieDto.setGenre(genre);
		movieDto.setDistributor(distributor);
		movieDto.setDirector(director);
		movieDto.setCountry(country);
		movieDto.setPosterOrigName(posterOrigName);
		movieDto.setPosterUuidName(posterUuidName);
		movieDto.setPosterImgPath(posterImgPath);
		movieDto.setOpeningYn(openingYn);
		
		System.out.println("------------- Movie DTO Created --------------");
		System.out.println("Created movie DTO to insert : " + movieDto);
		System.out.println("----------------------------------------------");
		
		movieService.registMovie(movieDto);
		
		pathToRedirect = "list";
		rAttr.addFlashAttribute("flashMessage", "[Success] 신규 영화 정보 등록을 성공했습니다.");
		
		mv.setViewName("redirect:" + pathToRedirect);
		
		return mv;
	}
	
	@PostMapping("/modify")
	public ModelAndView modifyMovie(HttpServletRequest request,
									@RequestParam("posterImg") MultipartFile posterImgFile,
									ModelAndView mv,
									RedirectAttributes rAttr)
											throws UnsupportedEncodingException, ParseException {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		request.setCharacterEncoding("UTF-8");
		
		/* 1. Verify String type of requested parameters from client browser */
		/* 1.1 Get requested parameters */
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
		
		/* 1-2. Test print */
		System.out.println("--------------- Check Requested Parameters ---------------");
		System.out.println("movieCode : " + movieCode);
		System.out.println("movieName : " + movieName);
		System.out.println("openingDate : " + openingDate);
		System.out.println("runningTime : " + runningTime);
		System.out.println("grade : " + grade);
		System.out.println("genre : " + genre);
		System.out.println("distributor : " + distributor);
		System.out.println("director : " + director);
		System.out.println("country : " + country);
		System.out.println("openingYn : " + openingYn);
		
		/* 2. Prepare original and new Movie DTO */
		MovieDTO movieOriginal = movieService.inquireSingleMovieByCode(movieCode);
		System.out.println("original moview entity : " + movieOriginal);
		MovieDTO movieToUpdate = new MovieDTO();
		
		movieToUpdate.setCode(movieCode);
		movieToUpdate.setName(movieName);
		movieToUpdate.setOpeningDate(openingDate);
		movieToUpdate.setRunningTime(runningTime);
		movieToUpdate.setGrade(grade);
		movieToUpdate.setGenre(genre);
		movieToUpdate.setDistributor(distributor);
		movieToUpdate.setDirector(director);
		movieToUpdate.setCountry(country);
		movieToUpdate.setOpeningYn(openingYn);
		
		movieToUpdate.setPosterOrigName(movieOriginal.getPosterOrigName());
		movieToUpdate.setPosterUuidName(movieOriginal.getPosterUuidName());
		movieToUpdate.setPosterImgPath(movieOriginal.getPosterImgPath());
		
		String pathToRedirect = "";
		
		/* 3. Check whether client has uploaded poster image file. */
		if (!posterImgFile.isEmpty()) {
			/*
			 * 4. Check whether client has uploaded the same file with original one
			 *    [Note that this process is only done by the name of the file!]
			 */
			
			if (!movieOriginal.getPosterOrigName().equals(posterImgFile.getOriginalFilename())) {
				
				/* 4-1. upload new poster image file */
				/* 4-1-1. Get relative root path on this project */
				System.out.println("--------------- File system analyzation ---------------");
				String root = this.getClass().getResource("/").getPath();
				System.out.println("root : " + root);
				
				String rootPath = root.concat(PATH_TO_SAVE_POSTER_IMG);
				
				/*
				 * (Alternative)
				 * Apply proper file separator for Windows server environment, but not recommended.
				 */
				if (isHostOsWindows) {
					rootPath = rootPath.replace("/", "\\");
				}
				
				/* 4-1-2. Set path where a file would be saved, based on this project */
				String posterImgPath = rootPath;
				System.out.println("posterImgPath(저장 경로) : " + posterImgPath);
				
				/* 4-1-3. Create directory if saving path doesn't exist */
				File mkdir = new File(posterImgPath);
				
				if (!mkdir.exists()) {
					System.out.println("Since there is no directory to store the image file, "
							+ "the directory was created and result is : " + mkdir.mkdirs());
				}
				
				/* 4-1-4. Randomize file name */
				String posterOrigName = posterImgFile.getOriginalFilename();
				System.out.println("Original file name : " + posterOrigName);
				
				String extension = posterOrigName.substring(posterOrigName.lastIndexOf("."));
				String posterUuidName = UUID.randomUUID().toString().replace("-", "") + extension;
				System.out.println("Randomized file name : " + posterUuidName);
				
				try {
					
					/* 4-1-5. Save the file, if no exceptions occur */
					posterImgFile.transferTo(new File(posterImgPath + posterUuidName));
					
				} catch (IllegalStateException | IOException e) {
					
					/* 4-1-6. Delete a file, if any exceptions occur */
					e.printStackTrace();
					
					new File(posterImgPath + posterUuidName).delete();
					
					pathToRedirect = "list";
					rAttr.addFlashAttribute("flashMessage", "[Error] 영화 정보를 수정하는 도중에"
															+ " 이미지 파일을 업로드하는데에 문제가 발생했습니다."
															+ " 다시 시도해 주세요. 불편을 드려 죄송합니다.");
					
					mv.setViewName("redirect:" + request.getHeader("Referer"));
					
					return mv;
				}
				
				movieToUpdate.setPosterOrigName(posterOrigName);
				movieToUpdate.setPosterUuidName(posterUuidName);
				movieToUpdate.setPosterImgPath(posterImgPath);
				
				/* 4-2. Delete original image file, if new image file is successfully saved. */
				new File(movieOriginal.getPosterImgPath() + movieOriginal.getPosterUuidName()).delete();
				
			}
			
		}
		
		System.out.println("--------------- Entity Created ---------------");
		System.out.println("Created movie entity to update : " + movieToUpdate);
		System.out.println("----------------------------------------------");
		
		movieService.modifyMovie(movieToUpdate);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 영화 정보 수정을 성공했습니다.");
		
		pathToRedirect = "list";
		
		mv.setViewName("redirect:" + pathToRedirect);
		
		return mv;
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteMovieByCode(ModelAndView mv,
										  RedirectAttributes rAttr,
										  @RequestParam int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		/* delete both movie entity and poster image file */
		MovieDTO movieToDelete = movieService.inquireSingleMovieByCode(code);
		movieService.deleteMovieByCode(code);
		
		new File(movieToDelete.getPosterImgPath() + movieToDelete.getPosterUuidName()).delete();
		
		rAttr.addFlashAttribute("flashMessage", "[Success] " + code + "번 영화 삭제를 성공했습니다.");
		mv.setViewName("redirect:list");
		
		return mv;
	}
	
	/**
	 * Represents whether the Operating System of server or host computer is Windows or not.
	 * <p>
	 * Need to know which file separator(<code>/</code> or <code>\\</code>) should be used,
	 * when specifying the path where to save and delete files.
	 * <p>
	 * The following OSs can be verified, but in this project, it is sufficient to verify whether
	 * or not is it windows.
	 * <ul>
	 *   <li>windows : supported</li>
	 *   <li>unix : recommended</li>
	 *   <li>macos : recommended</li>
	 *   <li>solaris : not supported</li>
	 * </ul>
	 * 
	 * @return true if operating system of host is Windows, false otherwise
	 */
	protected boolean isWindows() {
		
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.contains("win")) {
			return true;
		} else {
			return false;
		}
	}
}
