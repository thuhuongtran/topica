package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Map;

/**
 * Exercise 20: write Student class
 * "Viết chương trình tạo lớp Student kế thừa đối tượng People có thêm các thuộc tính sau:
 * Set setSubjectLearning
 * và thiết kế thêm 1 thuộc tính cho phép lưu điểm của từng Subject mà học sinh theo học"
 */
@Entity
@Table(name = "student")
public class Student extends People {

    @Column(name = "scores")
    private String scores;

    @Transient
    private Map<String, Integer> subjectScore; // score of each subject that student learned

    public Map<String, Integer> getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Map<String, Integer> subjectScore) {
        this.subjectScore = subjectScore;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + new StringBuffer(StringUtils.join(subjectScore, "\n"));
    }
}
