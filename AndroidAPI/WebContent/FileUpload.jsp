<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

String saveDirectory = application.getRealPath("/Upload");

int maxPostSize = 1024 * 1024 * 10000;

String encoding = "UTF-8";

FileRenamePolicy policy = new DefaultFileRenamePolicy();

MultipartRequest mr = null;

JSONObject jsonObject = new JSONObject();

try{
	mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
	
	//서버에 저장된 파일명 가져오기
	String fileName = mr.getFilesystemName("filename");
	
	//업로드성공
	jsonObject.put("success", 1);
	jsonObject.put("fileName", fileName);
}
catch(Exception e){
	//업로드실패
	jsonObject.put("success", 0);
}

out.println(jsonObject.toJSONString());
%>