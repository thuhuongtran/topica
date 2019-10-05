package topica.itlab.java8.factory;

import topica.itlab.java8.entity.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class AppFactory {
    public final static AppFactory INSTANCE = new AppFactory();
    private DataFactory dataFactory = DataFactory.INSTANCE;

    private Map<ClassOfSchool, List<Student>> studentList; // List of student in same class in same school
    private Map<School, List<ClassOfSchool>> classList; // List of class in same school
    private Map<ClassOfSchool, Set<Subject>> subjectList; // List of subject in same class in same school
    private Map<ClassOfSchool, List<StudentSubjectRegister>> registerList; // List of subject student register in same class in same school

    private AppFactory() {
        this.classList = dataFactory.getListClassOfSchool();
        this.studentList = dataFactory.getListStudentInSameClassOfSchool(classList);
        this.subjectList = dataFactory.getSubjectInClassOfSchool(studentList);
        this.registerList = dataFactory.getRegisterInClassOfSchool(studentList);
    }

    public Map<ClassOfSchool, List<Student>> getStudentList() {
        return studentList;
    }

    public Map<School, List<ClassOfSchool>> getClassList() {
        return classList;
    }

    public Map<ClassOfSchool, Set<Subject>> getSubjectList() {
        return subjectList;
    }

    public Map<ClassOfSchool, List<StudentSubjectRegister>> getRegisterList() {
        return registerList;
    }

    /**
     * Count number of students in each class of each school
     * Then print out
     */
    public void countStudentsInSchool() {
        studentList.forEach((c, stl) -> {
            System.out.print(String.format("School-id: %d      Class-id: %d",
                    c.getSchoolId(),
                    c.getClassId()));
            System.out.print(String.format("        Number of students: %d\n", stl.size()));
        });
    }

    /**
     * Average score of each subject of class of school
     * Then print out
     */
    public Map<ClassOfSchool, Map<Subject, Double>> averageScore() {
        Map<ClassOfSchool, Map<Subject, Double>> averageScoreOfClass = new LinkedHashMap<>();
        subjectList.forEach((c, sjl) -> {
            Map<Subject, Double> averages = new LinkedHashMap<>();
            System.out.print(String.format("School-id: %d      Class-id: %d         Total subjects: %d\n",
                    c.getSchoolId(),
                    c.getClassId(),
                    subjectList.get(c).size()));
            subjectList.get(c)
                    .forEach(sj -> {
                        long count = registerList.get(c)
                                .stream()
                                .filter(r -> r.getSubjectId().equals(sj.getSubjectId()))
                                .count();
                        double average = registerList.get(c)
                                .stream()
                                .filter(r -> r.getSubjectId().equals(sj.getSubjectId()))
                                .mapToDouble(StudentSubjectRegister::getScore)
                                .average()
                                .getAsDouble();
                        averages.put(sj, average);
                        System.out.println(String
                                .format("      Subject-id: %d       Total Students: %d      Average: %.2f",
                                        sj.getSubjectId(),
                                        count,
                                        average));
                    });
            averageScoreOfClass.put(c, averages);

        });
        return averageScoreOfClass;
    }

    /**
     * Get class which has max average score of subject of class of school
     */
    public void getClassHasMaxScore(Map<ClassOfSchool, Map<Subject, Double>> averageScoreOfClass) {
        Map<Map<ClassOfSchool, Subject>, Double> maxAverageScoreOfClass = new LinkedHashMap<>();
        averageScoreOfClass.forEach((c, a) -> {
            Subject sj = a.entrySet().stream()
                    .max(Comparator.comparing(Map.Entry::getValue))
                    .get()
                    .getKey();
            maxAverageScoreOfClass.put(new HashMap<ClassOfSchool, Subject>() {{
                put(c, sj);
            }}, a.get(sj));
        });
        Map<ClassOfSchool, Subject> cs = maxAverageScoreOfClass.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
        System.out.println(String.format("School-id: %d      Class-id: %d         Subject-id: %d        Max average: %.2f\n",
                cs.keySet().iterator().next().getSchoolId(),
                cs.keySet().iterator().next().getClassId(),
                cs.values().iterator().next().getSubjectId(),
                maxAverageScoreOfClass.get(cs)));
    }

    /**
     * Get 10 best students who has max score of each subject of all classes in school
     * Get 10 worst students who has min score of each subject of all classes in school
     */
    public void getBestWorstStudents() {
        // get all list score by subject of all classes in a school
        Map<School, Map<Subject, List<StudentSubjectRegister>>> subjectSchool = new LinkedHashMap<>();
        classList.forEach((sc, cl) -> {
            Map<Subject, List<StudentSubjectRegister>> studentSubject = new LinkedHashMap<>();
            cl.forEach(c -> subjectList.get(c)
                    .forEach(sj -> {
                        List<StudentSubjectRegister> rLi = studentSubject.get(sj) != null
                                ? studentSubject.get(sj)
                                : new ArrayList<>();
                        rLi.addAll(registerList.get(c)
                                .stream()
                                .filter(r -> r.getSubjectId().equals(sj.getSubjectId()))
                                .collect(Collectors.toList()));
                        if (studentSubject.get(sj) == null) studentSubject.put(sj, rLi);
                        else studentSubject.replace(sj, rLi);

                    }));
            subjectSchool.put(sc, studentSubject);
        });
        // print best and worst students
        subjectSchool.forEach((sc, rsl) -> {
            System.out.print(String.format("School-id: %d ---------------------------------\n",
                    sc.getSchoolId()));
            rsl.forEach((sj, rl) -> {
                System.out.print(String.format("--------Subject-id: %d \n", sj.getSubjectId()));
                rl = rl.stream()
                        .sorted(Comparator.comparingDouble(StudentSubjectRegister::getScore).reversed())
                        .collect(Collectors.toList());
                if (rl.size() <= 10) {
                    System.out.println(String
                            .format("        Only %d students register for this subject",
                                    rl.size()));
                    rl.subList(0, rl.size()).forEach(r -> System.out.println(String
                            .format("Student-id: %d, Score: %.1f",
                                    r.getStudentId(),
                                    r.getScore())));
                } else {
                    System.out.println("        10 best student");
                    rl.subList(0, 10).forEach(r -> System.out.println(String
                            .format("Student-id: %d, Score: %.1f",
                                    r.getStudentId(),
                                    r.getScore())));
                    System.out.println("        10 worst student");
                    new LinkedList<>(rl.subList(rl.size() - 11, rl.size()))
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
        // average score by subject domain in a class of school
        Map<ClassOfSchool, Map<SubjectDomain, Double>> averageDomainClass = new LinkedHashMap<>();
        registerList.forEach((c, rl) -> {
            Map<SubjectDomain, Double> averageDomain = new LinkedHashMap<>();
            SubjectDomain.getListDomain().forEach(sd ->
                    averageDomain.put(sd,
                            Double.parseDouble(String.format("%.2f",
                                    rl.stream().filter(r ->
                                            subjectList.get(c).stream()
                                                    .anyMatch(sj ->
                                                            sj.getSubjectId().equals(r.getSubjectId())
                                                                    && sj.getDomain().equals(sd)))
                                            .mapToDouble(StudentSubjectRegister::getScore)
                                            .average()
                                            .orElse(0)))));
            averageDomainClass.put(c, averageDomain);
        });
        // find class has max score in each subject domain
        classList.forEach((sc, cl) -> {
            System.out.println("       School-id: " + sc.getSchoolId());
            SubjectDomain.getListDomain().forEach(sd -> {
                System.out.print(sd.name());
                ClassOfSchool c = averageDomainClass.entrySet().stream()
                        .filter(e -> cl.contains(e.getKey()))
                        .max(Comparator.comparingDouble(e -> e.getValue().get(sd)))
                        .get()
                        .getKey();
                System.out.println(String.format("    Class-id: %d,    Max-score: %.2f",
                        c.getClassId(),
                        averageDomainClass.get(c).get(sd)
                ));
            });
        });
    }

    /**
     * Find subject domain which has highest score in each school
     * Find subject domain which has max registers in each school
     */
    public void maxScoreAndMaxRegisterByDomain() {
        // get all list register by subject domain of all classes in a school
        Map<School, Map<SubjectDomain, List<StudentSubjectRegister>>> domainSchool = new LinkedHashMap<>();
        classList.forEach((sc, cl) -> {
            Map<SubjectDomain, List<StudentSubjectRegister>> domainRegister = new LinkedHashMap<>();
            cl.forEach(c -> SubjectDomain.getListDomain()
                    .forEach(sd -> {
                        List<StudentSubjectRegister> rLi = domainRegister.get(sd) != null
                                ? domainRegister.get(sd)
                                : new ArrayList<>();
                        rLi.addAll(registerList.get(c)
                                .stream()
                                .filter(r -> subjectList.get(c).stream()
                                        .anyMatch(sj ->
                                                sj.getSubjectId().equals(r.getSubjectId())
                                                        && sj.getDomain().equals(sd)))
                                .collect(Collectors.toList()));
                        rLi = rLi.stream()
                                .sorted(Comparator.comparingDouble(StudentSubjectRegister::getScore).reversed())
                                .collect(Collectors.toList());
                        if (domainRegister.get(sd) == null) domainRegister.put(sd, rLi);
                        else domainRegister.replace(sd, rLi);
                    }));
            domainSchool.put(sc, domainRegister);
        });
        domainSchool.forEach((sc, drl) -> {
            System.out.print(String.format("School-id: %d ---------------------------------\n",
                    sc.getSchoolId()));
            // get subject domain which has max registers in each school
            SubjectDomain sdr = drl.entrySet().stream()
                    .max(Comparator.comparingInt(e -> e.getValue().size()))
                    .get()
                    .getKey();
            System.out.println("    " + sdr.name() + "    Max-registry: " + drl.get(sdr).size());
            // get subject domain which has highest score in each school
            SubjectDomain sds = drl.entrySet().stream()
                    .max(Comparator.comparingDouble(e -> e.getValue().get(0).getScore()))
                    .get()
                    .getKey();
            System.out.println(String.format("    %s    Max-score: %.1f",
                    sds.name(),
                    drl.get(sds).get(0).getScore()));
        });
    }
}