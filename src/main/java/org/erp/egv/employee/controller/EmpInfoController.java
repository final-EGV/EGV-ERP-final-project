package org.erp.egv.employee.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.employee.model.dto.AuthorityDTO;
import org.erp.egv.employee.model.dto.DepartmentDTO;
import org.erp.egv.employee.model.dto.EmpRankDTO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.ParttimeScheduleDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/emp")
public class EmpInfoController {

	private EmpInfoService empInfoService;

	@Autowired
	public EmpInfoController(EmpInfoService empInfoService) {
		this.empInfoService = empInfoService;
	}

	/* 관리자용 전체 재직 사원조회 */
	@GetMapping("/list")
	public ModelAndView empListRequest(ModelAndView mv) {
		
		System.out.println("콘트롤러 오나요?");
		
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/emplist");
		
		return mv;
	}
	
	/* 일반 사용자용 전체 재직 사원조회 */
	@GetMapping("/listUs")
	public ModelAndView empListForUserRequest(ModelAndView mv) {
		
		System.out.println("콘트롤러 오나요?");
		
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/emplistUs");
		
		return mv;
	}
	
	/* 전체 퇴사 사원조회 */
	@GetMapping("/outEmpList")
	public ModelAndView empOutListRequest(ModelAndView mv) {
		
		System.out.println("콘트롤러 오나요?");
		List<EmployeeDTO> empList = empInfoService.empOutListRequest();
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/empOutList");
		
		return mv;
	}
	
	/* 사원정보조회 */
	@GetMapping("/empInfor")
	public ModelAndView empOneRequest(ModelAndView mv, @RequestParam String empCode) {
		System.out.println("콘트롤러 one 오나요?");
		
		EmployeeDTO empInfor = empInfoService.empOneRequest(empCode);
		
		mv.addObject("empInfor", empInfor);
		mv.setViewName("emp/empInfor");
		
		return mv;
	}
	
	@GetMapping(value="departmentList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<DepartmentDTO> findDepartmentList(){
		return empInfoService.findDepartmentList();
	}
	
	@GetMapping(value="empRankList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<EmpRankDTO> findEmpRankList(){
		return empInfoService.findEmpRankList();
	}
	
	@GetMapping(value = "empList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<EmployeeDTO> findEmpList() {
		return empInfoService.empListRequest();
	}
	
	
	/* Date : 2021/11/25
	 * Writer : Hansoo Lee
	 * 
	 * 사번조합기를 사용하여 사번등록, 비밀번호는 '사번 + 생년월일'로 조합할 것이므로 새로 세팅해준다.  
	 * 서비스에서 시큐리티로 비밀번호를 암호화 해주어야 한다.
	 * 
	 * 조인컬럼은 새로 객체를 만들어서 set 해주자!
	 * */
	
	@PostMapping("/registEmp")
	public ModelAndView empRegistRequest(ModelAndView mv, EmployeeDTO newEmp, @RequestParam int rankRegist, @RequestParam int deptRegist,  RedirectAttributes rttr) {
		System.out.println("콘트롤러 regist 오나요?");
		
		DepartmentDTO departmentDTO = new DepartmentDTO();
		EmpRankDTO empRankDTO = new EmpRankDTO();
		
		departmentDTO.setCode(deptRegist);
		empRankDTO.setCode(rankRegist);

		newEmp.setDept(departmentDTO);
		newEmp.setRank(empRankDTO);
		
		newEmp.setCode(empInfoService.findNextEmpNum());
		newEmp.setPwd(empInfoService.findNextEmpNum() + newEmp.getRrn().substring(0,6));

		empInfoService.empRegistRequest(newEmp);
		rttr.addFlashAttribute("successMessage", "사원 등록 성공!!");
		mv.setViewName("redirect:/emp/list");
		
		return mv;
	}
	
	/* Date : 2021/11/25
	 * Writer : Hansoo Lee
	 * 
	 * 회원정보수정
	 * */
	@PostMapping("/modifyInfor")
	public ModelAndView empModifyInforRequest(ModelAndView mv, EmployeeDTO modifyInfor, @RequestParam int rankRegist, @RequestParam int deptRegist,  RedirectAttributes rttr) {
		System.out.println("콘트롤러 modify 오나요?");
		DepartmentDTO departmentDTO = new DepartmentDTO();
		EmpRankDTO empRankDTO = new EmpRankDTO();
		
		empRankDTO.setCode(rankRegist);
		departmentDTO.setCode(deptRegist);

		modifyInfor.setDept(departmentDTO);
		modifyInfor.setRank(empRankDTO);

		empInfoService.modifyInforRequest(modifyInfor);
		
		rttr.addFlashAttribute("successMessage", "사원 정보 수정 성공!!");
		mv.setViewName("redirect:/emp/list");
		
		return mv;
	}
	
	/* Date : 2021/11/25
	 * Writer : Hansoo Lee
	 * 
	 * 퇴사처리용 컨트롤러
	 * 바로 퇴사되는것이 아니라 퇴직금을 받을때까지는 계류 되므로
	 * 진짜 퇴사처리는 퇴직금 항목에서 다룰것이고,
	 * 여기서는 퇴사신청 날자와 퇴사사유를 DB에 넣어 주는 작업을 할 것 이다.
	 * 퇴사사유는 상당히 기므로 POST로 요청하자
	 * */
	@PostMapping("/out")
	public ModelAndView empOUTRequest(ModelAndView mv, @RequestParam String code,  @RequestParam String reason, RedirectAttributes rttr) {
		System.out.println("콘트롤러 modify 오나요?");
		
		empInfoService.empOUTRequest(code, reason);
		rttr.addFlashAttribute("successMessage", "퇴사 신청 완료!");
		mv.setViewName("redirect:/emp/list");
		
		return mv;
	}
	
	/* Date : 2021/11/26
	 * Writer : Hansoo Lee
	 * 
	 * 프로필 사진 등록 변경룔 컨트롤러
	 * */
	@PostMapping("/profilePicInsert")
	public ModelAndView empProfilePicInsert(ModelAndView mv, @RequestParam String code, @RequestParam("proPicThumb") MultipartFile singleFile,  RedirectAttributes rttr) throws UnsupportedEncodingException {

		String srcRootPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\";
		String filePath = srcRootPath + "profileImg/";

		/* 폴더 생성 */
		File mkdir = new File(filePath);
		if (!mkdir.exists()) {
			System.out.println("폴더 생성 : " + mkdir.mkdir());
		}
		
		/* 파일명 변경처리 */
		String originFileName = new String(singleFile.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("원본 이름 : " + originFileName);
		
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("변경한 이름 : " + saveName);

		/* 파일 저장 */
		try {
			singleFile.transferTo(new File(filePath + "\\" + saveName));

			/* 사진등록 */ 
			EmployeeDTO employee = new EmployeeDTO();
			employee.setCode(code); 
			employee.setProfileImgName(filePath+saveName);
			employee.setProfileOrigName(originFileName);
			employee.setProfileUuidName(saveName);
			
			empInfoService.empProfilePicInsert(employee);
			
			/* 회원 정보 조회 */
			EmployeeDTO empStampinfo = empInfoService.empOneRequest(code);
			System.out.println("프로필 등록 사원 : " + empStampinfo);
			
			String imgPath = "/prfile : /" + empStampinfo.getProfileUuidName();
			System.out.println("imgNewPath : " + imgPath);
			
//			mv.addObject("imgPath", imgPath);
			
			rttr.addFlashAttribute("successMessage", "사진등록성공!!!");
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			/* 실패 시 업로드 된 파일 삭제 */
			new File(filePath + "\\" + saveName).delete();
			rttr.addFlashAttribute("message", "사진등록 실패!!");
		}

//		empInfoService.empProfilePicInsert(code);
		
		rttr.addFlashAttribute("message", "사진등록 완료");
		
		
		mv.setViewName("redirect:/emp/profilePic/profileInseter?empCode="+ code);
		
		return mv;
	}
	
	/* 프로필 사진 입력 윈도우*/
	@GetMapping("/profilePic/profileInseter")
	public ModelAndView empPic(ModelAndView mv, @RequestParam String empCode) {
		EmployeeDTO empInfor = empInfoService.empOneRequest(empCode);
		mv.addObject("empInfor", empInfor);
		mv.setViewName("emp/profilePic/profileInseter");
		return mv;
	}
	
	
	/* 알바 스케줄 페이지 연결*/
	@GetMapping("/parttime/parttimeSchedule")
	public ModelAndView parttime(ModelAndView mv) {
		
		List<ParttimeScheduleDTO> parttimer = empInfoService.findParttimeScheduleList();
		mv.addObject("parttimer", parttimer);
		mv.setViewName("emp/parttime/parttimeSchedule");
		return mv;
	}
	
	/* 알바 스케줄 데이터 가져오기*/
	@GetMapping(value="/parttime/parttimeScheduleList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ParttimeScheduleDTO> parttimeScheduleList() {
		return empInfoService.findParttimeScheduleList();
	}
	
	/* 알바 스케줄 등록 */
	@PostMapping("registParttime")
	public ModelAndView registParttimeRequest(ModelAndView mv, ParttimeScheduleDTO parttimeSchedule, @RequestParam String empCode, RedirectAttributes rttr){
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setCode(empCode);
		String parttimerName = empInfoService.empOneRequest(empCode).getName();
		parttimeSchedule.setemp(employeeDTO);
		parttimeSchedule.setTitle(parttimerName+" : " + parttimeSchedule.getParttimeDivision());
		empInfoService.registParttimeRequest(parttimeSchedule);
		rttr.addFlashAttribute("successMessage", "일정 등록 성공!!");
		mv.setViewName("redirect:/emp/parttime/parttimeSchedule");
		
		return mv;
	}
	
	/* 알바 이름 조회용 */
	@GetMapping(value="parttimerList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Object> findParttimerList(){
		
		return empInfoService.findParttimerList();
	}
	
	/* 알바 스케줄 삭제 */
	@PostMapping("parttime/delete")
	@ResponseBody
	public String deleteParttime(@RequestParam int code){

		empInfoService.deleteParttime(code);

		 String message = "스케줄 삭제완료";
		
		return message;
	}
	
	
	
	
	/* Date : 2021/11/23
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 부서 조회 컨트롤러
	 */
	@GetMapping("/dept")
	public ModelAndView departmentList(ModelAndView mv) {
		List<DepartmentDTO> deptList = empInfoService.empDeptList();
		
		mv.addObject("deptList", deptList);
		mv.setViewName("emp/dept/deptList");
		
		return mv;
	}
	
	/* Date : 2021/11/24
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 부서 세부 조회 컨트롤러
	 */
	@GetMapping("/dept/{code}")
	public ModelAndView findDeptByCode(ModelAndView mv, @PathVariable int code) {
		DepartmentDTO dept = empInfoService.findDeptByCode(code);
		
		System.out.println(dept);
		
		mv.addObject("dept", dept);
		mv.setViewName("/emp/dept/deptOne");
		
		return mv;
	}

	/* Date : 2021/11/24
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 부서 세부 조회후 수정 컨트롤러
	 */
	@PostMapping("/dept/modify")
	public String modifyDept(@ModelAttribute DepartmentDTO dept) {
		empInfoService.modifyDept(dept);

		return "redirect:/emp/dept";
	}
	
	/* Date : 2021/11/24
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 부서 추가 컨트롤러
	 */
	@PostMapping("/dept/add")
	public ModelAndView addNewDept(ModelAndView mv, DepartmentDTO newDept, Locale locale) {
		
		empInfoService.addNewDept(newDept);
		System.out.println("newDept : " + newDept);
		mv.setViewName("redirect:/emp/dept");
		
		return mv;
	}
	
	/* Date : 2021/11/23
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 직위/직급 조회 컨트롤러
	 */
	@GetMapping("/rank")
	public ModelAndView rankList(ModelAndView mv) {
		List<EmpRankDTO> rankList = empInfoService.empRankList();
		
		mv.addObject("rankList", rankList);
		mv.setViewName("emp/rank/rankList");
		
		return mv;
	}
	
	/* Date : 2021/11/24
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 직위/직급 세부 조회 컨트롤러
	 */
	@GetMapping("/rank/{code}")
	public ModelAndView findRankByCode(ModelAndView mv, @PathVariable int code) {
		EmpRankDTO rank = empInfoService.findRankByCode(code);
		
		System.out.println(rank);
		
		mv.addObject("rank", rank);
		mv.setViewName("/emp/rank/rankOne");
		
		return mv;
	}
	
	/* Date : 2021/11/24
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 직위/직급 세부 조회후 수정 컨트롤러
	 */
	@PostMapping("/rank/modify")
	public String modifyRank(@ModelAttribute EmpRankDTO rank) {
		empInfoService.modifyRank(rank);
		
		return "redirect:/emp/rank";
	}
	
	@GetMapping("/login")
	public void empLogin() {}
	
	@GetMapping("/findid")
	public void findId() { }
	
	@PostMapping(value="findid", produces="application/json; charset=UTF-8")
	@ResponseBody
	public EmployeeDTO findId2(@RequestParam("name") String name,@RequestParam("birth") String birth,@RequestParam(
			"email") String email) {
		String birtha = birth.substring(2, 4) + birth.substring(5, 7) + birth.substring(8, 10);
		EmployeeDTO emp = empInfoService.finId(email);
				
		if(emp != null) {
			System.out.println("이메일 일치");
			if (emp.getName().equals(name)) {
				System.out.println("이름 일치");
				if(emp.getRrn().substring(0, 6).equals(birtha)) {
					System.out.println("생일 일치");
					emp.setEmployeeRoleList(null);
				} else {
					emp = null;
				}
			} else {
				emp = null;
			}
		} else {
			emp = null;
		}
		
		return emp;
	}
	
	@GetMapping("/pwreset")
	public void pwreset() { }
	
	
	@PostMapping(value="repw", produces="application/json; charset=UTF-8")
	@ResponseBody
	public EmployeeDTO resetPw(@RequestParam("code") String code, @RequestParam("name") String name,@RequestParam("birth") String birth,@RequestParam(
			"email") String email) {
		String birtha = birth.substring(2, 4) + birth.substring(5, 7) + birth.substring(8, 10);
		EmployeeDTO emp = empInfoService.empOneRequest(code);
		
		if((emp != null)) {
			if (!(emp.getName().equals(name) && emp.getRrn().substring(0, 6).equals(birtha) && emp.getEmail().equals(email))) {
				emp = null;
			} else {
				String repw = emp.getCode() + birtha;
				emp = empInfoService.resetPw(code, repw);
				emp.setEmployeeRoleList(null);
			}
		} 
		
		return emp;
		
	}	
	
	@PostMapping("pwchange")
	public String pwChange(HttpServletRequest request, RedirectAttributes redirect, Principal principal) {
		String referer = request.getHeader("Referer");		// 접속 경로 얻는 메소드
		
		String nowPw = request.getParameter("nowpw");
		String newPw = request.getParameter("newpw");
		String checkPw = request.getParameter("checkpw");
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		
		if(!newPw.equals(checkPw)) {
			redirect.addFlashAttribute("pwchangeresult", "확인 비밀번호가 일치하지 않습니다.");
		} else if(!empInfoService.pwMatch(nowPw, empCode)) {
			redirect.addFlashAttribute("pwchangeresult", "현재 비밀번호가 일치하지 않습니다.");
		} else {
			empInfoService.changePw(newPw, empCode);
			redirect.addFlashAttribute("pwchangeresult", "비밀번호가 변경되었습니다.");
		}
		
		return "redirect:" + referer;
		
	}
	
	@PostMapping("authority")
	public ModelAndView empAuthority(ModelAndView mv, @RequestParam String code,  @RequestParam String authority, RedirectAttributes rttr) {
		String aut = "ROLE_" + authority;
		AuthorityDTO authorit = empInfoService.selectRole(aut);
		EmployeeDTO emp = empInfoService.empOneRequest(code);
	
		if(authorit.getName().equals("ROLE_Admin")) {
			empInfoService.empAdminAuthority(emp, authorit); 
		} else {
			empInfoService.empAuthority(emp, authorit);
		}
		
		rttr.addFlashAttribute("successMessage", "권한 설정 완료");
		mv.setViewName("redirect:/emp/list");
		
		return mv;
	}
	
	
}
