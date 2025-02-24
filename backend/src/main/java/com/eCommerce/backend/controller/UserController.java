package com.eCommerce.backend.controller;

import com.eCommerce.backend.dto.CartItemDTO;
import com.eCommerce.backend.dto.CartItemResponse;
import com.eCommerce.backend.dto.CartListDTO;
import com.eCommerce.backend.model.CartItem;
import com.eCommerce.backend.model.Order;
import com.eCommerce.backend.model.User;
import com.eCommerce.backend.model.UserPrincipal;
import com.eCommerce.backend.repository.CartItemRepo;
import com.eCommerce.backend.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        return userService.verify(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/cart")
    public CartItemResponse addToCart(@RequestBody CartItemDTO dto,
                                      @AuthenticationPrincipal UserPrincipal userPrincipal){
        return userService.addToCart(dto, userPrincipal);
    }

    @GetMapping("/cart")
    public List<CartListDTO> getCartItems(@AuthenticationPrincipal UserPrincipal userPrincipal) throws JsonProcessingException {
        List<CartItem> cartItems = userService.getCartItems(userPrincipal.getUserId());
        return cartItems.stream().map(CartListDTO::new).collect(Collectors.toList());
    }

    @PatchMapping("/cart/update")
    public ResponseEntity<?> updateCartItemQuantity(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @RequestBody CartItemDTO cartItemDTO) {
       return userService.updateCartItemQuantity(userPrincipal.getUserId(), cartItemDTO);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return userService.placeOrder(userPrincipal.getUserId());
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return userService.getOrders(userPrincipal.getUserId());
    }

}
