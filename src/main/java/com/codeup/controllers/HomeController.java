package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  @GetMapping("/")
  public String landing() {
    return "roll-dice";
  }

  @GetMapping("/home")
  public String welcome() {
    return "home"; // this is how it refers to the view (html file)
  }


}
