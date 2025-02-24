package com.eCommerce.backend.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private Long productId;
    private String size;
    private int quantity;

}
