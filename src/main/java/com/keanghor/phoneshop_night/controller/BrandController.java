package com.keanghor.phoneshop_night.controller;
import com.keanghor.phoneshop_night.dto.BrandDTO;
import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.service.BrandService;
import com.keanghor.phoneshop_night.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //already built-in response body
@RequestMapping("brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand = Mapper.toBrandDTO(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(Mapper.toBrandDTO(brand));
    }
}
