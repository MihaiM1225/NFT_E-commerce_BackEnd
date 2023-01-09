package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.*;
import com.example.mvcproducts.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final HistoryService historyService;
    private final UserService userService;
    private final WalletService walletService;
    private final LoginService loginService;

    public ProductController(ProductService productService, HistoryService historyService, UserService userService, WalletService walletService, LoginService loginService) {
        this.productService = productService;
        this.historyService = historyService;
        this.userService = userService;
        this.walletService = walletService;
        this.loginService = loginService;
    }

    @PostMapping("/getList")
    public String getList() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<ProductBody> productBodyList = new ArrayList<>();
        List<Product> productList = productService.findAll();
        for(Product product : productList) {
            List<History> historyList = historyService.findAllByProductId(product.getId());
            List<HistoryBody> historyBodyList = new ArrayList<>();
            for(History history : historyList) {
                historyBodyList.add(new HistoryBody(history.getOwner(), history.getCurrency(), history.getPrice()));
            }
            productBodyList.add(new ProductBody(product.getId(), product.getName(), product.getCollection(), product.getDescription(), historyBodyList, product.getCurrency(),product.getPrice(), product.getPicture(), product.isVisible()));
        }

        try {
            return mapper.writeValueAsString(productBodyList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "{}";
    }

    @PostMapping("/buy")
    public String buy(@RequestBody PurchaseBody purchaseBody) {
        Product product = productService.findProductById(Long.parseLong(purchaseBody.getId_nft()));
        if(!product.isVisible()) {
            return "{\"errors\": \"Product not available\"}";
        }
        User user = userService.findUserById(Long.parseLong(purchaseBody.getId_user()));
        Wallet wallet = walletService.findByUserId(Long.parseLong(purchaseBody.getId_user()));

        User owner = userService.findUserById(product.getUser().getId());
        Wallet owner_wallet = walletService.findByUserId(product.getUser().getId());

        if(product.getCurrency().equals("ron")) {
            if(product.getPrice() > wallet.getRon()) {
                return "{\"errors\": [\"Insufficient funds\"]}";
            }
            wallet.setRon(wallet.getRon() - product.getPrice());
            owner_wallet.setRon(owner_wallet.getRon() + product.getPrice());
        }
        else if(product.getCurrency().equals("eur")) {
            if(product.getPrice() > wallet.getEur()) {
                return "{\"errors\": [\"Insufficient funds\"]}";
            }
            wallet.setEur(wallet.getEur() - product.getPrice());
            owner_wallet.setEur(owner_wallet.getEur() + product.getPrice());
        }
        else {
            if(product.getPrice() > wallet.getBitcoin()) {
                return "{\"errors\": [\"Insufficient funds\"]}";
            }
            wallet.setBitcoin(wallet.getBitcoin() - product.getPrice());
            owner_wallet.setBitcoin(owner_wallet.getBitcoin() + product.getPrice());
        }

        History new_history = new History();
        new_history.setProduct(product);
        new_history.setOwner(user.getFirstname() + " " + user.getLastname());
        new_history.setCurrency(product.getCurrency());
        new_history.setPrice(product.getPrice());
        historyService.save(new_history);

        product.setUser(user);
        product.setVisible(false);
        productService.save(product);

        List<Product> productList = productService.findAllByUserId(user.getId());
        String nfts = "[";

        for(int i = 0; i < productList.size(); i++) {
            nfts += String.valueOf(productList.get(i).getId());
            if(i != productList.size() - 1) {
                nfts += ",";
            }
        }
        nfts += "]";

        return "{\"errors\": []," +
                "\"wallet\": {\"ron\": \"" + wallet.getRon() + "\", \"eur\": \"" + wallet.getEur() + "\", \"bitcoin\": \"" + wallet.getBitcoin() + "\"}," +
                "\"nfts\": " + nfts + "," +
                "\"id\": \"" + user.getId() + "\"" +
                "}";
    }

    @PostMapping("/sell")
    public String buy(@RequestBody SellBody sellBody) {
        String currency = sellBody.getCurrency();
        double price = sellBody.getPrice();
        User user = userService.findUserById(Long.parseLong(sellBody.getId_user()));
        Product product = productService.findProductById(Long.parseLong(sellBody.getId_nft()));
        if(product.isVisible()) {
            return "{\"errors\": [\"Product is already on sale\"]}";
        }

        product.setPrice(price);
        product.setCurrency(currency);
        product.setVisible(true);
        productService.save(product);

        return "{}";
    }
}
