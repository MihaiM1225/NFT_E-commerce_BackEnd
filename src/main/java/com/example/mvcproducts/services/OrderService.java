package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductOrder;

import java.util.List;

public interface OrderService {
    void saveAll(Iterable<ProductOrder> orders);
    List<ProductOrder> findAll();
    void save(ProductOrder order);
}
