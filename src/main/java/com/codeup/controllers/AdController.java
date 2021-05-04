package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {
  private final AdRepository adsDao;

  public AdController(AdRepository adsDao) {
    this.adsDao = adsDao;
  }

  @GetMapping("/ads")
  @ResponseBody
  public List<Ad> getAllAds() {
    return adsDao.findAll();
  }

}
