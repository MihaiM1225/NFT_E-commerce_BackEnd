package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Login;

public interface LoginService {
    Login findByEmailAndPassword(String email, String password);
    Login findByEmail(String email);
    void save(Login login);
}
