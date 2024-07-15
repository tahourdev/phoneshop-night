package com.keanghor.phoneshop_night.service.impl;
import com.keanghor.phoneshop_night.dto.ProductSoldDTO;
import com.keanghor.phoneshop_night.dto.SaleDTO;
import com.keanghor.phoneshop_night.entity.Product;
import com.keanghor.phoneshop_night.entity.Sale;
import com.keanghor.phoneshop_night.entity.SaleDetail;
import com.keanghor.phoneshop_night.exception.ApiException;
import com.keanghor.phoneshop_night.repository.ProductRepository;
import com.keanghor.phoneshop_night.repository.SaleDetailRepository;
import com.keanghor.phoneshop_night.repository.SaleRepository;
import com.keanghor.phoneshop_night.service.ProductService;
import com.keanghor.phoneshop_night.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    @Override
    public void sell(SaleDTO saleDTO) {
        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId).toList();

        // validate product
        productIds.forEach(productService::getById);

        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        // validate stock
        saleDTO.getProducts()
                .forEach(ps -> {
                    Product product =  productMap.get(ps.getProductId());
                    if(product.getAvailableUnit() < ps.getNumberOfUnit()){
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
                    }
                });

        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        //Sale Detail
        saleDTO.getProducts().forEach(ps -> {
            Product product =  productMap.get(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);
        });

    }

    private void saveSale(SaleDTO saleDTO){
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        //Sale Detail
        saleDTO.getProducts().forEach(ps -> {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(null);
        });
    }

    private void validate(SaleDTO saleDTO){
        saleDTO.getProducts().forEach(ps -> {
           Product product =  productService.getById(ps.getProductId());
            if(product.getAvailableUnit() < ps.getNumberOfUnit()){
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
            }
        });
    }

//    private void validate2(SaleDTO saleDTO){
//
//
//      List<Long> productIds = saleDTO.getProducts().stream()
//                .map(ProductSoldDTO::getProductId).toList();
//
//        // validate product
//        productIds.forEach(productService::getById);
//
//        List<Product> products = productRepository.findAllById(productIds);
//        Map<Long, Product> productMap = products.stream()
//                .collect(Collectors.toMap(Product::getId, Function.identity()));
//
//        // validate stock
//        saleDTO.getProducts()
//                .forEach(ps -> {
//                   Product product =  productMap.get(ps.getProductId());
//                   if(product.getAvailableUnit() < ps.getNumberOfUnit()){
//                       throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
//                   }
//                });
//
//    }
}
