package fzu.edu.entiy;

/**
 * 课程类
 */
public class Course {
    private String cid;
    private String cname;       //课程名
    private Teacher teacher;    //任课教师
    private int period;
    private String address;     //上课地点
    private String week;
    private float credit;
    private int time1;
    private int time2;
    private int count;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public int getTime1() {
        return time1;
    }

    public void setTime1(int time1) {
        this.time1 = time1;
    }

    public int getTime2() {
        return time2;
    }

    public void setTime2(int time2) {
        this.time2 = time2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Course [cid=" + cid + ", cname=" + cname + ", teacher="
                + teacher + ", period=" + period + ", address=" + address
                + ", week=" + week + ", credit=" + credit + ", time1=" + time1
                + ", time2=" + time2 + ", count=" + count + "]";
    }


}
