package com.codeup.repositories;

import com.codeup.models.Post;
import com.codeup.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByUser(User user);
}
