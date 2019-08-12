package vn.topica.itlab4.exercises.hieu_trainer.generic;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String say() {
        return String.format("I am %s. Dog says hello", getName());
    }
}
