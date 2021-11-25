package org.erp.egv.sign.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
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
		List<ApproverDTO> signList = signService.selectwatingsignList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		Iterator<ApproverDTO> iter = signList.iterator();
		
		while (iter.hasNext()) {
			ApproverDTO app = iter.next();
			if(app.getOrder() != 1) {
				List<ApproverDTO> appList = signService.selectwatingsignList2(app.getSign().getCode());
				for (int i = 1; i < app.getOrder(); i++) {
					if(appList.get(i-1).getStatus().equals("대기") || appList.get(i-1).getStatus().equals("반려")) {
						iter.remove();
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
		List<ApproverDTO> signList = signService.selectSignHestory(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/received/signhistory");
		return mv;
	}
	
	@GetMapping("referencesign")
	public ModelAndView selectReferenceSign(ModelAndView mv, Principal principal) {
		List<RefferrerDTO> signList = signService.selectReferenceSign(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/received/referencesign");
		return mv;
		
	}

}
