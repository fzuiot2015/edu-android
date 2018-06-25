package fzu.edu.entiy;

public class Student 
{
	private String susername;	//账号
	private String spassword;	//密码
	private String sid;
	private String sname;		//姓名
	private String sdept;		//学院
	private String smajor;		//专业

	public String getSusername() {
		return susername;
	}

	public void setSusername(String susername) {
		this.susername = susername;
	}

	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSdept() {
		return sdept;
	}

	public void setSdept(String sdept) {
		this.sdept = sdept;
	}

	public String getSmajor() {
		return smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	@Override
	public String toString() {
		return "Student [susername=" + susername + ", spassword=" + spassword
				+ ", sid=" + sid + ", sname=" + sname + ", sdept=" + sdept
				+ ", smajor=" + smajor + "]";
	}

	
	
}
