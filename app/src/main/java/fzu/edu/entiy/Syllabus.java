package fzu.edu.entiy;

import java.util.List;

/**
 * 课表类
 */
public class Syllabus {
    private String yid;
    private Student student;

    private List<SyllabusItem> SyllabusItems;

    public List<SyllabusItem> getSyllabusItems() {
        return SyllabusItems;
    }

    public void setSyllabusItems(List<SyllabusItem> syllabusItems) {
        SyllabusItems = syllabusItems;
    }

    public String getYid() {
        return yid;
    }

    public void setYid(String yid) {
        this.yid = yid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    @Override
    public String toString() {
        return "Syllabus [yid=" + yid + ", student=" + student + "]";
    }

}
