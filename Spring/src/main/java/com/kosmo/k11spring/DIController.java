package com.kosmo.k11spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import di.CalculatorDTO;

@Controller
public class DIController {

	//간단한사칙연산 계산기 구현
	@RequestMapping("di/myCalculator")
	public String myCal(Model model) {
		
		/*
		 ApplicationContext 파일의 위치를 문자열에 저장한다.
		 물리적 경로는 /src/main/resources 디렉토리 하위임.
		 */
		String configLocation = "classpath:DIAppCtxCalculator.xml";
		
		/*
		 스프링 컨테이너 생성 : xml파일을 파싱하여 파싱된 내용을
		 기반으로 ctx 참조변수에 할당한다.
		 */
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		
		/*
		 XML설정파일에서 생성한 빈(Bean)을 getBean()메소드를 통해
		 주입받아 참조변수에 할당한다. new 연산자를 통해 생성한것과
		 동일하지만 생성된것을 해당 클래스로 주입(Injection) 받은 것임에 유의
		 */
		CalculatorDTO myCal = ctx.getBean("myCal", CalculatorDTO.class);
		
		/*
		 주입받은 빈을 통해 해당클래스에 정의된 메소드를 호출하여     연산을 진행한다.
		 */
		model.addAttribute("addResult", myCal.add());
		model.addAttribute("subResult", myCal.sub());
		model.addAttribute("mulResult", myCal.mul());
		model.addAttribute("divResult", myCal.div());
		
		return "04DI/myCalculator";
	}
}
