package com.codeup.controllers;

import com.codeup.repositories.DogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DogController {
  private final DogRepository dogDao;


  public DogController(DogRepository dogDao) {
    this.dogDao = dogDao;
  }

  @GetMapping ("/dogs")
  public String dogTest(Model model){
    model.addAttribute("dogs",dogDao.findAll());
    return "doggos";
  }

  @GetMapping("/dogs/{id}")
  public String dogByID(@PathVariable long id, Model model) {
    model.addAttribute("dogs",dogDao.getOne(id));
    return "doggos";
  }
}
