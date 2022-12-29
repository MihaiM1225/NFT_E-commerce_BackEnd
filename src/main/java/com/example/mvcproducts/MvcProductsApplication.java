package com.example.mvcproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class MvcProductsApplication {

  public static void main(String[] args) {
    SpringApplication.run(MvcProductsApplication.class, args);
  }

}
