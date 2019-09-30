package vn.topica.itlab.spring.sport.service;

import vn.topica.itlab.spring.sport.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findAll();

    Cart findById(Integer id);

    void save(Cart cart);

    void remove(Cart cart);

    Cart findByUserId(Integer userId);
}
