package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Product;

import java.util.List;

public interface CartService {
    List<Product> findAll();
    void save(Product product);
}
