package com.codeup.models;


import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String body;

  @ManyToOne
  private User user;

  public Post(long id, String title, String body, User user) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.user = user;
  }

  public Post(String title, String body, User user) {
    this.title = title;
    this.body = body;
    this.user = user;
  }

  public Post() {
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
