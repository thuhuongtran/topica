package vn.topica.itlab.spring.sport.service;

import vn.topica.itlab.spring.sport.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    void save(User user);

    void remove(User user);

    User findByMailAndPass(String mail, String pass);
}
