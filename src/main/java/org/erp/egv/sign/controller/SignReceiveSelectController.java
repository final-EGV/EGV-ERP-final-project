package org.erp.egv.sign.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.service.SignReceiveSelectService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign/received/*")
public class SignReceiveSelectController {
	
	private SignReceiveSelectService signService;
	
	public SignReceiveSelectController(SignReceiveSelectService signService) {
		this.signService = signService;
	}
	
	@GetMapping("watingsign")
	public ModelAndView selectWatingsignList(ModelAndView mv, Principal principal) {
		List<SignDTO> signList = signService.selectwatingsignList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		Iterator<SignDTO> iter = signList.iterator();
		
		/* for문 안에서 List remove가 불가능(원본인 리스트 데이터가 훼손되어 에러 발생) 해서 Iterator를 사용하여야 한다. */
		while (iter.hasNext()) {
			ApproverDTO app = iter.next().getApprover().get(0);
			System.out.println(app.getOrder());
			if(app.getOrder() != 1) {
				System.out.println("test");
				List<ApproverDTO> appList = signService.selectwatingsignList2(app.getSign().getCode());
				for (int i = 1; i < appList.size(); i++) {
					System.out.println(appList.get(i-1).getStatus());
					if(appList.get(i-1).getStatus().equals("대기") || appList.get(i-1).getStatus().equals("반려")) {
						iter.remove();
						System.out.println("test2");
						break;
					}
				}
			}
		}
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/received/watingsign");
		return mv;
	}
	
	@GetMapping("signhistory")
	public ModelAndView selectSignHestory(ModelAndView mv, Principal principal) {
		List<SignDTO> signList = signService.selectSignHestory(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/received/signhistory");
		return mv;
	}

}
