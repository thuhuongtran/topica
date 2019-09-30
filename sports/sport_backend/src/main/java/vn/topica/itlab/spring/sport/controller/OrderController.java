package vn.topica.itlab.spring.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.topica.itlab.spring.sport.entity.Order;
import vn.topica.itlab.spring.sport.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> create(
            @RequestBody Order order) {
        service.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getById(
            @PathVariable("id") Integer id) {
        Order order = service.findById(id);
        if (order == null) {
            return new ResponseEntity<>(order,
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
