package topica.itlab.db;

import org.junit.Test;
import topica.itlab.java8.repository.AppRepository;

public class RepositoryTest {
    private AppRepository repository = AppRepository.INSTANCE;

    @Test
    public void testSelect() {
        System.out.println(repository.getListStudentSubjectRegister().size());
    }
    
}
