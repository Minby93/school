package com.example.demo.repository;

import com.example.demo.domain.FileEntity;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findByUsername(String username);

    @Query(value = "select file_id from usr left join user_courses on usr.id = user_id where user_id = ?1", nativeQuery = true)
    List<Long> findCoursesByUserId(Long id);

    @Query(value = "select id from files where user_id = ?1", nativeQuery = true)
    List<Long> findMyCoursesByUserId(Long id);
}