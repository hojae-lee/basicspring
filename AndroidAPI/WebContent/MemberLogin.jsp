<%@page import="java.util.Map"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("user_id");
String pw = request.getParameter("user_pw");

String driver = application.getInitParameter("JDBCDriver");
String url = application.getInitParameter("ConnectionURL");
MemberDAO dao = new MemberDAO(driver, url);

/* 
{
	"isLogin" : 1,
	"memberInfo" : {"","","",""}
}
Json객체안에 또다른 JSON객체가 들어가는 형태로 생성된다.
*/


JSONObject jsonObject = new JSONObject();
JSONObject mInfo = new JSONObject();

Map<String, String> memberInfo = dao.getMemberMap(id, pw);

if(memberInfo.get("id")!=null){
   mInfo.put("id", memberInfo.get("id"));
   mInfo.put("pass", memberInfo.get("pass"));
   mInfo.put("name", memberInfo.get("name"));
   
   jsonObject.put("memberInfo", mInfo);
   jsonObject.put("isLogin", 1);
}else{
   jsonObject.put("isLogin", 0);
}

out.print(jsonObject.toJSONString());


%>