package com.kosmo.k11springapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mybatis.AndroidImpl;
import mybatis.MemberVO;

@Controller
public class AndroidController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping("/android/memberList.do")
	@ResponseBody
	public ArrayList<MemberVO> memberList(HttpServletRequest req){
		String paramStr = req.getParameter("name");
		
		ArrayList<MemberVO> memberLists = sqlSession.getMapper(AndroidImpl.class).memberList(paramStr);
		return memberLists;
	}
	
	@RequestMapping("/android/memberLogin.do")
	@ResponseBody
	public Map<String, Object> memberLogin(HttpServletRequest req){
		Map<String, Object> memberMap = new HashMap<String, Object>();
		
		String id = req.getParameter("id");
		String pass =req.getParameter("pass");
		
		MemberVO memberVO = sqlSession.getMapper(AndroidImpl.class).memberLogin(id,pass);
		
		if(memberVO==null) {
			//로그인실패
			memberMap.put("success",0);
		}
		else {
			//로그인성공
			memberMap.put("success",1);
			memberMap.put("memberInfo", memberVO);
		}
		
		return memberMap;
	}
	
	@RequestMapping(value="/android/memberRegist.do", method= RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberRegist(HttpServletRequest req, MemberVO memberVO){
		Map<String, Object> memberMap = new HashMap<String, Object>();
		int result = sqlSession.getMapper(AndroidImpl.class).memberRegist(memberVO);
		if(result==1) {
			//가입성공
			memberMap.put("success", 1);
		}
		else {
			//가입실패
			memberMap.put("success", 0);
		}
		
		return memberMap;
	}
	
}
