package mybatis;

import java.util.ArrayList;

public interface MybatisDAOImpl {

		//1.게시물수 카운트
		public int getTotalCount();
		//2. start, end값을 매개변수로 리스트 출력.
		public ArrayList<MyBoardDTO> listPage(int s, int e);
		
		public MemberVO login(String id, String pass);
	
		public int delete(String idx, String id);
}
