package com.keanghor.phoneshop_night.service;

import com.keanghor.phoneshop_night.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand create(Brand brand);
    Brand getById(Integer id);
    Brand update(Integer id, Brand brandUpdate);
    Page<Brand> getBrands(Map<String, String> params);


    //    List<Brand> getBrands();
    //    List<Brand> getBrands(String name);
    //    List<Brand> getBrands(Map<String, String> params);

}
