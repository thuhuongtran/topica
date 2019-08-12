package vn.topica.itlab4.exercises.hieu_trainer.generic;

import java.util.ArrayList;
import java.util.List;

public class Zoo<T extends Animal> {
    private List<T> animals = new ArrayList<>();

    public void add(T obj) {
        animals.add(obj);
    }

    public void remove(T obj) {
        animals.remove(obj);
    }

    public void sayHello() {
        for (T a : animals) {
            System.out.println(a.say());
        }
    }

}
