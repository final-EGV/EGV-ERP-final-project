package org.erp.egv.notice.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.notice.model.dto.NoticeDTO;
import org.erp.egv.notice.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/community/notice")
public class NoticeController {

	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	/* 공지사항 리스트 조회 */
	@GetMapping("/list")
	public ModelAndView noticeAllList(ModelAndView mv) {

		System.out.println("check Contorller");

		List<NoticeDTO> noticeList = noticeService.noticeAllList();

		mv.addObject("noticeList", noticeList);
		mv.setViewName("notice/noticeList");

		return mv;
	}

	/* 공지사항 세부내용 */
	@GetMapping("/detail/{noticeCode}")
	public ModelAndView noticeDetail(ModelAndView mv, @PathVariable int noticeCode) {

		System.out.println("checkController");
		System.out.println(noticeCode);

		NoticeDTO noticeDetail = noticeService.selectNoticeDetail(noticeCode);

		mv.addObject("noticeDetail", noticeDetail);
		mv.setViewName("notice/noticeDetail");
		return mv;
	}

	/* 공지사항 삭제 */
	@GetMapping("/delete")
	public ModelAndView deleteNotice(ModelAndView mv, RedirectAttributes rAttr, @RequestParam int noticeCode) {
		System.out.println(noticeCode);

		noticeService.deleteNotice(noticeCode);

		rAttr.addFlashAttribute("flashMessage", noticeCode + "번 영화 삭제를 성공했습니다.");

		mv.setViewName("redirect:list");

		return mv;
	}

	/* 공지사항 수정 */
	public ModelAndView modifyMovie(HttpServletRequest request,
			ModelAndView mv, RedirectAttributes rAttr) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");

		int noticeCode = Integer.valueOf(request.getParameter("noticeCode"));
		String noticeTitle = request.getParameter("noticeTitle");
		String insertDate = request.getParameter("insertDate");
		String noticeContent = request.getParameter("content");

		System.out.println("noticeCode : " + noticeCode);
		System.out.println("noticeTitle : " + noticeTitle);
		System.out.println("insertDate : " + insertDate);
		System.out.println("content : " + noticeContent);

		NoticeDTO noticeModify = new NoticeDTO();
		noticeModify.setNoticeCode(noticeCode);
		noticeModify.setTitle(noticeTitle);
		/* noticeModify.setDate (insertDate); */
		noticeModify.setContent(noticeContent);

		String pathToRedirect = "";

		noticeService.modifyNotice(noticeModify);

		rAttr.addFlashAttribute("flashMessage", "공지사항 수정을 성공했습니다.");

		pathToRedirect = "list";

		mv.setViewName("redirect:" + pathToRedirect);

		return mv;
	}

	/* 공지사항 작성 */
	@GetMapping("/insert")
	public String insertNotice() {

		return "notice/noticeInsert";
	}

	@PostMapping("/insert")
	public ModelAndView insertNotice(ModelAndView mv, HttpServletRequest request, EmployeeDTO empCode)
			throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");

		String title = request.getParameter("title");
		java.sql.Date noticeInsertDate = new java.sql.Date(System.currentTimeMillis());

		String content = request.getParameter("content");
		String userEmpCode = request.getParameter("userEmpCode");

		System.out.println("title : " + title);
		System.out.println("noticeInsertDate : " + noticeInsertDate);
		System.out.println("content : " + content);
		System.out.println("userEmpCode : " + userEmpCode);

		NoticeDTO notice = new NoticeDTO();
		notice.setNoticeCode(1);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setDate(noticeInsertDate);
		notice.setEmployee(empCode);

		System.out.println(notice);

		noticeService.insertNotice(notice);

		mv.setViewName("redirect:/notice/noticeList");
		return mv;

	}

}
