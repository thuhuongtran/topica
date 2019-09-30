package vn.topica.itlab.spring.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.itlab.spring.sport.entity.Order;
import vn.topica.itlab.spring.sport.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findAll() {
        return (List<Order>) repository.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void save(Order order) {
        repository.save(order);
    }

    @Override
    public void remove(Order order) {
        repository.delete(order);
    }
}
