package vn.topica.itlab.spring.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.topica.itlab.spring.sport.entity.User;
import vn.topica.itlab.spring.sport.service.UserService;

import java.util.List;

@RestController
public class Controller {

    private UserService service;

    @Autowired
    public Controller(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "Hello World.";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUser() {
        List<User> users = service.findAllUser();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(
            @PathVariable("id") Integer id) {
        User user = service.findById(id);

        if (user == null) {
            return new ResponseEntity<>(user,
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(
            @RequestBody User user) {
        User findUser = service.findByMailAndPass(user.getMail(), user.getPass());
        if (findUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }
    @RequestMapping(value = "/user",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(
            @RequestBody User user) {
        service.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody User user) {
        User currentUser = service
                .findById(id);

        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentUser.setName(user.getName());
        currentUser.setMail(user.getMail());
        currentUser.setPhone(user.getPhone());

        service.save(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(
            @PathVariable("id") Integer id) {
        User user = service.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.remove(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
