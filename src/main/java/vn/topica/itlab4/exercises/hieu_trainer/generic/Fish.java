package vn.topica.itlab4.exercises.hieu_trainer.generic;

public class Fish extends Animal {
    public Fish(String name) {
        super(name);
    }

    @Override
    public String say() {
        return String.format("I am %s. Fish says hello", getName());
    }
}
