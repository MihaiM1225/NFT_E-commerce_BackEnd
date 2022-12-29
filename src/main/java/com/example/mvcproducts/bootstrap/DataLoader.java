package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.*;
import com.example.mvcproducts.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  private final ProductService productService;
  private final LoginService loginService;
  private final UserService userService;
  private final HistoryService historyService;
  private final WalletService walletService;

  public DataLoader(ProductService productService, LoginService loginService, UserService userService, HistoryService historyService, WalletService walletService) {
    this.productService = productService;
    this.loginService = loginService;
    this.userService = userService;
    this.historyService = historyService;
    this.walletService = walletService;
  }

  @Override
  public void run(String... args) throws Exception {

    User user1 = new User();
    User user2 = new User();
    user2.setFirstname("Mihai2");
    user2.setLastname("Alo");
    userService.save(user1);
    userService.save(user2);

    Wallet wallet1 = new Wallet();
    wallet1.setEur(100);
    wallet1.setUser(user1);
    Wallet wallet2 = new Wallet();
    wallet2.setEur(200);
    wallet2.setUser(user2);
    walletService.save(wallet1);
    walletService.save(wallet2);

    Product product1 = new Product();
    product1.setUser(user1);
    product1.setPrice(50);
    product1.setCurrency("eur");
    productService.save(product1);

    History history = new History();
    history.setCurrency("ron");
    history.setPrice(10);
    history.setOwner("Mihai");
    history.setProduct(product1);

    historyService.save(history);

    /*
    Login login = new Login();
    login.setEmail("a");
    login.setPassword("b");
    loginService.save(login);

    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1=new User("user1",bcrypt.encode("user1"));
    user1.getRoles().add(Role.ROLE_USER);
    User user2=new User("user2",bcrypt.encode("user2"));
    user2.getRoles().add(Role.ROLE_ADMIN);
    userService.save(user1);
    userService.save(user2);*/
  }
}
