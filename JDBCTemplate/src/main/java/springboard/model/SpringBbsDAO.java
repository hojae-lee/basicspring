package springboard.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SpringBbsDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	public SpringBbsDAO(String driver, String url) {

		try {
			Class.forName(driver);
			String id = "kosmo";
			String pw = "1234";
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("DB연결성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB연결실패");
		}
	}

	public SpringBbsDAO() {

		try {
			Context initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource source = (DataSource) ctx.lookup("jdbc/myoracle");
			con = source.getConnection();

			System.out.println("DBCP연결성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBCP연결실패");
		}
	}

	// 전체목록카운트 하기
	public int getTotalCount(Map<String, Object> map) {
		int totalCount = 0;

		try {
			String sql = "select count(*) from springboard ";

			if (map.get("Word") != null) {
				sql += " where " + map.get("Column") + " " + " Like '%" + map.get("Word") + "%' ";
			}

			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("select문시 예외발생");
			e.printStackTrace();
		}

		return totalCount;
	}

	// 리스트 select하기
	public ArrayList<SpringBbsDTO> list(Map<String,Object> map){
	      
	      ArrayList<SpringBbsDTO> bbs = new ArrayList<SpringBbsDTO>();
	      
	      String query = " SELECT * FROM ( "
	            +"          SELECT Tb.*, ROWNUM rNum FROM ( "
	            +"             SELECT * FROM springboard ";
	      if(map.get("Word")!=null) {
	         query +=" WHERE "+map.get("Column")+" LIKE '%"+map.get("Word")+"%' ";
	      }
	      query += " ORDER BY bgroup DESC, bstep ASC "
	            +"   ) Tb "
	            +" ) "
	            +" WHERE rNum BETWEEN ? AND ?";
	      System.out.println("쿼리문 : "+ query);
	      
	      try {
	         psmt = con.prepareStatement(query);
	         
	         //JSP에서 계산한 페이지 범위값을 이용해 인파라미터를 설정한다.
	         psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
	         psmt.setInt(2, Integer.parseInt(map.get("end").toString()));
	         rs = psmt.executeQuery();
	         
	         while(rs.next()) {
	            SpringBbsDTO dto = new SpringBbsDTO();
	            
	            //답변글 처리를 위한 로직추가
	            int indentNum = rs.getInt(9);
	            
//	            String spacer = "";
//	            if(indentNum>0) {
//	               for(int i=1; i<=indentNum; i++) {
//	                  spacer += "&nbsp;&nbsp;";
//	               }
//	               spacer += spacer +"<img src='../images/re3.gif'>";
//	            }
	            
	            dto.setIdx(rs.getInt(1));
	            dto.setName(rs.getString(2));
	            //답변글처리
	            dto.setTitle(rs.getString(3));
	            dto.setContents(rs.getString(4));
	            dto.setPostdate(rs.getDate(5));
	            dto.setHits(rs.getInt(6));
	            dto.setBgroup(rs.getInt(7));
	            dto.setBstep(rs.getInt(8));
	            dto.setBindent(rs.getInt(9));
	            dto.setPass(rs.getString(10));
	            bbs.add(dto);
	         }
	      } 
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	      return bbs;
	   }

	public void write(SpringBbsDTO dto) {
		try {
			String sql = "insert into springboard" + " ( idx,name,title,contents,hits,bgroup,bstep,bindent,pass) "
					+ " values (springboard_seq.nextval,?,?,?,0,springboard_seq.nextval,0,0,?)";

			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContents());
			psmt.setString(4, dto.getPass());

			psmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("INSERT문 예외발생");
		}
	}

	// 게시물 상세보기
	public SpringBbsDTO view(String idx) {
		// 조회수 증가
		updateHit(idx);

		SpringBbsDTO dto = null;

		String sql = "select * from springboard where idx=?";

		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			if (rs.next()) {
				dto = new SpringBbsDTO();

				dto.setIdx(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContents(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setHits(rs.getInt(6));
				dto.setBgroup(rs.getInt(7));
				dto.setBstep(rs.getInt(8));
				dto.setBindent(rs.getInt(9));
				dto.setPass(rs.getString(10));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public void updateHit(String idx) {
		String sql = "update springboard set hits=hits+1 where idx=?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, idx);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int password(String idx, String pass) {
		int retNum = 0;

		try {
			String sql = "select * from springboard where pass=? and idx=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();

			if (rs.next()) {
				retNum = rs.getInt(1);
			}
		} 
		catch (Exception e) {
			retNum = 0;
			e.printStackTrace();
		}
		return retNum;
	}

	public void edit(SpringBbsDTO dto) {
		try {
			String sql = "update springboard set name=?, title=?, contents=? where idx=? and pass=?";

			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContents());
			psmt.setInt(4, dto.getIdx());
			psmt.setString(5, dto.getPass());

			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String idx, String pass) {

		try {
			String sql = "delete from springboard where idx=? and pass=? ";
			psmt = con.prepareStatement(sql);

			psmt.setInt(1, Integer.parseInt(idx));
			psmt.setString(2, pass);

			int rn = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reply(SpringBbsDTO dto) {
		// 답변글쓰기전 레코드 업데이트
		replyPrevUpdate(dto.getBgroup(), dto.getBstep());

		// 답변글입력
		try {
			String sql = "insert into springboard " 
					+ " (idx, name, title, contents, pass,"
					+ " bgroup,bstep,bindent) "
					+ " values "
					+ " (springboard_seq.nextval, ?, ?, ?, ?"
					+ " , ?, ?, ?) ";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContents());
			psmt.setString(4, dto.getPass());
			/*
			 * 답글은 기존글에 bstep+1, bindent+1 을 해준다. 
			 * bgroup : 원본글의 idx값을 입력받게 되어 같은 그룹으로 처리됨
			 * bstep : 같은 그룹내에서의 정렬순서 
			 * bindent : 답변글의 깊이(1이면 첫번쨰 답변글)
			 */
			psmt.setInt(5, dto.getBgroup());
			psmt.setInt(6, dto.getBstep() + 1);
			psmt.setInt(7, dto.getBindent() + 1);

			int rn = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert시 예외발생");
			e.printStackTrace();
		}
	}

	// 답변글 입력전 레코드 일괄 업데이트
	public void replyPrevUpdate(int strGroup, int strStep) {
		/*
		 * 현재 답변글이 작성되는 위치 bstep을 확인하여 해당 위치보다 큰 레코드를 일괄적으로+1처리
		 */
		try {
			String sql = "update springboard set bstep=bstep+1 where bgroup=? and bstep>?";

			psmt = con.prepareStatement(sql);
			psmt.setInt(1, strGroup);
			psmt.setInt(2, strStep);

			int rn = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("자원반납시 예외발생");
		}
	}

}
