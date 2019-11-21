package aop;

public class AopDTO {

	private String name;
	private int age;
	
	public AopDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public AopDTO(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public void showInfo() {
		System.out.println("이름:"+getName());
		System.out.println("나이:"+ getAge());
	}
	
}
