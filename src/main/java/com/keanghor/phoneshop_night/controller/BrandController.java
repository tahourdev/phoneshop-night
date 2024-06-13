package com.keanghor.phoneshop_night.controller;
import com.keanghor.phoneshop_night.dto.BrandDTO;
import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.mapper.BrandMapper;
import com.keanghor.phoneshop_night.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //already built-in response body
@RequestMapping("brands")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok( BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer branId){
        Brand brand = brandService.getById(branId);
        return ResponseEntity.ok( BrandMapper.INSTANCE.toBrandDTO(brand));
    }



    @PutMapping ("{id}")
    public ResponseEntity<?> getUpdate(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
//        Brand brand = Mapper.toBrandDTO(brandDTO);
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand updatedBrand = brandService.update(brandId, brand);
        return ResponseEntity.ok( BrandMapper.INSTANCE.toBrandDTO(updatedBrand));
    }
}
