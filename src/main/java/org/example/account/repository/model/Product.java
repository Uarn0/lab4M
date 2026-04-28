package org.example.account.repository.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * DATA LAYER — Entity для ювелірного виробу.
 * Відповідає за зберігання специфічних характеристик товарів:
 * проба, карати, вага, тип металу.
 */
@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Назва виробу не може бути порожньою")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Ціна є обов'язковою")
    @Positive(message = "Ціна повинна бути більше нуля")
    private Double price;

    private String metalType;
    private Integer purity;
    private Double weightGrams;
    private Double carats;
    private String stoneType;

    @DecimalMin(value = "14.0", message = "Мінімальний розмір каблучки 14")
    @DecimalMax(value = "25.0", message = "Максимальний розмір каблучки 25")
    private Double ringSize;

    @Min(value = 0, message = "Кількість не може бути від'ємною")
    @Column(nullable = false)
    private Integer stockQuantity = 0;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;
}