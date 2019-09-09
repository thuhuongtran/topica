package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

public class User {
    private String phone;
    private String name;
    private Common.Status status = Common.Status.INIT;

    public User() {
    }

    public User(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }
}
