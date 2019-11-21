package springboard.controller;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springboard.command.BbsCommandImpl;
import springboard.command.DeleteActionCommand;
import springboard.command.EditActionCommand;
import springboard.command.ListCommand;
import springboard.command.ReplyActionCommand;
import springboard.command.ReplyCommand;
import springboard.command.EditCommand;
import springboard.command.ViewCommand;
import springboard.command.WrtieActionCommand;
import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;
import springboard.util.EnvFileReader;

@Controller
public class BbsController {

	BbsCommandImpl command = null;
	
	@RequestMapping("/board/list.do")
	public String list(Model model, HttpServletRequest req) {
		
		model.addAttribute("req",req);
		command = new ListCommand();
		command.execute(model);
		
		//커넥션풀로 정의된 생성자 호출
		//SpringBbsDAO dao  = new SpringBbsDAO();
		
		//드라이버와 url을 가져와서 생성자 호출
//		String drv = EnvFileReader.getValue("SpringBbsInit.properties","ora.driver");
//		String url = EnvFileReader.getValue("SpringBbsInit.properties","ora.connUrl");
//		
//		
//		SpringBbsDAO dao  = new SpringBbsDAO(drv,url);
		
		return "06Board/list";
	}
	
	//게시판 글쓰기
	@RequestMapping("/board/write.do")
	public String write(Model model) {
		
		return "06Board/write";
	}
	
	//게시판 글쓰기 처리
	@RequestMapping(value = "/board/writeAciton.do", method = RequestMethod.POST)
	public String writeAction(Model model, HttpServletRequest req,
			SpringBbsDTO dto) {
		model.addAttribute("req",req);
		model.addAttribute("springBbsDTO", dto);
		
		command = new WrtieActionCommand();
		command.execute(model);
		
		return "redirect:list.do?nowPage=1";
	}
	
	//게시판 상세보기
	@RequestMapping("/board/view.do")
	public String view(Model model, HttpServletRequest req) {
		model.addAttribute("req",req);
		command = new ViewCommand();
		command.execute(model);
		
		return "06Board/view";
	}
	
	//수정,삭제전 패스워드 확인페이지
	@RequestMapping("/board/password.do")
	public String password(Model model, HttpServletRequest req) {
		model.addAttribute("idx",req.getParameter("idx"));
		return "06Board/password";
	}
	
	//패스워드를 전송후 검증
	@RequestMapping(value = "/board/passwordAction.do", method = RequestMethod.POST)
	public String passwordAction(Model model, HttpServletRequest req) {
		String modePage = null;
		
		//파라미터받기
		String mode = req.getParameter("mode");
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		String pass = req.getParameter("pass");
		
		//커넥션풀을 이용한 DAO
		SpringBbsDAO dao = new SpringBbsDAO();
		

		int rowExist = dao.password(idx,pass);
		dao.close();
		
		if(rowExist<=0) {
			model.addAttribute("isCorrMsg","패스워드가 일치하지 않습니다");
			model.addAttribute("idx",idx);
			modePage = "06Board/password";
		}
		else {
			if(mode.equals("edit")) {
				//수정이면 수정폼으로 이동한다.
				model.addAttribute("req",req);
				command = new EditCommand();
				command.execute(model);
				
				modePage = "06Board/edit";
			}
			else if(mode.equals("delete")) {
				//삭제면 삭제처리 후 리스트로 이동한다.
				model.addAttribute("req",req);
				command = new DeleteActionCommand();
				command.execute(model);
				
				model.addAttribute("nowPage",req.getParameter("nowPage"));
				modePage = "redirect:list.do";
			}
		}
		return modePage;
	}
	
	@RequestMapping(value = "/board/editAction.do", method = RequestMethod.POST)
	public String EditAction(HttpServletRequest req, Model model, SpringBbsDTO springBbsDTO) {
		
		//커맨드객체로 받은 폼값 확인하기
		System.out.println("springBbsDTO[제목]="+springBbsDTO.getTitle());
		
		model.addAttribute("req",req);
		model.addAttribute("springBbsDTO", springBbsDTO);
		command = new EditActionCommand();
		command.execute(model);
		
		/*
		 redirect를 이용해서 페이지 이동을 할 때 쿼리스트링을 연결하려면 아래와 같이
		 model에 속성을 저장하면 된다.
		 즉 view.do?idx=xx&nowPage=xx 와 같은 형태로 이동한다.
		 */
		
		//수정처리후 상세보기 페이지로 이동함
		model.addAttribute("idx",req.getParameter("idx"));
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		//뷰호출이 아니고 페이지이동임
		return "redirect:view.do";
	}
	
	@RequestMapping("/board/reply.do")
	public String reply(HttpServletRequest req, Model model) {
		System.out.println("reply()메소드호출");
		
		model.addAttribute("req",req);
		command = new ReplyCommand();
		command.execute(model);
		
		model.addAttribute("idx", req.getParameter("idx"));
		return "06Board/reply";
	}
	
	@RequestMapping(value = "/board/replyAction.do", method = RequestMethod.POST)
	public String replyAction(HttpServletRequest req, Model model, SpringBbsDTO springBbsDTO) {
		System.out.println("replyAction() 메소드 호출");
		
		/*
		 답변글쓰기폼에서 폼값을 커맨드 객체를 이용하여 한 번에 받아 전달하기
		 */
		
		model.addAttribute("springBbsDTO",springBbsDTO);
		
		model.addAttribute("req",req);
		command = new ReplyActionCommand();
		command.execute(model);
		
		//답변글 작성후에는 리스트로 이동
		model.addAttribute("nowPage", req.getParameter("nowPage"));
		return "redirect:list.do";
		
	}
	
	
}
