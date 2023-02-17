package com.example.banktest.repository;

import com.example.banktest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhonesPhone(String phone);

    Optional<User> findByEmailsEmail(String email);

    @Query(nativeQuery = true, value = "select distinct u.* from \"USER\" u \n" +
            "left join \"EMAIL_DATA\" ed on u.id = ed.user_id \n" +
            "left join \"PHONE_DATA\" pd on u.id  = pd.user_id \n" +
            "where u.\"name\" like ?1% \n" +
            "or ed.email = ?1 \n" +
            "or pd.phone = ?1 ORDER BY u.id OFFSET (?2 * ?3) ROWS FETCH NEXT ?3 ROWS ONLY")
    List<User> findAllBySearchQuery(String query, int page, int size);
}
