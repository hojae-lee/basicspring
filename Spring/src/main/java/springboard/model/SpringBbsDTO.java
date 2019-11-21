package springboard.model;

import java.sql.Date;

public class SpringBbsDTO {

	//매개변수
	private int idx;
	private String name; 
	private String title; 
	private String contents;
	private java.sql.Date postdate;
	private int hits;
	private int bgroup;
	private int bstep;
	private int bindent; 
	private String pass;
	private int virtualNum;
	
	//기본생성자
	public SpringBbsDTO() {}

	//맴버변수생성자
	public SpringBbsDTO(int idx, String name, String title, String contents, Date postdate, int hits, int bgroup,
			int bstep, int bindent, String pass, int virtualNum) {
		super();
		this.idx = idx;
		this.name = name;
		this.title = title;
		this.contents = contents;
		this.postdate = postdate;
		this.hits = hits;
		this.bgroup = bgroup;
		this.bstep = bstep;
		this.bindent = bindent;
		this.pass = pass;
		this.virtualNum = virtualNum;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public java.sql.Date getPostdate() {
		return postdate;
	}

	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getBgroup() {
		return bgroup;
	}

	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}

	public int getBstep() {
		return bstep;
	}

	public void setBstep(int bstep) {
		this.bstep = bstep;
	}

	public int getBindent() {
		return bindent;
	}

	public void setBindent(int bindent) {
		this.bindent = bindent;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getVirtualNum() {
		return virtualNum;
	}

	public void setVirtualNum(int virtualNum) {
		this.virtualNum = virtualNum;
	}
	
}
