package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Exercise 19: write People abstract class
 * "Viết chương trình tạo lớp abstract People có các thuộc tính sau:
 * String firstName
 * String lastName
 * Date dateOfBirth
 * String placetoLive"
 */
@MappedSuperclass
public abstract class People {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth")
    private Date dateOfBirth;
    @Column(name = "place")
    private String placetoLive;
    @Column(name = "sex")
    private String sex;

    public void setSimpleInformation(String firstName,
                                     String lastName,
                                     Date dateOfBirth,
                                     String placetoLive) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.placetoLive = placetoLive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringBuffer("Name: ")
                .append(this.firstName)
                .append(" ")
                .append(this.lastName)
                .append(",Birth: ")
                .append(this.dateOfBirth)
                .append(", Place: ")
                .append(this.placetoLive)
                .toString();
    }

    /**
     * Exercise 35: add sex in People
     * "Viết chương trình sửa lớp People thêm thuộc tính sau:
     *  sex (giới tính có 3 loại: Male, Female, Other)
     *  hobby (sở thích)"
     */

    public enum sex{
        male,
        female,
        other
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlacetoLive() {
        return placetoLive;
    }

    public void setPlacetoLive(String placetoLive) {
        this.placetoLive = placetoLive;
    }
}
