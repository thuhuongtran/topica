package topica.itlab4.mvc.entity;

public class User {
    private int id;
    private String name;
    private String birthYear;
    private String gender;
    private String enterDate;

    public User() {
    }

    public User(int id, String name, String birthYear, String gender, String enterDate) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.enterDate = enterDate;
    }

    public enum Gender{
        WOMAN,
        MAN
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

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(String enterDate) {
        this.enterDate = enterDate;
    }
}
