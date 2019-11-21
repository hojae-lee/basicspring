package mybatis;

import java.util.ArrayList;

public interface AndroidImpl {
	
	//회원리스트
	public ArrayList<MemberVO> memberList(String paramStr);
	
	//로그인
	//public MemberVO memberParamLogin(ParamMemberVO pmVo);
	
	//로그인
	public MemberVO memberLogin(String id, String pass);
	
	//회원가입
	public int memberRegist(MemberVO memberVO);

}
