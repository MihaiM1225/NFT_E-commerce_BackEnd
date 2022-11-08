package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Login;
import com.example.mvcproducts.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Long> {
    Login findByEmailAndPassword(String email, String password);
}
