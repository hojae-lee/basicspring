package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MemberDAO {
	
	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	
	
	/*
	 MemberDAO 클래스의 생성자 메소드
	 	:오라클 DB에 연결 후 로그를 출력
	 */
	
	public MemberDAO(String driver, String url) {
		try {
			Class.forName(driver);
			String id = "kosmo";
			String pw = "1234";
			con = DriverManager.getConnection(url,id,pw);
			System.out.println("DB 연결 성공");
		}
		catch (Exception e) {
			System.out.println("DB 연결 실패!!");
			e.printStackTrace();
		}
	}
	
	/*
	 id,pass를 파라미터로 받아서 회원인지 여부만 판단하여 true/false를 반환하는 메소드
	 */
	public boolean isMember(String id, String pass) {
		/*
		 JAVA에서 쿼리문 작성시 항상 문자열 앞뒤로 스페이스를 넣는 습관을 들이도록. 하자..
		 */
		String sql ="SELECT count(*) FROM member"+" WHERE id=? AND pass=?";
		int isMember =0;
		boolean isFlag = false;
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			rs.next();
			/*
			 반환되는 값이 0 or 1 이므로 getInt()로 받는다.
			 */
			isMember = rs.getInt(1);
			System.out.println("affected: "+isMember);
			if(isMember ==0) {
				isFlag = false;
			}
			else {
				isFlag = true;
			}
		} catch (Exception e) {
			isFlag = false;
			e.printStackTrace();
		}
		return isFlag;
	}
	
	
	//회원정보 확인후 Map 컬렉션으로 반환
	public Map<String,String> getMemberMap(String id,String pw){
		
		//회원정보를 담아서 반환하기 위한 변수
		Map<String,String> maps = new HashMap<String,String>();
		
		String sql = "SELECT id,pass,name FROM member "
				+ "WHERE id=? and pass=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			
			//결과 셋이 있는 경우메나 레코드를 읽어온다.
			if(rs.next()) {
				//Map에 추가할때는 put()메소드를 사용한다.
				maps.put("id", rs.getString(1));
				maps.put("pass", rs.getString(2));
				maps.put("name", rs.getString(3));
			}
			else {
				System.out.println("결과셋이 없습니다.");
			}
		}
		catch (Exception e) {
			System.out.println("getMemberDTO오류");
			e.printStackTrace();
		}
		return maps;
	}
	
	public ArrayList<MemberDTO> memberList(){
		ArrayList<MemberDTO> members = new ArrayList<>();
		
		String query = "select * from member where 1=1 order by regidate desc";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				
				members.add(dto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}
	
	public ArrayList<MemberDTO> memberList(String user_id){
		ArrayList<MemberDTO> members = new ArrayList<>();
		
		String query = "select * from member ";
		
		if(!(user_id.equalsIgnoreCase(""))) {
			query += "where id =? order by regidate desc";
		}
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, user_id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString("name"));
				dto.setRegidate(rs.getDate("regidate"));
				
				members.add(dto);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}
	
}
