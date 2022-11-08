package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;
    private Cart cart = new Cart();

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Product> findAll() {
        return cart.getProducts();
    }

    @Override
    public void save(Product product) {
        cart.addProduct(product);
    }
}
