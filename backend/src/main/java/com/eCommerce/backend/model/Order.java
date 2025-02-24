package com.eCommerce.backend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;
    private int quantity;
    private String size;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd MMMM yyyy")
    private LocalDate orderedAt;

    @PrePersist
    public void onCreate(){
        this.orderedAt = LocalDate.now();
    }

    public Order(CartItem cartItem){
        this.user = cartItem.getUser();
        this.product = cartItem.getProduct();
        this.quantity = cartItem.getQuantity();
        this.size = cartItem.getSize();
    }

}
