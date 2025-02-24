package com.eCommerce.backend.controller;

import com.eCommerce.backend.dto.CartItemDTO;
import com.eCommerce.backend.model.CartItem;
import com.eCommerce.backend.model.Product;
import com.eCommerce.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

}
