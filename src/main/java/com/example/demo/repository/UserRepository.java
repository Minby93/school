package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByUsername(String username);

}
