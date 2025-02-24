package com.eCommerce.backend.service;

import com.eCommerce.backend.dto.CartItemDTO;
import com.eCommerce.backend.dto.CartItemResponse;
import com.eCommerce.backend.dto.CartListDTO;
import com.eCommerce.backend.dto.OrdersDTO;
import com.eCommerce.backend.model.*;
import com.eCommerce.backend.repository.CartItemRepo;
import com.eCommerce.backend.repository.OrderRepo;
import com.eCommerce.backend.repository.ProductRepo;
import com.eCommerce.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(User user){
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "Fail";
    }

    public CartItemResponse addToCart(CartItemDTO dto, UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getUserId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setSize(dto.getSize());

        Optional<CartItem> optional = cartItemRepo.findByUser_UserIdAndProduct_ProductIdAndSize(user.getUserId(), product.getProductId(), dto.getSize());
        if (optional.isPresent()){
            CartItem existingItem = optional.get();
            existingItem.setQuantity(existingItem.getQuantity()+ 1);
            cartItemRepo.save(existingItem);

            return new CartItemResponse(existingItem);
        }
        cartItemRepo.save(cartItem);

        CartItemResponse response = new CartItemResponse(
                user.getUsername(),product.getName(), dto.getSize(),dto.getQuantity());
        return response;
    }

    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepo.findByUser_UserId(userId);
    }

    @Transactional
    public ResponseEntity<?> updateCartItemQuantity(Long userId, CartItemDTO cartItemDTO) {
        Optional<CartItem> optionalCartItem = cartItemRepo.findByUser_UserIdAndProduct_ProductIdAndSize(
                userId, cartItemDTO.getProductId(), cartItemDTO.getSize());
        if (optionalCartItem.isPresent()){
            CartItem cartItem = optionalCartItem.get();
            if (cartItemDTO.getQuantity() > 0){
                cartItem.setQuantity(cartItemDTO.getQuantity());
                cartItemRepo.save(cartItem);
                return ResponseEntity.ok(new CartListDTO(cartItem));
            }
            else  {
                System.out.println("Delete attempt");
                User user = cartItem.getUser();
                user.getCartItems().remove(cartItem);
                cartItemRepo.delete(cartItem);

                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<?> placeOrder(Long userId) {
        Optional<List<CartItem>> optionalList = Optional.ofNullable(cartItemRepo.findByUser_UserId(userId));
        if (optionalList.isPresent()){
            List<CartItem> cartItemList = optionalList.get();
            for(CartItem item : cartItemList){
                orderRepo.save(new Order(item));
            }
            cartItemRepo.deleteByUser_UserId(userId);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> getOrders(Long userId) {
        List<Order> order = orderRepo.findByUser_UserId(userId);
        List<OrdersDTO> orders = order.stream().map(OrdersDTO::new).toList();
        return ResponseEntity.ok(orders);
    }
}
