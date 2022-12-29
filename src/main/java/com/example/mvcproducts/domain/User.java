package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private Long id;

  private String firstname;
  private String lastname;
  private String phone;
  private String date;

  @OneToOne(mappedBy = "user")
  private Login login;

  @OneToOne(mappedBy = "user")
  private Wallet wallet;

  @OneToMany(mappedBy = "user")
  private Set<Product> productSet = new HashSet<>();

}
