package com.keanghor.phoneshop_night.repository;

import com.keanghor.phoneshop_night.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Long> {
    // Custom method to find import histories by product ID
    List<ProductImportHistory> findByProductId(Long productId);
}
