<%@page import="org.json.simple.JSONObject"%>
<%@page import="model.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
//안드로이드와 연동하기 위해 회원 리스트를 JSON으로 출력.

request.setCharacterEncoding("UTF-8");

String driver = application.getInitParameter("JDBCDriver");
String url = application.getInitParameter("ConnectionURL");
String user_id = request.getParameter("user_id");

MemberDAO dao = new MemberDAO(driver, url);

JSONArray jsonArray = new JSONArray();
List<MemberDTO> members;
if(user_id==null || user_id.equalsIgnoreCase("")){
	 members = dao.memberList();
}
else{
	 members = dao.memberList(user_id);
}

for(MemberDTO m : members){
	JSONObject jsonObject = new JSONObject();
	
	jsonObject.put("id", m.getId());
	jsonObject.put("pass", m.getPass());
	jsonObject.put("name",m.getName());
	jsonObject.put("regidate", m.getRegidate());
	
	jsonArray.add(jsonObject);
}
out.print(jsonArray.toJSONString());

%>