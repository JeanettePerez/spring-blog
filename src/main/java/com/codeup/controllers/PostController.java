package com.codeup.controllers;

import com.codeup.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
  List<Post> posts = new ArrayList<>();


  @RequestMapping(value = "/posts", method = RequestMethod.GET)
  public String posts(Model model) {

   posts.add(new Post("testing title", "testing body"));
    posts.add(new Post("testing title", "testing body"));
    model.addAttribute("post",posts);
    return "posts/index";
  }

  @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
  public String postById(@PathVariable int id) {
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
