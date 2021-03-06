package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostRepository;
import com.codeup.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private UserRepository userDao;
  private PasswordEncoder passwordEncoder;
  private PostRepository postDao;

  public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, PostRepository postDao) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
    this.postDao = postDao;
  }

  @GetMapping("/sign-up")
  public String showSignupForm(Model model) {
    model.addAttribute("user",new User());
    return "users/sign-up";
  }

  @PostMapping("/sign-up")
  public String saveUser(@ModelAttribute User user) {
    String hash = passwordEncoder.encode(user.getPassword());
    user.setPassword(hash);
    userDao.save(user);
    return "redirect:/login";
  }

  @GetMapping("/profile")
  public String userProfile(Model model) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("post",postDao.findAllByUser(user));
    return "users/profile";
  }


}
