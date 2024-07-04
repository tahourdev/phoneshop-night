package com.keanghor.phoneshop_night.service;

import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.exception.ResourceNotFoundException;
import com.keanghor.phoneshop_night.repository.BrandRepository;
import com.keanghor.phoneshop_night.service.impl.BrandServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    private BrandService brandService;

    @BeforeEach
    public void setup() {
        brandService = new BrandServiceImpl(brandRepository);
    }

    @Test
    public void testCreate() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");

        // when
        brandService.create(brand);

        // then
        verify(brandRepository, times(1)).save(brand);
    }

    @Test
    public void testGetById() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");
        brand.setId(1L);

        // when
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        Brand brandReturn = brandService.getById(1L);

        // then
        Assertions.assertEquals(1, brandReturn.getId());
        Assertions.assertEquals("Apple", brandReturn.getName());
    }

    @Test
    public void testGetByIdThrow() {
        // given
        when(brandRepository.findById(2L)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> brandService.getById(2L))
                .isInstanceOf(ResourceNotFoundException.class)
//                .hasMessage("Brand With id = 2 not found");
                .hasMessage(String.format("%s With id = %d not found", "Brand", 2));
//                .hasMessageEndingWith("not found");
    }
}
