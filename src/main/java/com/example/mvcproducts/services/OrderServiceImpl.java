package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void saveAll(Iterable<ProductOrder> orders) {
    orderRepository.saveAll(orders);
  }

  @Override
  public List<ProductOrder> findAll() {
    return orderRepository.findAll();
  }

  @Override
  public void save(ProductOrder order) {
    orderRepository.save(order);
  }
}
