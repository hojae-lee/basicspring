package springboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import springboard.model.JDBCTemplateDAO;
import springboard.model.SpringBbsDAO;
import springboard.model.SpringBbsDTO;

public class ViewCommand implements BbsCommandImpl{
	
	@Override
	public void execute(Model model) {
		
		//파라미터 한번에 전달받기
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest req = (HttpServletRequest)paramMap.get("req");
		
		//파라미터받기
		String idx = req.getParameter("idx");
		String nowPage = req.getParameter("nowPage");
		
		//커넥션풀 사용한 DAO
		//SpringBbsDAO dao = new SpringBbsDAO();
		
		//JDBCTemplate
		JDBCTemplateDAO dao = new JDBCTemplateDAO();
		SpringBbsDTO dto = new SpringBbsDTO();
		
		//상세보기
		dto = dao.view(idx);
		
		//줄바꿈처리: getter로 줄바꿈 처리후 다시 setter로 저장
		
		dto.setContents(dto.getContents().replace("\r\n", "<br/>"));
		
		//모델에 저장(뷰로 데이터를 넘겨주기 위해)
		model.addAttribute("viewRow", dto);
		model.addAttribute("nowPage", nowPage);
		dao.close();
		
	}

}
