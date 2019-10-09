package topica.itlab.java8;

import topica.itlab.java8.factory.StageFactory;

public class App {
    private static StageFactory stage = StageFactory.INSTANCE;

    public static void main(String[] args) {
        System.out.println("---------------------Ex1------------------------");
        stage.countStudentInSchool();
        System.out.println("\n---------------------Ex2------------------------");
        stage.averageScoreInSchool();
        System.out.println("\n---------------------Ex3------------------------");
        stage.getClassHasMaxAverageInSchool();
        System.out.println("\n---------------------Ex4------------------------");
        stage.getBestWorstStudents();
        System.out.println("\n---------------------Ex5------------------------");
        stage.maxScoreByDomain();
        System.out.println("\n---------------------Ex6------------------------");
        stage.getDomainHasMaxScoreAndMaxRegister();
    }
}
