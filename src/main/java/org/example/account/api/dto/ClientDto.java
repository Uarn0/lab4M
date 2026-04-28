package org.example.account.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;

    private String phone;

    private Integer bonusPoints;
}