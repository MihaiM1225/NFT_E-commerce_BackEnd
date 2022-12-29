package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.*;
import com.example.mvcproducts.services.LoginService;
import com.example.mvcproducts.services.ProductService;
import com.example.mvcproducts.services.UserService;
import com.example.mvcproducts.services.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    private final LoginService loginService;
    private final UserService userService;
    private final WalletService walletService;
    private final ProductService productService;
    PasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public AccountController(LoginService loginRepository, UserService userService, WalletService walletService, ProductService productService){
        this.loginService = loginRepository;
        this.userService = userService;
        this.walletService = walletService;
        this.productService = productService;
    }

    @GetMapping("/login")
    public String login(@RequestBody LoginBody loginBody) {
        Login login = loginService.findByEmail(loginBody.email);
        if(login == null || !bcrypt.matches(loginBody.password, login.getPassword())) {
            return "{" +
                    "'errors': ['Wrong username or password']," +
                    "'wallet': {'ron': '', 'eur': '', 'bitcoin': ''}," +
                    "'firstname': ''," +
                    "'lastname': ''," +
                    "'date': ''," +
                    "'nfts': []," +
                    "'email': ''," +
                    "'phone': ''," +
                    "'id': ''" +
                    "}";
        }
        User user = userService.findByLoginId(login.getId());
        Wallet wallet = walletService.findByUserId(user.getId());
        List<Product> productList = productService.findAllByUserId(user.getId());
        String nfts = "[";

        for(int i = 0; i < productList.size(); i++) {
            nfts += String.valueOf(productList.get(i).getId());
            if(i != productList.size() - 1) {
                nfts += ",";
            }
        }
        nfts += "]";

        return "{'errors': []," +
                "'wallet': {'ron': '" + wallet.getRon() + "', 'eur': '" + wallet.getEur() + "', 'bitcoin': '" + wallet.getBitcoin() + "'}," +
                "'firstname': '" + user.getFirstname() + "'," +
                "'lastname': '" + user.getLastname() + "'," +
                "'date': '" + user.getDate() + "'," +
                "'nfts': '" + nfts + "'," +
                "'email': '" + login.getEmail() + "'," +
                "'phone': '" + user.getPhone() + "'," +
                "'id': '" + user.getId() + "'" +
                "}";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterBody registerBody) {
        Login login = loginService.findByEmail(registerBody.email);
        if(login != null) {
            String aux = "['Email already exists'";

            if(registerBody.password.length() < 8) {
                aux += ",'Password too short']";
            }
            else {
                aux += "]";
            }

            return "{'errors':" + aux + "}";
        }

        User newUser = new User();
        newUser.setFirstname(registerBody.firstname);
        newUser.setLastname(registerBody.lastname);
        newUser.setPhone(registerBody.phone);
        newUser.setDate(String.valueOf(java.time.LocalDate.now()));

        Login newLogin = new Login();
        newLogin.setPassword(bcrypt.encode(registerBody.password));
        newLogin.setEmail(registerBody.email);
        newLogin.setUser(newUser);

        Wallet newWallet = new Wallet();
        newWallet.setBitcoin(0);
        newWallet.setEur(0);
        newWallet.setRon(0);
        newWallet.setUser(newUser);

        userService.save(newUser);
        loginService.save(newLogin);
        walletService.save(newWallet);

        return "{}";
    }

}
