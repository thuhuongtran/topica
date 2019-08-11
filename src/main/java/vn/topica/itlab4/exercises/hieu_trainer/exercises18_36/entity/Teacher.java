package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Map;

/**
 * Exercise 21: write Teacher class
 * "Viết chương trình tạo lớp Teacher kế thừa đối tượng People có thêm các thuộc tính sau:
 * Set setSubjectTeaching
 * và thiết kế thêm 1 thuộc tính cho phép lưu điểm của từng Student theo học từng Subject mà Teacher này dạy."
 */
@Entity
@Table(name = "teacher")
public class Teacher extends People {
    @Column(name = "subjects")
    private String subjects;

    @Transient
    private Map<String, Integer> studentScore; // score of each student in teacher class

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public Map<String, Integer> getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Map<String, Integer> studentScore) {
        this.studentScore = studentScore;
    }

    @Override
    public String toString() {
        return super.toString() + new StringBuffer(StringUtils.join(studentScore, "\n"));
    }
}
