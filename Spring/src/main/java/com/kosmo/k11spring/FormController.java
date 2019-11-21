package com.kosmo.k11spring;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import common.MemberDTO;

/*
 사용자의 요청을 제일 먼저 받는 DispatcherServlet은 기본 패키지인
 com.kosmo.k11spring을 스캔하여 컨트롤러 클래스를 찾는다. 그리고 해당 요청명에
 매핑되는 메소드를 찾아 실행하게 된다.
 요청명의 매핑은 @RequestMapping 어노테이션을 사용한다.
 */

/*
 @Controller 어노테이션
 	: 해당 클래스를 컨트롤러로 사용하고 싶을때 클래스명 앞에 선언한다.
 	패키지를 스캔할때 해당 어노테이션이 있는 클래스를 찾게된다.
 */

@Controller
public class FormController {
	
	/*
	 @RequestMapping 어노테이션
	 	: 요청명을 매핑한다. 요청명은 컨택스트 루트를 제외한 나머지 경로명으로 이루어진다.
	 	요청명의 매핑정보를 통해 메소드를 호출하게 되므로 메소드명은 큰 의미가 없다.
	 	개발자가 구분하기 좋은 정도의 이름으로 설정한다.
	 */
	@RequestMapping("/form/servletRequest")
	public String loginMember(HttpServletRequest req, Model model) {
		
		System.out.println("하이");
		
		/*
		 폼값받기1] 파라미터로 전달된 값을 HttpServletRequest객체를 통해서
		 받는다. JSP나 Servlet에서 받는것과 동일한 방법으로 받을 수 있따.
		 */
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		/*
		 View영역으로 전송할 데이터를 Model객체에 저장한다.
		 JSP에서 사용하는 영역과 동일하다 생각하면 된다.
		 */
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		model.addAttribute("message","로그인 정보가 전달되었습니다.");
		
		/*
		 View페이지명을 반환한다.
		 아래처럼 뷰경로를 반환하면 ViewResolver가 경로를 조립하여 해당 View를 
		 웹브라우져에 로딩하게 된다.
		 (설정파일 : servlet-context.xml)
		 */
		
		return "01Form/servletRequest";
	}
	
	/*
	 폼값 받기2] @RequestParam 어노테이션으로 폼값받기
	 파라미터 형식으로 아래와같이 사용한다. 이와같이 하면 해당 메소드내에서
	 변수명을 그대로 사용할 수 있다.
	 */
	
	@RequestMapping("/form/requestParam.do")
	public String joinMember(HttpServletRequest req, Model model,
			@RequestParam("name") String name, @RequestParam("id") String id,
			@RequestParam("email") String email, @RequestParam("pw") String pw) {
		
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setName(name);
		memberDTO.setEmail(email);
		memberDTO.setPw(pw);
		
		//Model에서 DTO객체를 저장한다.
		model.addAttribute("memberInfo", memberDTO);
		
		return "01Form/requestParam";
	}
	
	/*
	 폼값받기3 ] 커맨드객체를 이용해서 폼값 한번에 받기
	 조건 : 쿼리스트링으로 전달되는 파라미터의 갯수와 폼값을 저장할 객체(DTO,VO)
	 의 멤버변수의 갯수가 동일할 때 사용가능함.
	 - 커맨드객체를 사용할때는 클래스명 앞글자를 소문자로 바꾼 형태의 매개변수를 사용해야
	 한다.
	 MemberDTO - >memberDTO
	 
	 -커맨드객체를 만들때 파라미터의 갯수는 상관없으나 파라미터의 이름과
	 멤버변수의 이름은 반드시 동일해야 한다,
	 그리고 getter/setter가 반드시 생성되어야 한다.
	 
	 ※커맨드객체의 이름을 변경에서 View로 전달하고 싶을떄는
	 @ModelAttribute 어노테이션을 사용하면 된다.
	 */
	@RequestMapping("form/commandObjGet.do")
	public String commandObjGet(MemberDTO memberDTO) {
		
		return "01Form/commandObjGet";
	}
	
	/*
	 폼값받기4] @PathVariable 어노테이션으로 폼값 받기
	 	요청명 ./form 뒤에 붙는 값이 메소드에서 사용가능한 파라미터가 된다.
	 	아래의 경우 2개의 파라미터를 받아서 사용하게 된다.
	 	단 웹브라우저는 요청명을 경로로 인식하므로 리소스(이미지 등)를 사용할 때
	 	경로에 주의해야 한다.
	 	파라미터의 갯수가 틀릴경우 404에러가 발생된다.
	 */
	@RequestMapping("/form/{memberId}/{memberName}")
	public String pathVariable(Model model, 
			@PathVariable String memberId, 
			@PathVariable String memberName) {
		
		model.addAttribute("memberid", memberId);
		model.addAttribute("memberpw", memberName);
		
		return "01Form/pathVariable";
	}

}
