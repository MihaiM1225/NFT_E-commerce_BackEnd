package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String collection;
  private String description;
  private double price;

  @Lob
  private String picture;

  @ManyToOne
  private User user;

  public Product(String name, String collection, String description, double price) {
    this.name = name;
    this.description = description;
    this.collection = collection;
    this.price = price;
  }


}
