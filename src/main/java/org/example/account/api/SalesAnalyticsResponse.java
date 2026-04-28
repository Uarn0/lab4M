package org.example.account.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesAnalyticsResponse {
    private LocalDate date;
    private Long totalOrders;
    private Double totalRevenue;
    private Double averageOrderValue;
}