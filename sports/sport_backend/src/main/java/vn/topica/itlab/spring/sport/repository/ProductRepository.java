package vn.topica.itlab.spring.sport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.topica.itlab.spring.sport.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
