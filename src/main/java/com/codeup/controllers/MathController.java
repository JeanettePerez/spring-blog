package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

  @RequestMapping(value = "/add/{numOne}/and/{numTwo}", method = RequestMethod.GET)
  @ResponseBody
  public String add(@PathVariable int numOne, @PathVariable int numTwo){
    return numOne + " plus " + numTwo + " = " + (numOne + numTwo);
  }

  @RequestMapping(value = "/subtract/{numOne}/from/{numTwo}", method = RequestMethod.GET)
  @ResponseBody
  public String subtract(@PathVariable int numOne, @PathVariable int numTwo) {
    return "Subtracting " + numOne + " from " + numTwo + " = " + (numTwo - numOne);
  }

  @RequestMapping(value = "/multiply/{numOne}/and/{numTwo}", method = RequestMethod.GET)
  @ResponseBody
  public String multiply(@PathVariable int numOne, @PathVariable int numTwo) {
    return numOne + " times " + numTwo + " = " + (numOne * numTwo);
  }

  @RequestMapping(value = "/divide/{numOne}/by/{numTwo}", method = RequestMethod.GET)
  @ResponseBody
  public String divide(@PathVariable int numOne, @PathVariable int numTwo) {
    return numOne + " divided by " + numTwo + " = " + (numOne / numTwo);
  }
}
