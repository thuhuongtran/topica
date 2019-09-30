package vn.topica.itlab.spring.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.itlab.spring.sport.entity.Cart;
import vn.topica.itlab.spring.sport.repository.CartRepository;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository repository;

    @Autowired
    public CartServiceImpl(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cart> findAll() {
        return (List<Cart>) repository.findAll();
    }

    @Override
    public Cart findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void save(Cart cart) {
        repository.save(cart);
    }

    @Override
    public void remove(Cart cart) {
        repository.delete(cart);
    }

    @Override
    public Cart findByUserId(Integer userId) {
        return repository.getByUserId(userId);
    }
}
