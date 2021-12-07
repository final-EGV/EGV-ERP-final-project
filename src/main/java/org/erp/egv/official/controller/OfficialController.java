package org.erp.egv.official.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.official.model.dto.OfficialDTO;
import org.erp.egv.official.model.dto.OfficialFileDTO;
import org.erp.egv.official.model.service.OfficialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/official/*")
public class OfficialController {

	private OfficialService officialService;

	@Autowired
	public OfficialController(OfficialService officialService) {
		this.officialService = officialService;
	}

	/*   Date : 2021/12/04
	 * Writer : Hansoo Lee
	 * 공문 리스트 받아오기
	 * */
	@GetMapping("/list")
	public ModelAndView officialListRequest(ModelAndView mv) {

		List<OfficialDTO> officialList = officialService.officialListRequest();
		mv.addObject("officialList", officialList);
		mv.setViewName("/official/officialList");

		return mv;
	}

	@GetMapping("/officialContext")
	public void officialContentWrite() {
	}
	
	
	/*   Date : 2021/12/08
	 * Writer : Hansoo Lee
	 * 공문 작성
	 * */
	@PostMapping("/write")
	public ModelAndView officialContentWrite(ModelAndView mv, OfficialDTO official, @RequestPart(value="files", required=false) List<MultipartFile> files,
		      Principal principal, RedirectAttributes rttr) throws UnsupportedEncodingException {
		
		int nextOfficialCode = officialService.findNextCode();
		EmployeeDTO emp = new EmployeeDTO();
		emp.setCode(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		
		
		official.setCode(nextOfficialCode);
		official.setEmp(emp);
		if ( files.get(0).getSize() > 0) {
		
		String srcRootPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
		String filePath = srcRootPath + "officialFiles/";
		
		/* 폴더 생성 */
		File mkdir = new File(filePath);
		if (!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		int fileCount = files.size()-1;
		List<OfficialFileDTO> officialFiles = new ArrayList<>();
		for (int i = 0 ; i < fileCount; i++) {
			
			/*   Date : 2021/12/08
			 * Writer : Hansoo Lee
			 * 
			 * JSP와 다르게 UTF-8로 설정하지 않아도 한글이 잘나온다.
			 * 괜히 String originFileName = new String(singleFile.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8"); 쓰지 말것
			 * */
			String originFileName = new String(files.get(i).getOriginalFilename());
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
			System.out.println(originFileName);
			OfficialFileDTO officialFileDTO = new OfficialFileDTO();
			
			/* 파일에 관한 정보 추출 후 보관 */
			officialFileDTO.setOfficial(official);
			officialFileDTO.setOfficialImgName(filePath+saveName);
			officialFileDTO.setOfficialOrigName(originFileName);
			officialFileDTO.setOfficialUuidName(saveName);
			
			officialFiles.add(officialFileDTO);
		}
		
		try {
			for (int i = 0 ; i < officialFiles.size() ; i++) {
				String file = officialFiles.get(i).getOfficialUuidName();
				files.get(i).transferTo(new File(filePath + "\\" + file));
			}
			
			official.setOfficialFiles(officialFiles);
			officialService.officialContentWrite(official);
		
			/* DB 다녀오는 비지니스 로직 수행 */
			rttr.addFlashAttribute("message", "공문 등록 성공!!");	
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			for (int i = 0 ; i < officialFiles.size() ; i++) {
				String file = officialFiles.get(i).getOfficialUuidName();
				new File(filePath + "\\" + file).delete();
			}
			
			rttr.addFlashAttribute("message", "공문 등록 실패!!");
		}}
		officialService.officialContentWrite(official);
		rttr.addFlashAttribute("message", "공문 등록 성공!!");	
		mv.setViewName("redirect:/official/list");
		return mv;
	}
	
	/*   Date : 2021/12/05
	 * Writer : Hansoo Lee
	 * 공문 내용 보기
	 * */
	@GetMapping("/officialDetail")
	public ModelAndView officialDetailRequest(ModelAndView mv, @RequestParam int code) {

		OfficialDTO officialDetail = officialService.officialDetailRequest(code);
		mv.addObject("officialDetail", officialDetail);
		mv.setViewName("/official/officialDetail");

		return mv;
	}

	@PostMapping("/delete")
	public ModelAndView officialDeleteRequest(ModelAndView mv, @RequestParam int code) {

		officialService.officialDeleteRequest(code);
		mv.setViewName("redirect:/official/list");

		return mv;
	}
	
	
}
