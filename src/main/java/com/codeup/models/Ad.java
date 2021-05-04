package com.codeup.models;

import javax.persistence.*;

@Entity
@Table(name="ads")
public class Ad {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String description;

  @Column(nullable = false)
  private int priceInCent;

  public Ad() {
  }

  public Ad(long id, String title, String description, int priceInCent) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.priceInCent = priceInCent;
  }

  public Ad(String title, String description, int priceInCent) {
    this.title = title;
    this.description = description;
    this.priceInCent = priceInCent;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPriceInCent() {
    return priceInCent;
  }

  public void setPriceInCent(int priceInCent) {
    this.priceInCent = priceInCent;
  }
}
