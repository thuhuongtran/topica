package topica.itlab.spring.jpa.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import topica.itlab.spring.jpa.entity.Category;
import topica.itlab.spring.jpa.entity.Type;

import java.util.List;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    List<Category> findByType(Type type, Pageable pageable);

    Category findByName(String name);
}
