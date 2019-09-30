package vn.topica.itlab.spring.sport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.topica.itlab.spring.sport.entity.User;
import vn.topica.itlab.spring.sport.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository respository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.respository = repository;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) respository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return respository.findOne(id);
    }

    @Override
    public void save(User user) {
        respository.save(user);
    }

    @Override
    public void remove(User user) {
        respository.delete(user);
    }

    @Override
    public User findByMailAndPass(String mail, String pass) {
        return respository.findByMailAndPass(mail, pass);
    }
}
