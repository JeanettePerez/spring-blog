package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.models.UserWithRoles;
import com.codeup.repositories.Users;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class UserController {

  private Users users;
  private PasswordEncoder passwordEncoder;

  public UserController(Users users, PasswordEncoder passwordEncoder) {
    this.users = users;
    this.passwordEncoder = passwordEncoder;

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
    users.save(user);
    authenticate(user);
    return "redirect:/login";
  }

  private void authenticate(User user) {
    UserDetails userDetails = new UserWithRoles(user, Collections.emptyList());
    Authentication auth = new UsernamePasswordAuthenticationToken(
      userDetails,
      userDetails.getPassword(),
      userDetails.getAuthorities());
    SecurityContext context = SecurityContextHolder.getContext();
    context.setAuthentication(auth);
  }


}
