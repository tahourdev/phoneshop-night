package com.keanghor.phoneshop_night.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.repository.BrandRepository;
import com.keanghor.phoneshop_night.service.BrandService;

@Service //Business layer
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }
    
}
