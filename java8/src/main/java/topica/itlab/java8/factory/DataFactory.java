package topica.itlab.java8.factory;

import topica.itlab.java8.entity.*;
import topica.itlab.java8.repository.AppRepository;

import java.util.*;
import java.util.stream.Collectors;

public class DataFactory {
    public final static DataFactory INSTANCE = new DataFactory();
    private AppRepository repository = AppRepository.INSTANCE;
    private List<School> schools;
    private List<Subject> subjects;
    private List<ClassOfSchool> classes;
    private List<Student> students;
    private List<StudentSubjectRegister> registers;

    private DataFactory() {
        this.schools = repository.getListSchool();
        this.classes = repository.getListClass();
        this.students = repository.getListStudent();
        this.subjects = repository.getListSubject();
        this.registers = repository.getListStudentSubjectRegister();
    }

    /**
     * From schools and classes selected from DB
     * Then get list of class which is in same school (has same school-id)
     *
     * @return classList
     */
    public Map<School, List<ClassOfSchool>> getListClassOfSchool() {
        Map<School, List<ClassOfSchool>> classList = new LinkedHashMap<>();
        schools.forEach(sc ->
        {
            List<ClassOfSchool> classOfSchools = classes.stream()
                    .filter(c -> c.getSchoolId().equals(sc.getSchoolId()))
                    .collect(Collectors.toList());
            classList.put(sc, classOfSchools);
        });
        return classList;
    }

    /**
     * From students get from DB and list of class in same school
     * Then get list of student which is in same class (has same class-id) and in same school
     *
     * @return studentList
     */
    public Map<ClassOfSchool, List<Student>> getListStudentInSameClassOfSchool(Map<School, List<ClassOfSchool>> classList) {
        Map<ClassOfSchool, List<Student>> studentList = new LinkedHashMap<>();
        classList.values().forEach(cl ->
                cl.forEach(c -> {
                    List<Student> stLi = students.stream()
                            .filter(st -> st.getClassId().equals(c.getClassId()))
                            .collect(Collectors.toList());
                    studentList.put(c, stLi);
                }));
        return studentList;
    }

    /**
     * From subjects selected from DB and list of student of each class of school
     * Then get list of subject which is in a class of a school
     *
     * @return subjectList
     */
    public Map<ClassOfSchool, Set<Subject>> getSubjectInClassOfSchool(Map<ClassOfSchool, List<Student>> studentList) {
        Map<ClassOfSchool, Set<Subject>> subjectList = new LinkedHashMap<>();
        studentList.forEach((c, sl) -> {
            Set<Subject> sjLi = new LinkedHashSet<>();
            sl.forEach(st ->
                    sjLi.addAll(subjects.stream()
                            .filter(sj -> registers.stream()
                                    .anyMatch(r -> r.getSubjectId().equals(sj.getSubjectId())
                                            && r.getStudentId().equals(st.getStudentId())))
                            .collect(Collectors.toList())));
            subjectList.put(c, sjLi);
        });
        return subjectList;
    }

    /**
     * From registers selected from DB and list of student of each class of school
     * Then get list of student-register-subject which is in a class of a school
     *
     * @return subjectList
     */
    public Map<ClassOfSchool, List<StudentSubjectRegister>> getRegisterInClassOfSchool(Map<ClassOfSchool, List<Student>> studentList) {
        Map<ClassOfSchool, List<StudentSubjectRegister>> registerList = new LinkedHashMap<>();
        studentList.forEach((c, sl) -> {
            List<StudentSubjectRegister> rLi = new ArrayList<>();
            sl.forEach(st ->
                    rLi.addAll(registers.stream()
                            .filter(r -> r.getStudentId().equals(st.getStudentId()))
                            .collect(Collectors.toList())));
            registerList.put(c, rLi);
        });
        return registerList;
    }
}
