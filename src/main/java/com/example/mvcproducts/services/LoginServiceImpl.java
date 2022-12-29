package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Login;
import com.example.mvcproducts.repositories.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login findByEmailAndPassword(String email, String password) {
        return loginRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Login findByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    @Override
    public void save(Login login) {
        loginRepository.save(login);
    }
}
