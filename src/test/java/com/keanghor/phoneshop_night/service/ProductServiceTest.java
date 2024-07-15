package com.keanghor.phoneshop_night.service;

import com.keanghor.phoneshop_night.entity.Product;
import com.keanghor.phoneshop_night.exception.ResourceNotFoundException;
import com.keanghor.phoneshop_night.repository.ProductRepository;
import com.keanghor.phoneshop_night.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        productService = new ProductServiceImpl(productRepository, null, null);
    }

    @Test
    public void testSetSalePrice() {
        // given
        Product product = new Product();
        product.setId(1L);
        product.setSalePrice(BigDecimal.ZERO);

        // when
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        BigDecimal newPrice = BigDecimal.valueOf(199.99);
        productService.setSalePrice(1L, newPrice);

        // then
        verify(productRepository, times(1)).save(product);
        Assertions.assertEquals(newPrice, product.getSalePrice());

        System.out.println(newPrice);
    }

    @Test
    public void testSetSalePriceThrow() {
        // given
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.setSalePrice(2L, BigDecimal.valueOf(199.99)))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(String.format("Product With id = %d not found", 2));
    }
}
