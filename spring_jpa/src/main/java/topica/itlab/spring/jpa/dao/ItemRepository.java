package topica.itlab.spring.jpa.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import topica.itlab.spring.jpa.entity.Category;
import topica.itlab.spring.jpa.entity.Item;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    List<Item> findByCategory(Category category, Pageable pageable);
}
