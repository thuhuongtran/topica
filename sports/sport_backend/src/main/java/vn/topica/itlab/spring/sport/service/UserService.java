package vn.topica.itlab.spring.sport.service;

import vn.topica.itlab.spring.sport.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    User findById(Integer id);

    void save(User blog);

    void remove(User blog);

    User findByMailAndPass(String mail, String pass);
}
