package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
  List<Post> posts = new ArrayList<>();
  private final PostRepository postDao;

  public PostController(PostRepository postDao) {
    this.postDao = postDao;
  }

  @RequestMapping(value = "/posts", method = RequestMethod.GET)
  public String posts(Model model) {
    model.addAttribute("post",postDao.findAll());
    return "posts/index";
  }

//  @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
  @PostMapping("/posts/{id}")
  public String postById(@PathVariable long id, Model model) {


    Post postTwo = new Post();
    postTwo.setTitle("two");
    postTwo.setBody("body2");
    postDao.save(postTwo);
    model.addAttribute("post", postDao.getOne(id));
    return "posts/show";
  }

  @RequestMapping(value = "/posts/create", method = RequestMethod.GET)
  @ResponseBody
  public String createPost() {
    return "view the form for creating a post";
  }

  @RequestMapping(value = "posts/create", method = RequestMethod.POST)
  @ResponseBody
  public String createPostSent() {
    return "create a new post";
  }
}
