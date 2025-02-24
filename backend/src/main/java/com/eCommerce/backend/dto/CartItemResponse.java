package com.eCommerce.backend.dto;

import com.eCommerce.backend.model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {

    private String username;
    private String productName;
    private String size;
    private int quantity;

    public CartItemResponse(CartItem cartItem){
        this.username = cartItem.getUser().getUsername();
        this.productName = cartItem.getProduct().getName();
        this.size = cartItem.getSize();
        this.quantity = cartItem.getQuantity();
    }
}
