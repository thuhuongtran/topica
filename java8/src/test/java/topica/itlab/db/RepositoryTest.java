package topica.itlab.db;

import org.junit.Test;
import topica.itlab.java8.entity.Subject;
import topica.itlab.java8.factory.AppFactory;
import topica.itlab.java8.factory.DataFactory;
import topica.itlab.java8.repository.AppRepository;

import java.util.Comparator;

public class RepositoryTest {
    private AppRepository repository = AppRepository.INSTANCE;
    private DataFactory factory = DataFactory.INSTANCE;
    private AppFactory app = AppFactory.INSTANCE;

    @Test
    public void testSelect() {
        System.out.println(repository.getListStudentSubjectRegister().size());
    }

    @Test
    public void getStudentList() {
        app.getStudentList()
                .forEach((c, sl) -> {
                    System.out.println("size: " + sl.size() + " cid: " + c.getClassId());
                    sl.forEach(st -> System.out.println(st.getStudentId()));
                });
    }

    @Test
    public void getSubjectList() {
        app.getSubjectList()
                .forEach((c, sjl) -> {
                    System.out.println("size = " + sjl.size() + " cid: " + c.getClassId());
                    sjl.forEach(sj ->
                            System.out.println(sj.getSubjectId()));
                });
    }

    @Test
    public void getRegisterList() {
        app.getRegisterList()
                .forEach((c, rl) -> {
                    System.out.println("------------size = " + rl.size() + " cid: " + c.getClassId());
                    rl.forEach(r ->
                            System.out.println("sjid: "+r.getSubjectId() +" - stid: "+r.getStudentId()));
                });
    }

    @Test
    public void average() {
        app.averageScore();
    }
}
