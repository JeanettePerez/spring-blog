package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

  @GetMapping("/add/{numOne}/and/{numTwo}")
  @ResponseBody
  public String add(@PathVariable int numOne, @PathVariable int numTwo){
    return numOne + " plus " + numTwo + " = " + (numOne + numTwo);
  }

  @GetMapping(value = "/subtract/{numOne}/from/{numTwo}")
  @ResponseBody
  public String subtract(@PathVariable int numOne, @PathVariable int numTwo) {
    return "Subtracting " + numOne + " from " + numTwo + " = " + (numTwo - numOne);
  }

  @GetMapping(value = "/multiply/{numOne}/and/{numTwo}")
  @ResponseBody
  public String multiply(@PathVariable int numOne, @PathVariable int numTwo) {
    return numOne + " times " + numTwo + " = " + (numOne * numTwo);
  }

  @GetMapping(value = "/divide/{numOne}/by/{numTwo}")
  @ResponseBody
  public String divide(@PathVariable int numOne, @PathVariable int numTwo) {
    return numOne + " divided by " + numTwo + " = " + (numOne / numTwo);
  }
}
