package topica.itlab.spring.jpa.dao;

import org.springframework.data.repository.CrudRepository;
import topica.itlab.spring.jpa.entity.Type;

public interface TypeRepository extends CrudRepository<Type, Long>{
    Type findByName(String name);
}
