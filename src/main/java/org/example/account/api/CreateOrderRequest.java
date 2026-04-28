package org.example.account.api;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private Long clientId;

    @NotEmpty(message = "Замовлення має містити хоча б один товар")
    private List<OrderItemRequest> items;

    private Double bonusesToUse = 0.0;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemRequest {
        @NotNull(message = "ID товару є обов'язковим")
        private Long productId;

        @NotNull(message = "Кількість є обов'язковою")
        @Min(value = 1, message = "Кількість має бути не менше 1")
        private Integer quantity;
    }
}