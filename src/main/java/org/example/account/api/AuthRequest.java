package org.example.account.api;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Логін є обов'язковим")
    private String username;

    @NotBlank(message = "Пароль є обов'язковим")
    private String password;
}