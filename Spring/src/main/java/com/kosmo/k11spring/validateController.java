package com.kosmo.k11spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import common.MemberDTO;
import common.MemberValidator;

@Controller
public class validateController {

	//회원가입페이지 매핑하기
	@RequestMapping("/validate/memberRegist.do")
	public String memberRegist() {
		
		return "03Validate/memberRegist";
	}
	
	//회원가입페이지의 폼값을 전송받아 검증하는 메소드
	@RequestMapping("/validate/registProc")
	public String registProc(
		@ModelAttribute("mInfo") MemberDTO memberDTO, BindingResult result, Model model	) {
		
		//폼값검증 완료시 이동할 페이지의 요청명(경로명)
		String viewPage = "03Validate/memberDone";
		
		/*
			유효성검증을 위해 정의한 클래스의 객체를 생성한 후 전송된
			폼값을 저장한 커맨드객체를 통해 폼값의 유효성체크를 실시한다.
			매개변수로 유효성체크를 할 커맨드객체와 바인딩결과(검증결과)
			를 저장할 객체를 전달한다.
		 */
		MemberValidator validator = new MemberValidator();
		validator.validate(memberDTO, result);
		
		if(result.hasErrors()) {
			System.out.println("유효성체크 실패 :"+result.toString());
			model.addAttribute("formError", "폼값 유효성체크에 실패하였습니다.");
			viewPage = "03Validate/memberRegist";
		}
		return viewPage;
		
	}
	
	
}
