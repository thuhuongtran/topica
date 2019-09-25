package vn.topica.itlab4.jdbc.hibernate.factory;

import vn.topica.itlab4.jdbc.hibernate.entity.ClassHib;
import vn.topica.itlab4.jdbc.hibernate.entity.StudentHib;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HibFactory {
    private final static HibFactory INSTANCE = new HibFactory();
    private final SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();
    private final Session session = sessionFactory.getCurrentSession();

    public void saveEntities() {
        Transaction tx = session.beginTransaction();
        ClassHib ch = new ClassHib("Football", "Soccer 3");
        session.save(ch);
        session.save(new StudentHib("April", ch));
        session.save(new StudentHib("March", ch));
        tx.commit();
    }

    public void getEntities() {
        session.beginTransaction();
//        List<StudentHib> sts = (List<StudentHib>) session.createQuery("from StudentHib").list();
//        sts.forEach(s -> System.out.println(s.getId() + "-" + s.getName()));
        List<ClassHib> cls = (List<ClassHib>) session.createQuery("from ClassHib").list();
        cls.forEach(c -> System.out.println(c.getClsID() + "-" + c.getName() + "-" + c.getDescription()
//                +"-"+c.getListStudent().size()
        ));
        // test fetch lazy
//        cls.forEach(c ->
//                c.getListStudent()
//                        .forEach(s -> System.out.println(s.getStID() + "_" + s.getStName())));
    }

    public static void main(String[] args) {
//        HibFactory.INSTANCE.saveEntities();
        HibFactory.INSTANCE.getEntities();
    }
}
