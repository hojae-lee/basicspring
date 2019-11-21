package springboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;

import oracle.net.aso.f;

/*
 JdbcTemplate 관련 주요메소드
 
 -List query(String sql, RowMapper rowMapper)
 	: 여러개의 레코드를 반환하는 select계열의 쿼리문인 경우 사용함.
 	
 -List query(String sql, Object[] args, RowMapper rowMapper)
 	: 인파라미터를 가진 여러개의 레코드를 반환하는 select계열의 쿼리문인 경우 사용함.
 	
 -int queryForInt(String sql) 혹은 queryForInt(String sql, Object[] args)
 	:쿼리문의 실행결과가 숫자를 반환하는 select계열의 쿼리문에 사용함.
 	
 -Object queryForObject(String sql, RowMapper rowMapper)
 혹은 Object queryForObject(String sql, Object[] args, RowMapper rowMapper)
 	: 하나의 레코드를 반환하는 select계열의 쿼리문 실행.
 	
 -int update(String sql)
 	: 인파라미터가 없는 update/delete/insert 쿼리문인경우 사용함.
 -int update(String sql, Object[] args)
 	: 인파라미터가 있는 update/delete/insert 쿼리문인 경우 사용함.
 	
 	※ queryForObject() 메소드는 실행결과가 0개이거나 2개 이상인 경우에 예외가 발생하므로 반드시
   예외처리를 해주어야 한다.
 */

public class JDBCTemplateDAO {

	//멤버변수
	JdbcTemplate template;
	
	//생성자
	public JDBCTemplateDAO() {
		this.template = JdbcTemplateConst.template;
		System.out.println("JDBCTemplate() 생성자 호출");
	}
	
	public void close() {
		//JDBCTemplate에서는 사용하지 않음.
	}
	
	//게시물 수 카운트
	
	public int getTotalCount(Map<String, Object> map) {
		
		String sql = "select count(*) from springboard ";

		if (map.get("Word") != null) {
			sql += " where " + map.get("Column") + " " + " Like '%" + map.get("Word") + "%' ";
		}
		
		return template.queryForObject(sql, Integer.class);
		
	}
	
	//레코드 페이지별로 가져오기
	public ArrayList<SpringBbsDTO> list(Map<String, Object> map){
		
		int start = Integer.parseInt(map.get("start").toString());
		int end = Integer.parseInt(map.get("end").toString());
		
		String sql = " SELECT * FROM ( "
	            +"          SELECT Tb.*, ROWNUM rNum FROM ( "
	            +"             SELECT * FROM springboard ";
	      if(map.get("Word")!=null) {
	    	  sql +=" WHERE "+map.get("Column")+" LIKE '%"+map.get("Word")+"%' ";
	      }
	      sql += " ORDER BY bgroup DESC, bstep ASC "
	            +"   ) Tb "
	            +" ) "
	            +" WHERE rNum BETWEEN "+start+" AND "+end;
	      
	      return (ArrayList<SpringBbsDTO>)template.query(sql, new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));
		
	}
	
	public void write(final SpringBbsDTO dto) {
			
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					
					String sql = "insert into springboard" 
							+ " ( idx,name,title,contents,hits,bgroup,bstep,bindent,pass) "
							+ " values (springboard_seq.nextval,?,?,?,0,springboard_seq.nextval,0,0,?)";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, dto.getName());
					psmt.setString(2, dto.getTitle());
					psmt.setString(3, dto.getContents());
					psmt.setString(4, dto.getPass());
					
					return psmt;
				}
			});

	}
	
	// 게시물 상세보기
	public SpringBbsDTO view(String idx) {
		// 조회수 증가
		updateHit(idx);

		SpringBbsDTO dto = null;

		String sql = "select * from springboard where idx="+idx;
		try {
			dto = template.queryForObject(sql, new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));				
			System.out.println("view상세보기실행");
		} 
		catch (NumberFormatException e) {
			System.out.println("View() 실행시 예외발생");
			dto = new SpringBbsDTO();
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			dto = new SpringBbsDTO();
		}
		catch (Exception e) {
			e.printStackTrace();
			dto = new SpringBbsDTO();
		}
		
		return dto;
	}

	public void updateHit(final String idx) {
		
		String sql = "update springboard set hits=hits+1 where idx=?";
		
		template.update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(idx));
				
			}
		});
	}
	
	public int password(String idx, String pass) {
		int retNum = 0;
		
		/*
		 queryForObject() 메소드는 실행결과가 1개가 아니면 예외가 발생하므로
		 반드시 예외처리 해주어야한다.
		 */
		try {
			String sql = "select * from springboard where pass="+pass+" and idx="+idx;
			
			SpringBbsDTO dto = template.queryForObject(sql, 
					new BeanPropertyRowMapper<SpringBbsDTO>(SpringBbsDTO.class));
			retNum = dto.getIdx();				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("password() 예외발생");
		}
			
		return retNum;
	}
	
	public void edit(final SpringBbsDTO dto) {
		
		String sql = "update springboard set name=?, title=?, contents=? where idx=? and pass=?";

		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getName());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContents());
				ps.setInt(4, dto.getIdx());
				ps.setString(5, dto.getPass());
			}
		});
	}
	
	public void delete(final String idx, final String pass) {
		
		String sql = "delete from springboard where idx=? and pass=? ";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, idx);
				ps.setString(2, pass);
			}
		});

	}
	
	public void reply(final SpringBbsDTO dto) {
		// 답변글쓰기전 레코드 업데이트
		replyPrevUpdate(dto.getBgroup(), dto.getBstep());
		
		String sql = "insert into springboard " 
				+ " (idx, name, title, contents, pass,"
				+ " bgroup,bstep,bindent) "
				+ " values "
				+ " (springboard_seq.nextval, ?, ?, ?, ?"
				+ " , ?, ?, ?) ";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, dto.getName());
				ps.setString(2, dto.getTitle());
				ps.setString(3, dto.getContents());
				ps.setString(4, dto.getPass());
				ps.setInt(5, dto.getBgroup());
				ps.setInt(6, dto.getBstep()+1);
				ps.setInt(7, dto.getBindent()+1);	
			}
		});
	}
	
	// 답변글 입력전 레코드 일괄 업데이트
	public void replyPrevUpdate(final int strGroup, final int strStep) {
		
		/*
		 현재 답변글이 작성되는 위치를(bstep)을 확인하여 
		 해당위치를 확인하여 해당 위치보다 큰 레코드를 일괄적으로 +1처리한다.
		 */
		String sql = "update springboard set bstep=bstep+1 where bgroup=? and bstep>?";
		
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, strGroup);
				ps.setInt(2, strStep);
			}
				
		});
	
	}
}
