package com.kosmo.k11spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import aop.AopDTO;
import aop.GoodsDAO;
import aop.GoodsDTO;
import aop.StaticDAO;

@Controller
public class AOPController {
	
	/*
	 servlet-context.xml에서 생성된 JdbcTemplate 빈을 자동주입 후 페이지 전체에서 
	 사용할 수 있는 static타입의 변수에 재 할당.
	 */
	
	private JdbcTemplate template;
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		StaticDAO.template = this.template;
	}

	@RequestMapping("/aop/main.do")
	public ModelAndView aop1() {
		
		//aop패키지 하위에 XML설정파일을 생성함.
		String xml = "classpath:aop/AopContext.xml";
		//XML설정파일을 통해 컨테이너를 생성함.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(xml);
		
		AopDTO dto = ctx.getBean("dto", AopDTO.class);
		//빈의 정보 출력
		dto.showInfo();
		ctx.close();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/12Aop/main");
		return mv;
	}
	
	@RequestMapping("/aop/insert.do")
	public String insertForm() {
		
		return "/12Aop/insert";
	}
	
	@RequestMapping("/aop/insertAction.do")
	public String insertAction(Model model, HttpServletRequest req) {
		String xml = "classpath:aop/AopContext.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(xml);
		
		GoodsDTO dto = new GoodsDTO();
		dto.setGoods_name(req.getParameter("goods_name"));
		dto.setGoods_price(req.getParameter("goods_price"));
		dto.setP_code(req.getParameter("p_code"));
		System.out.println("1"+req.getParameter("goods_name"));
		System.out.println("2"+req.getParameter("goods_price"));
		System.out.println("3"+req.getParameter("p_code"));
		
		GoodsDAO dao = ctx.getBean("goodsDAO", GoodsDAO.class);
		int result = dao.goodInsert(dto);
		System.out.println("상품입력결과: "+ result);
		return "12Aop/insertAction";
	}
}
