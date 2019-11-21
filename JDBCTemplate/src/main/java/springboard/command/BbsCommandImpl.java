package springboard.command;

import org.springframework.ui.Model;

//게시판의 모든 Command 인터페이스 구현.
public interface BbsCommandImpl {
	
	//추상메소드 : 오버라이딩의 목적으로 정의함.
	void execute(Model model);
}
