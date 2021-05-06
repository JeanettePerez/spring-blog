package com.codeup.controllers;


import com.codeup.models.Post;
import com.codeup.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PostController {

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
  @GetMapping("/posts/{id}")
  public String postById(@PathVariable long id, Model model) {
    model.addAttribute("post", postDao.getOne(id));
    return "posts/show";
  }

  @GetMapping(value = "/posts/create")
  public String createPost(Model model) {
    model.addAttribute("createForm", new Post());
//    Post post = new Post();
//    post.setTitle("Movie Test2");
//    post.setBody("body of movie test2");
//    postDao.save(post);
    return "posts/create";
  }

  @PostMapping("/posts/create")
  public String createPostResults(@ModelAttribute("createForm") Post post) {
    postDao.save(post);
    return "redirect:/posts";
  }


  @GetMapping("/posts/update/{id}")
  public String UpdatePost(@PathVariable("id") long id, Model model) {
    model.addAttribute("updatePost",postDao.getOne(id));
    return "posts/update";
  }

  @PostMapping("/posts/update/{id}")
  public String UpdatePostResults(@ModelAttribute("updatePost") Post post) {
    postDao.save(post);
    return "redirect:/posts";
  }

  @GetMapping("/posts/delete/{id}")
  public String deletePost(@PathVariable("id") long id) {
    postDao.deleteById(id);
    return "redirect:/posts";
  }

  @GetMapping("/posts/show/{id}")
  public String postHistory(@PathVariable long id, Model model) {
    model.addAttribute("post",postDao.getOne(id));
    return "posts/show";
  }

//  @RequestMapping(value = "posts/create", method = RequestMethod.POST)
//  @ResponseBody
//  public String createPostSent() {
//    return "create a new post";
//  }

//  @PostMapping("/posts/delete")
//  public String deletePost() {
//
//  }
}
