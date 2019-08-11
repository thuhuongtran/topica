package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Exercise 18: write Subject class
 * "Viết chương trình  tạo lớp Subject có các thuộc tính sau:
 * String subjectName
 * Date startDate
 * Date endDate"
 */
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String subjectName;
    @Column(name = "start")
    private Date startDate;
    @Column(name = "end")
    private Date endDate;

    public Subject() {
    }

    public Subject(String subjectName, Date startDate, Date endDate) {
        this.subjectName = subjectName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return new StringBuffer("Name: ")
                .append(this.subjectName)
                .append(" , Start: ")
                .append(this.startDate)
                .append(" , End: ")
                .append(this.endDate)
                .toString();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
