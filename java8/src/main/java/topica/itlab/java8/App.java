package topica.itlab.java8;

import topica.itlab.java8.entity.ClassOfSchool;
import topica.itlab.java8.entity.Subject;
import topica.itlab.java8.factory.AppFactory;

import java.util.Map;

public class App {
    private static AppFactory factory = AppFactory.INSTANCE;

    public static void main(String[] args) {
        System.out.println("---------------------Ex1------------------------");
        factory.countStudentsInSchool();
        System.out.println("\n---------------------Ex2------------------------");
        Map<ClassOfSchool, Map<Subject, Double>> averageScoreOfClass = factory.averageScore();
        System.out.println("\n---------------------Ex3------------------------");
        factory.getClassHasMaxScore(averageScoreOfClass);
        System.out.println("\n---------------------Ex4------------------------");
        factory.getBestWorstStudents();
        System.out.println("\n---------------------Ex5------------------------");
        factory.maxScoreByDomain();
        System.out.println("\n---------------------Ex6------------------------");
        factory.maxScoreAndMaxRegisterByDomain();
    }
}
