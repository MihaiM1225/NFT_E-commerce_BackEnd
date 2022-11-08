package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<ProductOrder,Long> {
    List<ProductOrder> findAll();
}
