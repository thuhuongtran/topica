package vn.topica.itlab.spring.sport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.topica.itlab.spring.sport.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM user WHERE mail= :mail AND pass= :pass LIMIT 1", nativeQuery = true)
    User findByMailAndPass(@Param("mail") String mail, @Param("pass") String pass);
}
