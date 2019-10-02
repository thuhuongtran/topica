package topica.itlab.spring.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import topica.itlab.spring.jpa.dao.AppService;
import topica.itlab.spring.jpa.entity.Category;
import topica.itlab.spring.jpa.entity.Item;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private AppService service;

    @RequestMapping(value = "/categories",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Category>> getCategoriesByType(
            @RequestParam(name = "type") String type,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "20") int limit) {
        List<Category> cats = service.findCategoryByType(type, offset, limit);
        if (cats == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @RequestMapping(value = "/items",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getItemsByCategory(
            @RequestParam(name = "category") String category,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "20") int limit) {
        List<Item> items = service.findItemByCategory(category, offset, limit);
        if (items == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/category",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "name") String name) {
        Category cat = service.updateCategory(id, name);
        if (cat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

}
