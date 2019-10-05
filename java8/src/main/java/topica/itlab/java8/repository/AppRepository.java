package topica.itlab.java8.repository;

import topica.itlab.java8.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppRepository {
    public final static AppRepository INSTANCE = new AppRepository();
    private Connection connection;

    private AppRepository() {
        connection = getConnection();
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/topica", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select all student from database
     *
     * @return students
     */
    public List<Student> getListStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM jv8_student";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("id"));
                student.setStudentName(rs.getString("name"));
                student.setStudentMobile(rs.getString("mobile"));
                student.setClassId(rs.getInt("class_id"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * Select all subjects from database
     *
     * @return subjects
     */
    public List<Subject> getListSubject() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM jv8_subject";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("id"));
                subject.setSubjectName(rs.getString("name"));
                subject.setSubjectDesc(rs.getString("desc"));
                subject.setDomain(rs.getString("domain"));
                subjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    /**
     * Select all classes from database
     *
     * @return classes
     */
    public List<ClassOfSchool> getListClass() {
        List<ClassOfSchool> classes = new ArrayList<>();
        String sql = "SELECT * FROM jv8_class";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ClassOfSchool clazz = new ClassOfSchool();
                clazz.setClassId(rs.getInt("id"));
                clazz.setClassCode(rs.getString("code"));
                clazz.setClassDesc(rs.getString("desc"));
                clazz.setSchoolId(rs.getInt("school_id"));
                classes.add(clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * Select all schools from database
     *
     * @return schools
     */
    public List<School> getListSchool() {
        List<School> schools = new ArrayList<>();
        String sql = "SELECT * FROM jv8_school";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                School school = new School();
                school.setSchoolId(rs.getInt("id"));
                school.setSchoolName(rs.getString("name"));
                school.setSchoolDesc(rs.getString("desc"));
                schools.add(school);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schools;
    }

    /***
     * Select all student register subject from database
     * @return registers
     */
    public List<StudentSubjectRegister> getListStudentSubjectRegister() {
        List<StudentSubjectRegister> registers = new ArrayList<>();
        String sql = "SELECT * FROM jv8_student_subject";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                StudentSubjectRegister register = new StudentSubjectRegister();
                register.setStudentId(rs.getInt("st_id"));
                register.setSubjectId(rs.getInt("sj_id"));
                register.setScore(rs.getDouble("score"));
                registers.add(register);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registers;
    }
}
