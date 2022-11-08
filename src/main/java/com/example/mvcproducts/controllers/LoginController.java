package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Login;
import com.example.mvcproducts.domain.LoginBody;
import com.example.mvcproducts.repositories.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    @GetMapping
    public String login(@RequestBody LoginBody loginBody) {
        Login login = loginRepository.findByEmailAndPassword(loginBody.email, loginBody.password);
        if(login == null) {
            return "{}";
        }
        return login.toString();
    }
}
