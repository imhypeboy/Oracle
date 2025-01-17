package Oracle;

public class MemberVO {
	private String id;
	private String pwd;

	public MemberVO() {

	}

	public MemberVO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}
}