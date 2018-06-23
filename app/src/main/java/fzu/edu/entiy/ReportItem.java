package fzu.edu.entiy;

public class ReportItem {
    private String riid;
    private Course course;
    private Report report;
    private float Score;

    public String getRiid() {
        return riid;
    }

    public void setRiid(String riid) {
        this.riid = riid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float score) {
        Score = score;
    }

}
