package com.codeup.repositories;

import com.codeup.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
