package vn.topica.itlab4.jdbc.hibernate.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "class")
public class ClassHib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int clsID;

    @Column(name = "cname")
    private String name;

    @Column(name = "desciption")
    private String description;

    @OneToMany(mappedBy = "clsHib", fetch = FetchType.LAZY)
    private Set<StudentHib> listStudent = new HashSet<>();

    public ClassHib() {
    }

    public ClassHib(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getClsID() {
        return clsID;
    }

    public void setClsID(int id) {
        this.clsID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StudentHib> getListStudent() {
        return listStudent;
    }

    public void setListStudent(Set<StudentHib> listStudent) {
        this.listStudent = listStudent;
    }
}
