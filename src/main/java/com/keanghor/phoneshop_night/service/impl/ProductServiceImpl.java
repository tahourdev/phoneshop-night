package com.keanghor.phoneshop_night.service.impl;
import com.keanghor.phoneshop_night.dto.ProductImportDTO;
import com.keanghor.phoneshop_night.entity.Product;
import com.keanghor.phoneshop_night.entity.ProductImportHistory;
import com.keanghor.phoneshop_night.exception.ApiException;
import com.keanghor.phoneshop_night.exception.ResourceNotFoundException;
import com.keanghor.phoneshop_night.mapper.ProductMapper;
import com.keanghor.phoneshop_night.repository.ProductImportHistoryRepository;
import com.keanghor.phoneshop_night.repository.ProductRepository;
import com.keanghor.phoneshop_night.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", id)
        );
    }

    @Override
    public void importProduct(ProductImportDTO productImportDTO) {
        if (productImportDTO.getImportUnit() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Import unit can't be null");
        }

        Product product = getById(productImportDTO.getProductId());
        Integer availableUnit = product.getAvailableUnit() != null ? product.getAvailableUnit() : 0;
        product.setAvailableUnit(availableUnit + productImportDTO.getImportUnit());

        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(productImportDTO, product);
        productImportHistory.setDateImport(productImportDTO.getImportDate()); // Set LocalDateTime
        productImportHistoryRepository.save(productImportHistory);

        productRepository.save(product);
    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(0.0001)) < 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Sale price must be greater than 0.0001");
        }
        Product product = getById(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public void validateStock(long productId, Integer numberOfUnit) {

    }

    @Override
    public List<ProductImportHistory> getProductImport() {
        return productImportHistoryRepository.findAll();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }


}

