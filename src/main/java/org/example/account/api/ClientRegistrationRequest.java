package org.example.account.api;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRegistrationRequest {
    @NotBlank(message = "Ім'я є обов'язковим")
    private String firstName;

    @NotBlank(message = "Прізвище є обов'язковим")
    private String lastName;

    @NotBlank(message = "Номер телефону є обов'язковим")
    @Pattern(regexp = "^\\+380\\d{9}$", message = "Формат: +380XXXXXXXXX")
    private String phone;
}