package com.codeup;


import javax.persistence.*;

@Entity
//@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String body;


  public Post(long id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  public Post(String title, String body) {
    this.title = title;
    this.body = body;
  }

  public Post() {

  }

  public String getTitle() {
    return title;
  }
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}