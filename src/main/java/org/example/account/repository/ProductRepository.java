package org.example.account.repository;
import org.example.account.repository.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByStoneTypeAndPriceLessThanEqual(String stoneType, Double maxPrice);
}