package aop;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

public class GoodsDAO {

	public JdbcTemplate template = StaticDAO.template;
	public GoodsDAO() {
		System.out.println("GoodsDAO() 생성자 호출됨.");
	}
	
	public int goodInsert(final GoodsDTO dto) {
		
		String sql = "insert into sh_goods(g_idx,goods_name,goods_price,p_code) values "
				+ " (goods_seq.nextval,?,?,? )";
		int result = template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, dto.getGoods_name());
				ps.setString(2, dto.getGoods_price());
				ps.setString(3, dto.getP_code());
				
			}
		});
		
		return result;
		
	}
	
}
