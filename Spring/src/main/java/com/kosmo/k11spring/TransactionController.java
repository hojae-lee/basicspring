package com.kosmo.k11spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import transaction.TicketDAO;
import transaction.TicketDTO;
import transaction.TicketTplDAO;

@Controller
public class TransactionController {

	/*
	 servlet-context.xml에서 생성한 TicketDAO타입의 빈을 자동으로 주입받음.
	 */
	
	private TicketDAO dao;

//	@Autowired
//	public void setDao(TicketDAO dao) {
//		this.dao = dao;
//		System.out.println("@Autowired=>TicketDAO주입성공");
//	}
	
	/*
	 2.트레잭션 템플릿을 활용하여 처리
	 */
	@Autowired
	private TicketTplDAO daoTpl;
	
	public void setDaoTpl(TicketTplDAO daoTpl) {
		this.daoTpl = daoTpl;
	}

	//티켓구매 페이지
	@RequestMapping("/transaction/buyTicketMain.do")
	public String buyTicketMain() {
		return "10Transaction/buyTicketMain";
	}
	
	//티켓구매처리 페이지 : View에서 전송한 폼값을 커맨드객체를 통해 한번에 받음.
	@RequestMapping("/transaction/buyTicketAction.do")
	public String buyTicketAction(TicketDTO ticketDTO, Model model) {
		
		//티켓 구매처리를 위한 DAO 호출
		dao.buyTicket(ticketDTO);
		
		//뷰로 전달할 데이터 저장
		model.addAttribute("ticketInfo", ticketDTO);
		
		return "10Transaction/buyTicketAction";
	}
	
	@RequestMapping("/transaction/buyTicketTpl.do")
	public String buyTicketTpl() {
		return "10Transaction/buyTicketTpl";
	}
	
	@RequestMapping("/transaction/buyTicketTplAction.do")
	public String buyTicketTplAction(TicketDTO ticketDTO, Model model) {
		//티켓 구매처리를 위한 DAO호출
		boolean isBool = daoTpl.buyTicket(ticketDTO);
		System.out.println("isBool"+isBool);
		if(isBool==true) {
			model.addAttribute("successOrFail","티켓구매가 정상처리 되었습니다.");
		}
		else {
			model.addAttribute("successOrFail","티켓구매가 취소 되었습니다. 다시 시도해주세요.");
		}
		
		//뷰로 전달할 데이터 저장
		model.addAttribute("ticketInfo", ticketDTO);
		
		return "10Transaction/buyTicketTplAction";
	}
	//트랜잭션 3단계에서 사용할 DAO와 메소드 끝
	
}
