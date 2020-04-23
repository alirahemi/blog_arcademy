package ir.arcademy.blog.repository;

import ir.arcademy.blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    // JPQL
    /*@Query("select u from Users u where u.email=:email")
    Users findByQuery(@Param("email") String email);*/

    // Native SQL
    /*@Query(nativeQuery = true, value = "select * from users where email=...")
    Users findByEmail();*/
}
