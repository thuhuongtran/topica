package topica.itlab.java8.factory;

import topica.itlab.java8.entity.*;
import topica.itlab.java8.repository.AppRepository;

import java.util.*;
import java.util.stream.Collectors;

public class StageFactory {
    public final static StageFactory INSTANCE = new StageFactory();
    private AppRepository repository = AppRepository.INSTANCE;
    private List<School> schools;
    private List<Subject> subjects;
    private List<ClassOfSchool> classes;
    private List<Student> students;
    private List<StudentSubjectRegister> registers;

    private StageFactory() {
        this.schools = repository.getListSchool();
        this.classes = repository.getListClass();
        this.students = repository.getListStudent();
        this.subjects = repository.getListSubject();
        this.registers = repository.getListStudentSubjectRegister();
    }

    /**
     * Count number of students in each class of each school
     * Then print out
     */
    public void countStudentInSchool() {
        students.stream()
                .collect(Collectors.groupingBy(st ->
                                classes.stream()
                                        .filter(c -> c.getClassId().equals(st.getClassId()))
                                        .findFirst()
                                        .get()
                                        .getSchoolId(),
                        Collectors.groupingBy(Student::getClassId,
                                Collectors.counting())))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().values().stream()
                                .mapToInt(Long::intValue).sum()
                ))
                .forEach((sc, sts) -> System.out.println("School-id: " + sc + " sum all students: " + sts));
    }

    /**
     * Average score of each subject of each class of each school
     * Then print out
     */
    public void averageScoreInSchool() {
        registers.stream()
                .collect(Collectors.groupingBy(r ->
                                students.stream()
                                        .collect(Collectors.groupingBy(st ->
                                                        classes.stream()
                                                                .filter(c -> c.getClassId().equals(st.getClassId()))
                                                                .findFirst()
                                                                .get()
                                                                .getSchoolId(),
                                                Collectors.groupingBy(Student::getClassId,
                                                        Collectors.toList())))
                                        .entrySet().stream()
                                        .collect(Collectors.toMap(
                                                Map.Entry::getKey,
                                                e -> e.getValue().entrySet().stream()
                                                        .map(Map.Entry::getValue)
                                                        .flatMap(Collection::stream)
                                                        .distinct()
                                                        .collect(Collectors.toList())
                                        ))
                                        .entrySet().stream()
                                        .filter(e -> e.getValue().stream()
                                                .anyMatch(st ->
                                                        st.getStudentId().equals(r.getStudentId())))
                                        .findFirst()
                                        .get()
                                        .getKey()
                        , Collectors.groupingBy(StudentSubjectRegister::getSubjectId,
                                Collectors.averagingDouble(StudentSubjectRegister::getScore))))
                .forEach((sc, sjl) -> {
                    System.out.println("School-id: " + sc);
                    sjl.forEach((sj, a) -> System.out.println(String
                            .format("     Subject-Id: %d,    Average: %.3f", sj, a)));
                });
    }

    /**
     * Get class which has max average score of each subject in each class of each school
     */
    public void getClassHasMaxAverageInSchool() {
        registers.stream()
                .collect(Collectors.groupingBy(r ->
                                students.stream()
                                        .collect(Collectors.groupingBy(st ->
                                                classes.stream()
                                                        .filter(c -> c.getClassId().equals(st.getClassId()))
                                                        .findFirst()
                                                        .get()))
                                        .entrySet().stream()
                                        .filter(e -> e.getValue().stream()
                                                .anyMatch(st -> st.getStudentId().equals(r.getStudentId())))
                                        .findFirst()
                                        .get()
                                        .getKey()
                        , Collectors.groupingBy(StudentSubjectRegister::getSubjectId,
                                Collectors.averagingDouble(StudentSubjectRegister::getScore))))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().values().stream()
                                .max(Double::compareTo)
                                .get()))
                .entrySet().stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getSchoolId(),
                        Collectors.maxBy(
                                Comparator.comparingDouble(Map.Entry::getValue))))
                .forEach((sc, csco) ->
                        System.out.println("School-Id: " + sc + "    Class-Id: " + csco.get().getKey().getClassId()
                                + "     Max Average: " + csco.get().getValue()));
    }

    /**
     * Get 10 best students who has max score of each subject of all classes in school
     * Get 10 worst students who has min score of each subject of all classes in school
     */
    public void getBestWorstStudents() {
        registers.stream()
                .collect(Collectors.groupingBy(r ->
                                students.stream()
                                        .collect(Collectors.groupingBy(st ->
                                                        classes.stream()
                                                                .filter(c -> c.getClassId().equals(st.getClassId()))
                                                                .findFirst()
                                                                .get()
                                                                .getSchoolId(),
                                                Collectors.groupingBy(Student::getClassId,
                                                        Collectors.toList())))
                                        .entrySet().stream()
                                        .collect(Collectors.toMap(
                                                Map.Entry::getKey,
                                                e -> e.getValue().entrySet().stream()
                                                        .map(Map.Entry::getValue)
                                                        .flatMap(Collection::stream)
                                                        .distinct()
                                                        .collect(Collectors.toList())
                                        ))
                                        .entrySet().stream()
                                        .filter(e -> e.getValue().stream()
                                                .anyMatch(st ->
                                                        st.getStudentId().equals(r.getStudentId())))
                                        .findFirst().get().getKey()
                        , Collectors.groupingBy(StudentSubjectRegister::getSubjectId)))
                .forEach((sc, sjrs) -> {
                    System.out.println(String.format("School-id: %d-------------------------------------", sc));
                    sjrs.forEach((sj, rl) -> {
                        System.out.print("      Subject-Id: " + sj);
                        List<StudentSubjectRegister> rs = rl.stream()
                                .sorted(Comparator.comparingDouble(
                                        StudentSubjectRegister::getScore)
                                        .reversed())
                                .collect(Collectors.toList());
                        if (rs.size() <= 10) {
                            System.out.println(String
                                    .format("---------Only %d students register for this subject",
                                            rs.size()));
                            rs.subList(0, rs.size()).forEach(r -> System.out.println(String
                                    .format("Student-id: %d, Score: %.1f",
                                            r.getStudentId(),
                                            r.getScore())));
                        } else {
                            System.out.println("        10 best student");
                            rs.subList(0, 10).forEach(r -> System.out.println(String
                                    .format("Student-id: %d, Score: %.1f",
                                            r.getStudentId(),
                                            r.getScore())));
                            System.out.println("        10 worst student");
                            new LinkedList<>(rs.subList(rs.size() - 11, rs.size()))
                                    .descendingIterator()
                                    .forEachRemaining(r -> System.out.println(String
                                            .format("Student-id: %d, Score: %.1f",
                                                    r.getStudentId(),
                                                    r.getScore())));
                        }
                    });
                });
    }

    /**
     * Get class has max average score by subject domain in each school
     */
    public void maxScoreByDomain() {
        registers.stream()
                .collect(Collectors.groupingBy(r -> classes.stream().filter(c -> schools.stream()
                                .anyMatch(sc -> sc.getSchoolId().equals(c.getSchoolId()))
                                && students.stream().anyMatch(st -> st.getClassId().equals(c.getClassId())
                                && st.getStudentId().equals(r.getStudentId())))
                                .findFirst().get().getSchoolId(),
                        Collectors.groupingBy(r -> subjects.stream().filter(sj ->
                                        sj.getSubjectId().equals(r.getSubjectId()))
                                        .findFirst().get().getDomain(),
                                Collectors.groupingBy(r -> students.stream().filter(st -> classes.stream()
                                                .anyMatch(c -> c.getClassId().equals(st.getClassId())
                                                        && schools.stream().anyMatch(sc ->
                                                        sc.getSchoolId().equals(c.getSchoolId())))
                                                && st.getStudentId().equals(r.getStudentId()))
                                                .findFirst().get().getClassId(),
                                        Collectors.averagingDouble(StudentSubjectRegister::getScore)))))
                .forEach((sc, dca) -> {
                    System.out.println("-----------------School-Id: " + sc);
                    dca.forEach((d, ca) -> {
                        System.out.print(d);
                        Integer cid = ca.entrySet().stream()
                                .max(Comparator.comparingDouble(Map.Entry::getValue))
                                .get().getKey();
                        System.out.println("    Class-Id: " + cid + "       Max average: " + ca.get(cid));
                    });
                });
    }

    /**
     * Find subject domain which has highest score in each school
     * Find subject domain which has max registers in each school
     */
    public void getDomainHasMaxScoreAndMaxRegister() {
        Map<Integer, Map<SubjectDomain, List<StudentSubjectRegister>>> rMap = registers.stream()
                .collect(Collectors.groupingBy(r -> classes.stream().filter(c -> schools.stream()
                                .anyMatch(sc -> sc.getSchoolId().equals(c.getSchoolId()))
                                && students.stream().anyMatch(st -> st.getClassId().equals(c.getClassId())
                                && st.getStudentId().equals(r.getStudentId())))
                                .findFirst().get().getSchoolId(),
                        Collectors.groupingBy(r -> subjects.stream().filter(sj ->
                                sj.getSubjectId().equals(r.getSubjectId()))
                                .findFirst().get().getDomain())));
        rMap.forEach((sc, dr) -> {
            System.out.println("-------------School-Id: " + sc);
            // get domain which has highest score in each school
            Map.Entry<SubjectDomain, Double> dsc = dr.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                    e -> e.getValue().stream().mapToDouble(StudentSubjectRegister::getScore)
                            .max().getAsDouble()))
                    .entrySet().stream()
                    .max(Comparator.comparingDouble(Map.Entry::getValue)).get();
            System.out.println("    " + dsc.getKey() + "     Max score: " + dsc.getValue());
            // get domain which has max registers in each school
            Map.Entry<SubjectDomain, Integer> dmr = dr.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            e -> e.getValue().size()))
                    .entrySet().stream()
                    .max(Comparator.comparingInt(Map.Entry::getValue)).get();
            System.out.println("    " + dmr.getKey() + "     Max register: " + dmr.getValue());
        });
    }
}
