package org.erp.egv.sign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign/*")
public class SignInsertController {
	
	@GetMapping("/signInsert")
	public void signInsert() {}

}
