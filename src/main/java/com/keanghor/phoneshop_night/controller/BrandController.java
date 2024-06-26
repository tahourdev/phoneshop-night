package com.keanghor.phoneshop_night.controller;
import com.keanghor.phoneshop_night.dto.BrandDTO;
import com.keanghor.phoneshop_night.dto.PageDTO;
import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.mapper.BrandMapper;
import com.keanghor.phoneshop_night.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController //already built-in response body
@RequestMapping("brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId){
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> getUpdateBrand(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand brandUpdate = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brandUpdate));
    }

/*
    @GetMapping
    public ResponseEntity<?> getBrands(){
        List<BrandDTO> list = brandService.getBrands()
                .stream()
                .map(brand -> BrandMapper.INSTANCE.toBrandDTO(brand))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
    */

    @GetMapping
    public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
        Page<Brand> page = brandService.getBrands(params);
//        System.out.println(params);
        PageDTO pageDTO = new PageDTO(page);
        return ResponseEntity.ok(pageDTO);
    }

}
