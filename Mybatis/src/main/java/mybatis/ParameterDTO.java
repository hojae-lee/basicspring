package mybatis;

public class ParameterDTO {

	private String user_id;
	private String board_idx;
	
	public ParameterDTO() {}

	public ParameterDTO(String user_id, String board_idx) {
		super();
		this.user_id = user_id;
		this.board_idx = board_idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(String board_idx) {
		this.board_idx = board_idx;
	}
	
}
