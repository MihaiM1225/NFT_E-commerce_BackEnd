package com.example.mvcproducts.domain;

import lombok.Data;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ProductOrder {
  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  private Set<Product> orderLineItems = new HashSet<>();

  public void addItem(Product item) {
    this.orderLineItems.add(item);
  }

}
