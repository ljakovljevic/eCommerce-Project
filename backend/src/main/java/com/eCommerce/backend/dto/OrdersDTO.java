package com.eCommerce.backend.dto;

import com.eCommerce.backend.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private ProductDTO product;
    private String size;
    private int quantity;
    private LocalDate orderedAt;

    public OrdersDTO(Order order){
        this.product = new ProductDTO(order.getProduct());
        this.size = order.getSize();
        this.quantity = order.getQuantity();
        this.orderedAt = order.getOrderedAt();
    }
}
