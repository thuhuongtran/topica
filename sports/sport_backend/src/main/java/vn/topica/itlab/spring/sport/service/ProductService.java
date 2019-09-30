package vn.topica.itlab.spring.sport.service;

import vn.topica.itlab.spring.sport.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    void save(Product product);

    void remove(Product product);

}
