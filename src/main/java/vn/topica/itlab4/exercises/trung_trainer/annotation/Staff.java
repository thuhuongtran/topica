package vn.topica.itlab4.exercises.trung_trainer.annotation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Staff {
    private String name;
    private int age;
    private String[] position;

    @JsonField(name = "skills")
    private List<String> listSkill;

    @JsonField(name = "salary")
    private Map<String, BigDecimal> salaryDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getPosition() {
        return position;
    }

    public void setPosition(String[] position) {
        this.position = position;
    }

    public List<String> getListSkill() {
        return listSkill;
    }

    public void setListSkill(List<String> listSkill) {
        this.listSkill = listSkill;
    }

    public Map<String, BigDecimal> getSalaryDetails() {
        return salaryDetails;
    }

    public void setSalaryDetails(Map<String, BigDecimal> salaryDetails) {
        this.salaryDetails = salaryDetails;
    }
}
