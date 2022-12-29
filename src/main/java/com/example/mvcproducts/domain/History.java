package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class History {

    @Id
    @GeneratedValue
    private Long id;

    private String owner;
    private String currency;
    private double price;

    @ManyToOne
    private Product product;
}
