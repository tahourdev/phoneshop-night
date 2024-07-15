package com.keanghor.phoneshop_night.controller;
import com.keanghor.phoneshop_night.dto.PriceDTO;
import com.keanghor.phoneshop_night.dto.ProductDTO;
import com.keanghor.phoneshop_night.dto.ProductImportDTO;
import com.keanghor.phoneshop_night.entity.Product;
import com.keanghor.phoneshop_night.entity.ProductImportHistory;
import com.keanghor.phoneshop_night.mapper.BrandMapper;
import com.keanghor.phoneshop_night.mapper.ModelEntityMapper;
import com.keanghor.phoneshop_night.mapper.ProductMapper;
import com.keanghor.phoneshop_night.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //already built-in response body
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @Operation(
            tags = "Product Controller",
            description = "Create Product",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Products Not Found",
                            responseCode = "404"
                    )
            }
    )
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
        Product product = productMapper.toProduct(productDTO);
        product = productService.create(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("histories")
    public ResponseEntity<?> getProductsHistory(){
        List<ProductImportHistory> productImport = productService.getProductImport();
        return ResponseEntity.ok(productImport);
    }

    @PostMapping("importProduct")
    public ResponseEntity<?> importProduct(@RequestBody @Valid ProductImportDTO productImportDTO){
        productService.importProduct(productImportDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{productId}/setSalePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable Long productId, @Valid @RequestBody PriceDTO priceDTO){
        productService.setSalePrice(productId, priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }

}
