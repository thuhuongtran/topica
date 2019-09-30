package vn.topica.itlab.spring.sport.service;

import vn.topica.itlab.spring.sport.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findById(Integer id);

    void save(Order order);

    void remove(Order order);
}
