package com.kosmo.k11spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mybatis.MemberVO;
import mybatis.MyBoardDTO;
import mybatis.MybatisDAOImpl;
import springboard.util.PagingUtil;

@Controller
public class JSONUseController {
	
	//마이바티스 빈을 자동주입
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/jsonUse/jsonView.do")
	@ResponseBody
	public Map<String, Object> responseBodyView(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("String","나는문자열이담");
		map.put("Number", 123);
		map.put("Message", "아으ㅏ으아아앙");
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("collection");
		list.add("모르면");
		list.add("장애인");
		
		map.put("Collection", list);
		
		return map;
	}
	
	@RequestMapping("/jsonUse/board.do")
	public String board() {
		
		return "11JsonUse/board";
	}
	
	@RequestMapping("/jsonUse/aList.do")
	public String aList(Model model, HttpServletRequest req) {
		
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
		System.out.println("nowPage= "+nowPage);
		
		ArrayList<MyBoardDTO> lists = sqlSession.getMapper(MybatisDAOImpl.class).listPage(start,end);
		
		//페이지 처리를 위한 처리부분
		String pagingImg = PagingUtil.pagingAjax(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath()
				+"");
		
		model.addAttribute("pagingImg", pagingImg);
		//줄바꿈처리
		for(MyBoardDTO dto : lists) {
			String temp = dto.getContents().replace("\r\n", "<br/>");
			dto.setContents(temp);
		}
		
		model.addAttribute("lists", lists);
		
		return "11JsonUse/aList";
	}
	
	//로그인 페이지
	@RequestMapping("/jsonUse/login.do")
	public String login(Model model) {
		
		return "11JsonUse/login";
	}
	
	//로그아웃
	@RequestMapping("jsonUse/logout.do")
	public String logout(HttpSession session) {
		session.setAttribute("siteUserInfo", null);
		return "redirect:login.do";
	}
	
	//로그인 처리
	@RequestMapping("/jsonUse/loginAction.do")
	@ResponseBody
	public Map<String, Object> loginAction(HttpServletRequest req, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		
		//mapper에서 반환해주는 DTO를 저장
		MemberVO vo = sqlSession.getMapper(MybatisDAOImpl.class).login(
		
				req.getParameter("id"),
				req.getParameter("pass")
		);
		
		if(vo==null) {
			//로그인실패
			map.put("loginResult", 0);
			map.put("loginMessage", "로그인 실패");
		}
		else {
			//로그인에 성공한 경우 Session영역에 속성저장
			session.setAttribute("siteUserInfo", vo);
			map.put("loginResult", 1);
			map.put("loginMessage", "로그인 성공");
		}
		return map;
	}
	
	//글 삭제처리
	@RequestMapping("/jsonUse/deleteAction.do")
	@ResponseBody
	public Map<String, Object> deleteAction(HttpServletRequest req, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		
		//로그인 확인
		if(session.getAttribute("siteUserInfo")==null) {
			//로그인 실패. 로그인 후 삭젳러ㅣ
			map.put("statusCode",1);
			return map;
		}
		
		int result = sqlSession.getMapper(MybatisDAOImpl.class).delete(req.getParameter("idx"),
				((MemberVO)session.getAttribute("siteUserInfo")).getId());
		
		if(result<=0) {
			//삭제실패라면 코드는 0 
			map.put("statusCode",0);
		}
		else {
			//삭제성공이라면 코드는 2
			map.put("statusCode", 2);
		}
		
		return map;
				
	}
	
}
