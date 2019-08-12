package vn.topica.itlab4.exercises.hieu_trainer.generic;

public class Tiger extends Animal {

    public Tiger(String name) {
        super(name);
    }

    @Override
    public String say() {
        return String.format("I am %s. Tiger says hello", getName());
    }
}
