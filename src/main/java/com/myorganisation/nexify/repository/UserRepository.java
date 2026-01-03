package com.myorganisation.nexify.repository;

import com.myorganisation.nexify.enums.Gender;
import com.myorganisation.nexify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom finder methods
    User findByUsername(String username);
    List<User> findByUsernameContaining(String username);
    List<User> findByName(String name);

    // JPQL
    @Query("SELECT u FROM User u WHERE u.name = :name AND u.gender = :gender")
    List<User> searchByNameAndGender(@Param("name") String name, @Param("gender") Gender gender);

    // Native Query - SQL
    @Query(value = "SELECT * FROM users u WHERE u.name = :name AND u.gender = :gender", nativeQuery = true)
    List<User> searchByNameAndGenderUsingSql(@Param("name") String name, @Param("gender") String gender);
}
