package vn.topica.itlab.spring.sport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.topica.itlab.spring.sport.entity.Product;
import vn.topica.itlab.spring.sport.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(
            @PathVariable("id") Integer id) {
        Product prod = service.findById(id);
        if (prod == null) {
            return new ResponseEntity<>(prod,
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

}
