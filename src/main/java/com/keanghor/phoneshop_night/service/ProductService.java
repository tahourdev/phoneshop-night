package com.keanghor.phoneshop_night.service;

import com.keanghor.phoneshop_night.dto.ProductImportDTO;
import com.keanghor.phoneshop_night.entity.Product;
import com.keanghor.phoneshop_night.entity.ProductImportHistory;

import java.util.List;

public interface ProductService {
    List<ProductImportHistory> getProductImport();
    List<Product> getProducts();
    Product create(Product product);
    Product getById(Long id);
    void importProduct(ProductImportDTO productImportDTO);
}
