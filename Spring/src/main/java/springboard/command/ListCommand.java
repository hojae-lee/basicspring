package springboard.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;
import springboard.util.EnvFileReader;
import springboard.util.PagingUtil;

public class ListCommand implements BbsCommandImpl{
	
	/*
	 BbsCommandImpl 인터페이스를 구현하므로 execute() 메소드는 반드시 오버라이딩
	 처리해야 에러가 나지 않는다.
	 인터페이스는 클래스 정의시 설계도로 사용된다.
	 */

	@Override
	public void execute(Model model) {
		
		//System.out.println("ListCommand클래스 > execute() 호출");
		
		//컨트롤러에서 넘겨준 파라미터를 한번에 받기위한 코드
		//asMap() 메소드를 통해 Model객체에 저장된 내용을 Map 컬렉션으로 변환한다.
		Map<String, Object> paramMap = model.asMap();
		/*
		 컨트롤러에서 전달된 request객체를 Command 객체내에서 사용하기 위해 형변환 후 변수에
		 저장함.
		 */
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		//커넥션 풀 DAO 호출
		SpringBbsDAO dao = new SpringBbsDAO();
		
		//검색기능구현
		String addQueryString = "";
		String searchColumn = req.getParameter("searchColumn");
		String searchWord = req.getParameter("searchWord");
		
		if(searchWord!=null) {
			addQueryString = String.format("searchColumn=%s"+ "&searchWord=%s&",searchColumn,searchWord );
			
			paramMap.put("Column", searchColumn);
			paramMap.put("Word", searchWord);
		}
		
		//전체레코드 수 카운트 하기
		int totalRecordCount = dao.getTotalCount(paramMap);
		
		System.out.println("로그"+totalRecordCount);
		/*
		 외부파일로 만든 파일에 저장된 게시판페이징 설정값을 읽어온다.
		 초기상태는 String형태이므로 int형으로 변환한 후 저장한다.
		 */
		int pageSize = Integer.parseInt(EnvFileReader.getValue("SpringBbsInit.properties", "springBoard.pageSize"));
		int blockPage = Integer.parseInt(EnvFileReader.getValue("SpringBbsInit.properties", "springBoard.blockPage"));
		
		//전체 페에지 수 계산하기
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		//시작 및 끝 rowNum 구하기
		int nowPage = req.getParameter("nowPage") ==null ? 
				1 : Integer.parseInt(req.getParameter("nowPage"));
		//게시물 select시 구간으로 사용할 변수를 계싼
		int start = (nowPage-1) * pageSize +1;
		int end = nowPage * pageSize;
		
		//리스트 가져오기 위한 파라미터 저장
		paramMap.put("start",start);
		paramMap.put("end", end);
		
		//출력할 리스트 가져오기
		ArrayList<SpringBbsDTO> listRows = dao.list(paramMap);
		
		//가상번호 계싼하여 부여하기
		int virtualNum = 0;
		int countNum=0;
		for(SpringBbsDTO row : listRows) {
			//가상번호 연산후 setter를 통해 값을 저장.
			virtualNum = totalRecordCount - (((nowPage-1)*pageSize)+ countNum++);
			System.out.println("로그2:" + virtualNum);
			row.setVirtualNum(virtualNum);
			
			String reSpace = "";
			if(row.getBindent() > 0) {
				//답변글을 indent만큼 들여쓰기
				for(int i=0; i<row.getBindent(); i++) {
					reSpace += "&nbsp;&nbsp;";
				}
				row.setTitle(reSpace+"<img src='../images/re3.gif'>"+row.getTitle());
			}
		}
				
		//페이지 처리를 위한 처리부분
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath()+"/board/list.do?"+addQueryString);
		model.addAttribute("pagingImg", pagingImg); 
		model.addAttribute("totalPage",totalPage); //전체페이지수
		model.addAttribute("nowPage",nowPage); //현재페이지수
		model.addAttribute("listRows", listRows);
		
		
	}
	
}
