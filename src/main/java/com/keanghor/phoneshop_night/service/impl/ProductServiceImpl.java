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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

        updateSalePrice(product);

        productRepository.save(product);
    }

    @Override
    public List<ProductImportHistory> getProductImport() {
        return productImportHistoryRepository.findAll();
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(this::updateSalePrice);
        return products;
    }

    private void updateSalePrice(Product product) {
        List<BigDecimal> allPrices = productImportHistoryRepository.findByProductId(product.getId())
                .stream()
                .map(ProductImportHistory::getPricePerUnit)
                .toList();

        Optional<BigDecimal> maxPriceOptional = allPrices.stream().max(Comparator.naturalOrder());

        if (maxPriceOptional.isPresent()) {
            BigDecimal maxPrice = maxPriceOptional.get();
            BigDecimal salePrice = maxPrice.multiply(BigDecimal.valueOf(1.1));
            product.setSalePrice(salePrice);
        } else {
            throw new ApiException(HttpStatus.BAD_REQUEST, "No import prices found for the product");
        }
    }
}

