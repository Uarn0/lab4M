package org.example.account.repository;
import org.example.account.repository.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(:stoneType IS NULL OR p.stoneType = :stoneType) AND " +
            "(:ringSize IS NULL OR p.ringSize = :ringSize) AND " +
            "p.stockQuantity > 0")
    List<Product> findByFilters(
            @Param("stoneType") String stoneType,
            @Param("ringSize") Double ringSize
    );

}