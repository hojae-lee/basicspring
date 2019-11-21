package com.kosmo.k11mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.MemberVO;
import mybatis.MyBoardDTO;
import mybatis.MybatisDAOImpl;
import mybatis.MybatisMemberImpl;
import mybatis.ParameterDTO;
import springboard.util.PagingUtil;

@Controller
public class MybatisController {

	/*
	 servlet-context.xml에서 생성한 빈을 자동으로 주입받아 Mybatis를 사용할 준비를 한다.
	 @Autowired는 타입만 일차하면 자동 주입 받을수 있다.
	 */
	@Autowired
	private SqlSession sqlSession;
	
	//방명록 리스트
	@RequestMapping("/mybatis/list.do")
	public String list(Model model, HttpServletRequest req) {
		//Mybatis사용 - 페이지처리
		/*
		 getTotalCount()는 MybatisDAOImpl 인터페이스에 정의된 추상메소드로, 해당 호출로
		 실제 동작을 하는 것은 Mapper에 정의된 id = "getTotalCount" 엘리먼트의 쿼리문이 된다.
		 */
		int totalRecordCount = sqlSession.getMapper(MybatisDAOImpl.class).getTotalCount();
		
		//페이지 처리를 위한 설정값
		int pageSize = 4;
		int blockPage = 2;
		
		//전체페이지수계싼하기
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		//시작 및 끝 rownum구하기
		int nowPage = req.getParameter("nowPage")==null ? 1 :
			Integer.parseInt(req.getParameter("nowPage"));
		int start = (nowPage-1) * pageSize + 1 ;
		int end = nowPage * pageSize;
		
		ArrayList<MyBoardDTO> lists = sqlSession.getMapper(MybatisDAOImpl.class).listPage(start,end);
		
		//페이지 처리를 위한 처리부분
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath()
				+"/mybatis/list.do?");
		
		model.addAttribute("pagingImg", pagingImg);
		//줄바꿈처리
		for(MyBoardDTO dto : lists) {
			String temp = dto.getContents().replace("\r\n", "<br/>");
			dto.setContents(temp);
		}
		
		model.addAttribute("lists", lists);
		
		return "07Mybatis/list";
	}
	
	//박명록쓰기
	@RequestMapping("/mybatis/write.do")
	public String write(Model model, HttpSession session, HttpServletRequest req) {
		if(session.getAttribute("siteUserInfo")==null) {
			model.addAttribute("backUrl","07Mybatis/write");
			return "redirect:login.do";
		}
		
		return "07Mybatis/write";
	}
	
	//로그인 페이지
	@RequestMapping("/mybatis/login.do")
	public String login(Model model) {
		return "07Mybatis/login";
	}
	
	//로그인 처리
	@RequestMapping("/mybatis/loginAction.do")
	public ModelAndView loginAction(HttpServletRequest req, HttpSession session) {
		MemberVO vo = sqlSession.getMapper(MybatisMemberImpl.class).login(
				req.getParameter("id"),req.getParameter("pass")
		);
		
		ModelAndView mv = new ModelAndView();
		if(vo==null) {
			mv.addObject("LoginNG","아이디/패스워드가 틀렸습니다.");
			mv.setViewName("07Mybatis/login");
			return mv;
		}
		else {
			session.setAttribute("siteUserInfo", vo);
		}
		
		//로그인 후 페이지 이동
		String backUrl = req.getParameter("backUrl");
		if(backUrl==null || backUrl.equals("")) {
			mv.setViewName("07Mybatis/login");
		}
		else {
			mv.setViewName(backUrl);
		}
		return mv;
	}
	
	//글쓰기 처리
	@RequestMapping(value="/mybatis/writeAction.do", method = RequestMethod.POST)
	public String writeAction(Model model, HttpServletRequest req, HttpSession session) {
		//로그인이 해제되었는지  확인 후 작성 완료
		
		if(session.getAttribute("siteUserInfo")==null) {
			return "redirect:login.do";
		}
		
		//Mybatis 사용
		sqlSession.getMapper(MybatisDAOImpl.class).write(
				req.getParameter("name"),
				req.getParameter("contents"),
				((MemberVO)session.getAttribute("siteUserInfo")).getId()
		);
		
		return "redirect:list.do";
	}
	
	//수정하기
	@RequestMapping("/mybatis/modify.do")
	public String modify(Model model, HttpServletRequest req, HttpSession session) {
		if(session.getAttribute("siteUserInfo")==null) {
			return "redirect:login.do";
		}		
		
		//Mybatis 사용
		ParameterDTO parameterDTO = new ParameterDTO();
		parameterDTO.setBoard_idx(req.getParameter("idx"));
		parameterDTO.setUser_id(((MemberVO)session.getAttribute("siteUserInfo")).getId());
		
		MyBoardDTO dto = sqlSession.getMapper(MybatisDAOImpl.class).view(parameterDTO);
		
		model.addAttribute("dto",dto);
		return "07Mybatis/modify";
	}
	
	//로그아웃
	@RequestMapping("/mybatis/logout.do")
	public String logout(HttpSession session) {
		
		//세션영역을 비워준다.
		session.setAttribute("siteUserInfo", null);
		return "redirect:login.do";
	}
	
	@RequestMapping("/mybatis/modifyAction.do")
	public String modifyAction(Model model, HttpServletRequest req, HttpSession session) {
		//Mybatis 사용
		sqlSession.getMapper(MybatisDAOImpl.class).modify(
				req.getParameter("idx"),
				req.getParameter("name"),
				req.getParameter("contents"),
				((MemberVO)session.getAttribute("siteUserInfo")).getId()
		);
		
		return "redirect:list.do";
	}
	
	@RequestMapping("/mybatis/delete.do")
	public String delete(Model model, HttpServletRequest req, HttpSession session) {
		
		//로그인확인
		if(session.getAttribute("siteUserInfo")==null) {
			return "redirect:login.do";
		}
		
		//Mybatis사용
		sqlSession.getMapper(MybatisDAOImpl.class).delete(
				req.getParameter("idx"),
				((MemberVO)session.getAttribute("siteUserInfo")).getId()
		);
		
		return "redirect:list.do";
	}
	
	
}
