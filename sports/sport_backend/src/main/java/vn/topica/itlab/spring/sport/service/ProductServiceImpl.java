package vn.topica.itlab.spring.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.itlab.spring.sport.entity.Product;
import vn.topica.itlab.spring.sport.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    public void remove(Product product) {
        repository.delete(product);
    }
}
