package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

public class User {
    private String phone;
    private String name;

    public User() {
    }

    public User(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
