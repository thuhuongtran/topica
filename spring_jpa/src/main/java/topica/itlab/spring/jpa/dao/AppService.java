package topica.itlab.spring.jpa.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import topica.itlab.spring.jpa.entity.Category;
import topica.itlab.spring.jpa.entity.Item;
import topica.itlab.spring.jpa.entity.Type;

import java.util.List;

@Service
public class AppService {
    @Autowired
    private CategoryRepository cateRepo;
    @Autowired
    private ItemRepository itemRepo;
    @Autowired
    private TypeRepository typeRepo;

    private ObjectMapper om = new ObjectMapper();

    public List<Category> findCategoryByType(String typeName, int pageIndex, int pageSize) {
        Type type = typeRepo.findByName(typeName);
        if (type == null) {
            return null;
        }
        List<Category> cats = cateRepo.findByType(type, PageRequest.of(pageIndex, pageSize));
        cats.forEach(c -> {
            try {
                System.out.println(om.writeValueAsString(c));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return cats;
    }

    public List<Item> findItemByCategory(String catName, int pageIndex, int pageSize) {
        Category cat = cateRepo.findByName(catName);
        if (cat == null) {
            return null;
        }
        List<Item> items = itemRepo.findByCategory(cat, PageRequest.of(pageIndex, pageSize));
        return items;
    }

    public Category updateCategory(Long id, String name) {
        Category cat = cateRepo.findById(id).get();
        if (cat == null) {
            return null;
        }
        cat.setName(name);
        cateRepo.save(cat);
        try {
            System.out.println(om.writeValueAsString(cat));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return cat;
    }
}
