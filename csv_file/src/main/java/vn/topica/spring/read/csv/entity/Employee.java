package vn.topica.spring.read.csv.entity;

import java.sql.Date;

public class Employee {
    private int id;
    private String name;
    private String birth;
    private String gender;

    public Employee() {
    }

    public Employee(int id, String name, String birth, String gender) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
