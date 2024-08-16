package com.javakun.usermanagementsystem.repository;

import com.javakun.usermanagementsystem.model.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);

    List<User> findUserById(Long id);
}
