package fzu.edu.entiy;

import java.util.List;

public class Report {
    private String rid;
    private Student student;

    private List<ReportItem> reportItems;


    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<ReportItem> getReportItems() {
        return reportItems;
    }

    public void setReportItems(List<ReportItem> reportItems) {
        this.reportItems = reportItems;
    }

    @Override
    public String toString() {
        return "Report [rid=" + rid + ", student=" + student + ", reportItems="
                + reportItems + "]";
    }


}
