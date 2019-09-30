package vn.topica.itlab.spring.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.topica.itlab.spring.sport.entity.Cart;
import vn.topica.itlab.spring.sport.entity.User;
import vn.topica.itlab.spring.sport.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> getById(
            @PathVariable("id") Integer id) {
        Cart cart = service.findByUserId(id);
        if (cart == null) {
            return new ResponseEntity<>(cart,
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> create(
            @RequestBody Cart cart) {
        Cart oldCart = service.findByUserId(cart.getUserId());
        if (oldCart != null) {
            oldCart.setTotal(oldCart.getTotal() + cart.getTotal());
            oldCart.setProdIds(oldCart.getProdIds() + "," + cart.getProdIds());
            service.save(oldCart);
            return new ResponseEntity<>(oldCart, HttpStatus.OK);
        }
        service.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cart> edit(
            @RequestBody Cart cart) {
        Cart oldCart = service.findByUserId(cart.getUserId());
        if (oldCart != null) {
            oldCart.setTotal(oldCart.getTotal() - cart.getTotal());
            String ids = oldCart.getProdIds()
                    .replace(String.format(",%s", cart.getProdIds()),
                            "");
            oldCart.setProdIds(ids);
            service.save(oldCart);
            return new ResponseEntity<>(oldCart, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(
            @PathVariable("id") Integer id) {
        Cart cart = service.findByUserId(id);
        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.remove(cart);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
