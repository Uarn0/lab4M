package org.example.account.api.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long clientId;
    private Long sellerId;
    private Double amount;
    private String status;
    private String cardNumber;
    private String cvv;
}