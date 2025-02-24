package com.eCommerce.backend.dto;


import com.eCommerce.backend.model.CartItem;
import com.eCommerce.backend.model.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartListDTO {
    private ProductDTO product;
    private String size;
    private int quantity;

    public CartListDTO(CartItem cartItem) {
        this.product = new ProductDTO(cartItem.getProduct());
        this.size = cartItem.getSize();
        this.quantity = cartItem.getQuantity();
    }
}
