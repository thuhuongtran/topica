package vn.topica.itlab4.jdbc.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class StudentHib implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int stID;

    @Column(name = "sname")
    private String stName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassHib clsHib;

    public StudentHib() {
    }

    public StudentHib(String name) {
        this.stName = name;
    }

    public StudentHib(String name, ClassHib classHib) {
        this.stName = name;
//        this.clsHib = classHib;
    }

    public int getStID() {
        return stID;
    }

    public void setStID(int id) {
        this.stID = id;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String name) {
        this.stName = name;
    }

    public ClassHib getClsHib() {
        return clsHib;
    }

    public void setClsHib(ClassHib clsHib) {
        this.clsHib = clsHib;
    }
}
