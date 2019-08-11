package vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.db;

import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.People;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Student;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Subject;
import vn.topica.itlab4.exercises.hieu_trainer.exercises18_36.entity.Teacher;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

public class DbFactory {
    private static SessionFactory SESSION_FACTORY;
    private static DbFactory factory = new DbFactory();

    private DbFactory() {

    }
    /**
     * Initialize Session-factory for hibernate
     * @return DbFactory
     */
    public static DbFactory getInstance() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry sr = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SESSION_FACTORY = configuration.buildSessionFactory(sr);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return factory;
    }

    /**
     * Add any object in DB
     * @param obj
     */
    public void addObj(Object obj) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(obj); // add new object
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    /**
     * Add list of object in DB
     * @param objs
     */
    public void addListObj(List<?> objs) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (Object obj : objs) {
                session.save(obj);
            }
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    /**
     * Get list of object class from DB
     * @param className - can be student or teacher or subject
     * @return List of student or list of teacher or list of subject
     */
    public List<?> listObj(String className) {
        Session session = SESSION_FACTORY.openSession();
        List<?> objs = new ArrayList<>();

        try {
            switch (className) {
                case "Student":
                    List<Student> students = session
                            .createSQLQuery("select * from student")
                            .list();
                    objs = students;
                    break;
                case "Teacher":
                    List<Teacher> teachers = session
                            .createSQLQuery("select * from teacher")
                            .list();
                    objs = teachers;
                    break;
                case "Subject":
                    List<Subject> subjects = session
                            .createSQLQuery("select * from subject")
                            .list();
                    objs = subjects;
                    break;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return objs;
    }

    /**
     * Update by sql
     * @param sql - a sql inputted by user
     */
    public void updateObjBySql(String sql) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    /**
     * Update student - add sex in student in DB
     * @param id
     * @param sex
     */
    public void updateStudent(int id, People.sex sex) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Student student = (Student) session.get(Student.class, id); // get student by id
            student.setSex(sex.name());
            session.saveOrUpdate(student); // update student
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
