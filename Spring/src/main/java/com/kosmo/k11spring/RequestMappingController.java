package com.kosmo.k11spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import common.StudentDTO;

@Controller
public class RequestMappingController {

	/*
	 @RequestMapping(method=RequestMethod.get[post], value="/요청명1/요청명2")
	 
	 method : 요청시 전송방식을 명시함
	 value : 요청명을 명시함
	 즉 요청명과 전송방식을 만족해야 메소드가 호출되는 방식으로 동작됨.
	 서블릿의 doGet/doPost와 같은 방식
	 -method를 명시하지 않으면 디폴트로 GET방식이 됨.
	 */
	
	@RequestMapping("/requestMapping/index.do")
	public String rmIndex() {
		
		return "02RequestMapping/index";
	}
	
	/*
	 단순히 요청명만 있는 경우에는 value, method와 같은 속성을 제외하고 사용해도 되지만
	 전송방식까지 명시해야 하는 경우에는 속성을 제거하면 오류가 발생된다.
	 */
	@RequestMapping(value ="/requestMapping/getSearch.do", method = RequestMethod.GET)
	public String getSearch(HttpServletRequest req, Model model) {
		
		System.out.println("RequestMethod.GET방식으로 폼값전송");
		
		String sColumn = req.getParameter("searchColumn");
		String sWord = req.getParameter("searchWord");
		
		model.addAttribute("sColumn", sColumn);
		model.addAttribute("sWord", sWord);
		
		return "02RequestMapping/getSearch";
	}
	
	/*
	 ModelAndView객체
	 	: 뷰로 전송할 데이터의 저장과 뷰를 호출하는 2가지 로직을
	 	동시에 처리할 수 있는 클래스
	 	
	 	사용법
	 		참조변수.setViewName("뷰의경로 및 파일명");
	 			-> View설정
	 		참조변수.addObject("속성명", 속성값);
	 		ModelAndView의 객체를 반환함.
	 */
	@RequestMapping(value = "/requestMapping/postLogin.do", method = RequestMethod.POST)
	public ModelAndView postLogin(
			@RequestParam("user_id") String id,
			@RequestParam("user_pw") String pw) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("02RequestMapping/postLogin");
		mv.addObject("id",id);
		mv.addObject("pw",pw);
		
		return mv;
	}
	
	/*
	 @ModelAttribute 어노테이션
	 	: 뷰로 전달되는 커맨드 객체의 이름을 임의로 변경하고 싶을때
	 	사용한다. 아래코드는 studentDTO -> si로 변경한 후 View로 데이터를 전달한다.
	 */
	@RequestMapping("/requestMapping/modelAttribute.do")
	public String studentInfo(
			@ModelAttribute("si") StudentDTO studentDTO) {
		
		return "02RequestMapping/modelAttribute";
	}
	
}
