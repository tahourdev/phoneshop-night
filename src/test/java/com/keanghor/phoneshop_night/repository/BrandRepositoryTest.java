package com.keanghor.phoneshop_night.repository;

import com.keanghor.phoneshop_night.entity.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void testFindByNameLike() {
        //given
        Brand brand1 = new Brand();
        brand1.setName("Apple");
        brandRepository.save(brand1);

        Brand brand2 = new Brand();
        brand2.setName("Samsung");
        brandRepository.save(brand2);

        Brand brand3 = new Brand();
        brand3.setName("Sony");
        brandRepository.save(brand3);

        Brand brand4= new Brand();
        brand4.setName("Ion");
        brandRepository.save(brand4);

        Brand brand5= new Brand();
        brand5.setName("Eon");
        brandRepository.save(brand5);


        //when
        List<Brand> allBrands = brandRepository.findAll();
        System.out.println("All brands in the repository:");
        allBrands.forEach(brand -> System.out.println(brand.getId() + ": " + brand.getName()));
//        System.out.println(allBrands.size());

        List<Brand> brands = brandRepository.findByNameLike("%on%");
        brands.forEach(b -> System.out.println(b.getId()+ ": " + b.getName()));


        //then
        Assertions.assertEquals(3, brands.size());
        Assertions.assertEquals("Sony", brands.get(0).getName());
        Assertions.assertTrue(brands.stream().anyMatch(b -> b.getName().equals("Sony")));
        Assertions.assertEquals(6, brands.get(0).getId());
    }

    @Test
    public void testFindByNameContaining(){
        // given
        Brand brand6 = new Brand();
        brand6.setName("Nokia");
        brandRepository.save(brand6);

        Brand brand7 = new Brand();
        brand7.setName("Oppo");
        brandRepository.save(brand7);

        Brand brand8 = new Brand();
        brand8.setName("OnePlus");
        brandRepository.save(brand8);

        List<Brand> allBrands = brandRepository.findAll();
        System.out.println("All brands in the repository:");
        allBrands.forEach(brand -> System.out.println(brand.getId() + ": " + brand.getName()));

        //when
        List<Brand> brands = brandRepository.findByNameContaining("Pl");

        //then
        Assertions.assertTrue(brands.stream().anyMatch(b -> b.getName().equals("OnePlus")));
        Assertions.assertEquals(1, brands.size());
        Assertions.assertEquals("OnePlus", brands.get(0).getName());
        Assertions.assertEquals(3,  brands.get(0).getId());
    }
}
