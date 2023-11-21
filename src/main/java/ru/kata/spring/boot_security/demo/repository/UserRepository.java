package ru.kata.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u left join fetch u.roles where u.firstName = :name")
    User findByUsername(@Param("name") String name);

    @Override
    Optional<User> findById(Long id);

    boolean existsByFirstName(String value);
}