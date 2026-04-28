package org.example.account.repository.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ім'я є обов'язковим")
    private String firstName;

    @NotBlank(message = "Прізвище є обов'язковим")
    private String lastName;

    @NotBlank(message = "Номер телефону є обов'язковим")
    @Pattern(regexp = "^\\+380\\d{9}$", message = "Номер телефону має формат +380XXXXXXXXX")
    @Column(unique = true, nullable = false)
    private String phone;

    @Min(value = 0)
    private Double bonusBalance = 0.0;

    @Min(value = 0)
    private Integer totalOrders = 0;
}