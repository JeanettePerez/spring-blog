package com.codeup.controllers;


import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostRepository;
import com.codeup.repositories.UserRepository;
import com.codeup.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class PostController {

  private final PostRepository postDao;
  private final UserRepository userDao;
  private final EmailService emailService;

  public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
    this.postDao = postDao;
    this.userDao = userDao;
    this.emailService = emailService;
  }

  @RequestMapping(value = "/posts", method = RequestMethod.GET)
  public String posts(Model model) {
    model.addAttribute("post",postDao.findAll());
    return "posts/index";
  }

  @GetMapping("/posts/{id}")
  public String postById(@PathVariable long id, Model model) {
    model.addAttribute("post", postDao.getOne(id));
    return "posts/show";
  }

  @GetMapping(value = "/posts/create")
  public String createPost(Model model) {
    model.addAttribute("createPost", new Post());
    return "posts/create";
  }

  @PostMapping("/posts/create")
  public String createPostResults(@ModelAttribute("createPost") Post post) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User creator = userDao.getOne(user.getId());
    post.setUser(creator);
    postDao.save(post);
    emailService.prepareAndSend(post,"Post Creation","Post created successfully");
    return "redirect:/posts";
  }


  @GetMapping("/posts/update/{id}")
  public String UpdatePost(@PathVariable("id") long id, Model model) {
    model.addAttribute("updatePost",postDao.getOne(id));
    return "posts/update";
  }

  @PostMapping("/posts/update/{id}")
  public String UpdatePostResults(@ModelAttribute("updatePost") Post post) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User creator = userDao.getOne(user.getId());
    post.setUser(creator);
    postDao.save(post);
    return "redirect:/profile";
  }

  @GetMapping("/posts/delete/{id}")
  public String deletePost(@PathVariable("id") long id) {
    postDao.deleteById(id);
    return "redirect:/profile";
  }

  @GetMapping("/posts/show/{id}")
  public String showPost(@PathVariable long id, Model model) {
    model.addAttribute("post",postDao.getOne(id));
    return "posts/show";
  }
}
