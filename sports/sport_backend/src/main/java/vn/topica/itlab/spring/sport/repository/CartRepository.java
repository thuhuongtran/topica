package vn.topica.itlab.spring.sport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.topica.itlab.spring.sport.entity.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

    @Query(value = "SELECT * FROM cart WHERE user_id = :userId", nativeQuery = true)
    Cart getByUserId(@Param("userId") Integer userId);
}
