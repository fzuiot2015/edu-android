package fzu.edu.entiy;

/**
 * 教师类
 */
public class Teacher {
    private String tusername;   //账号
    private String tpassword;   //密码
    private String tid;
    private String tname;       //姓名
    private String tdept;       //学院
    private String tmajor;      //专业

    public String getTusername() {
        return tusername;
    }

    public void setTusername(String tusername) {
        this.tusername = tusername;
    }

    public String getTpassword() {
        return tpassword;
    }

    public void setTpassword(String tpassword) {
        this.tpassword = tpassword;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTdept() {
        return tdept;
    }

    public void setTdept(String tdept) {
        this.tdept = tdept;
    }

    public String getTmajor() {
        return tmajor;
    }

    public void setTmajor(String tmajor) {
        this.tmajor = tmajor;
    }

    @Override
    public String toString() {
        return "Teacher [tusername=" + tusername + ", tpassword=" + tpassword
                + ", tid=" + tid + ", tname=" + tname + ", tdept=" + tdept
                + ", tmajor=" + tmajor + "]";
    }
}
