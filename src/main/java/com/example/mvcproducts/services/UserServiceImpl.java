package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public User findByLoginId(Long id) {
    return userRepository.findByLoginId(id);
  }

  @Override
  public User findUserById(Long id) {
    return userRepository.findUserById(id);
  }
}
