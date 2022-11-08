package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Login;

public interface LoginService {
    Login find(String email, String password);
    void save(Login login);
}
