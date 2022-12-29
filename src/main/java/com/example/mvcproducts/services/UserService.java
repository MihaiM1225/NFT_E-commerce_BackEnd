package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.User;

public interface UserService {
  void save(User user);
  User findByLoginId(Long id);
  User findUserById(Long id);
}
