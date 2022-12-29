package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void saveAll(Iterable<Product> products) {
    productRepository.saveAll(products);
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public List<Product> findAllByUserId(Long id) {
    return productRepository.findAllByUser_Id(id);
  }

  @Override
  public void save(Product product) {
    productRepository.save(product);
  }

  @Override
  public Product findProductById(Long id) {
    return productRepository.findProductById(id);
  }
}
