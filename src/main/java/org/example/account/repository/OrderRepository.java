package org.example.account.repository;


import org.example.account.repository.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByClientId(Long clientId);

    @Query("SELECT COUNT(o), SUM(o.finalAmount) FROM Order o " +
            "WHERE o.createdAt BETWEEN :from AND :to AND o.status = 'COMPLETED'")
    Object[] getSalesAnalytics(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );

    @Query("SELECT o FROM Order o WHERE o.createdAt BETWEEN :from AND :to")
    List<Order> findByDateRange(
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}