package com.codeup.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class PostImage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 100)
  private String imageTitle;

  @Column(nullable = false, length = 1000)
  private String url;

  @ManyToOne
  @JsonBackReference
  private Post post;


}
