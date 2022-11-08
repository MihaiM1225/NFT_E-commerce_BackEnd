package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.CartService;
import com.example.mvcproducts.services.OrderService;
import com.example.mvcproducts.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@Slf4j
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;

    public CartController(CartService cartService, ProductService productService, OrderService orderService) {
        this.cartService = cartService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping
    public String seeProducts(Model model, Authentication authentication) {
        model.addAttribute("cart", cartService.findAll());
        User user = (User) authentication.getPrincipal();
        log.info(user.getUsername());
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addProduct(@PathVariable("id") String id) {
        List<Product> products = productService.findAll();
        List<Product> orders = cartService.findAll();
        for(int i = 0; i < orders.size(); i++) {
            if(Objects.equals(orders.get(i).getId(), products.get(Integer.parseInt(id)).getId())) {
                return "redirect:/products";
            }
        }
        cartService.save(new Product());
        return "redirect:/products";
    }

    @GetMapping("/checkout")
    public String addOrder() {
        List<Product> products = cartService.findAll();
        ProductOrder order = new ProductOrder();
        for(int i = 0; i < products.size(); i++) {
            Set<Product> items = order.getOrderLineItems();
            Product item = new Product();
            //item.addProduct(products.get(i));
            order.addItem(item);
        }
        orderService.save(order);
        return "redirect:/";
    }
}
