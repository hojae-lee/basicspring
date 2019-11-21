package mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface MybatisDAOImpl {

	//1.게시물수 카운트
	public int getTotalCount();
	//2. start, end값을 매개변수로 리스트 출력.
	public ArrayList<MyBoardDTO> listPage(int s, int e);
	 //방명록 쓰기
   /*
   파라미터 전달시 Mapper에서 즉시 사용할 이름을 지정하고 싶을 때 @Param 어노테이션을 활용한다.
   아래와 같이 지정한 경우 Mapper에서 #{_name}과 같이 사용할 수 있다.
    */

	public void write(@Param("_name") String name, @Param("_contents") String contents,
			@Param("_id") String id);
	
	public MyBoardDTO view(ParameterDTO parameterDTO);
	
	//방명록 수정처리
	public int modify(String idx, String name, String contents, String id);
	
	public int delete(String idx, String id);
	
}
