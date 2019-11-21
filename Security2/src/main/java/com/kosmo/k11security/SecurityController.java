package com.kosmo.k11security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/security1/index.do")
	public String security1() {
		
		return "08Security/Step1/index";
	}
	@RequestMapping("/security1_1/index.do")
	public String security1_1() {
		
		return "08Security/Step1/access";
	}

	
	
	
	//Step2 : 커스텀 페이지 사용	
	@RequestMapping("/security2/index.do")
	public String securityIndex2()
	{		
		//커스텀 인덱스 페이지
		return "08Security/Step2/index";
	}
	@RequestMapping("/security2/login.do")
	public String securityIndex2Login()
	{		
		//로그인 페이지
		return "08Security/Step2/login";
	}
	@RequestMapping("/security2/accessDenied.do")
	public String securityIndex2AccessDenied()
	{		
		//접근거부 페이지
		return "08Security/Step2/accessDenied";
	}
	@RequestMapping("/security2/admin/main.do")
	public String securityIndex2AdminMain()
	{		
		//관리자모드 메인페이지
		return "08Security/Step2/adminMain";
	}	
}
