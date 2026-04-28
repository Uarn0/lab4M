package org.example.account.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CartItemDto {
    private Long clientId;
    private Long productId;
    private Integer quantity;
}