package com.keanghor.phoneshop_night.controller;
import com.keanghor.phoneshop_night.dto.BrandDTO;
import com.keanghor.phoneshop_night.dto.ModelDTO;
import com.keanghor.phoneshop_night.dto.PageDTO;
import com.keanghor.phoneshop_night.entity.Brand;
import com.keanghor.phoneshop_night.entity.Model;
import com.keanghor.phoneshop_night.mapper.BrandMapper;
import com.keanghor.phoneshop_night.mapper.ModelEntityMapper;
import com.keanghor.phoneshop_night.service.BrandService;
import com.keanghor.phoneshop_night.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController //already built-in response body
@RequestMapping("brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;
    private final ModelEntityMapper modelEntityMapper;

    @Operation(
            tags = "Brand Controller",
            description = "Create Brand",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Brands Not Found",
                            responseCode = "404"
                    )
            }
    )
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @Operation(
            tags = "Brand Controller",
            description = "Get One Brand By ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Brand Not Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId){
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @Operation(
            tags = "Brand Controller",
            description = "Update Brand By Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Brand Not Found",
                            responseCode = "404"
                    )
            }
    )
    @PutMapping("{id}")
    public ResponseEntity<?> getUpdateBrand(@PathVariable("id") Long brandId, @RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand brandUpdate = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brandUpdate));
    }

    @Operation(
            tags = "Brand Controller",
            description = "Get Brand by Limitation and Pagination",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Brand Not Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
        Page<Brand> page = brandService.getBrands(params);
//        System.out.println(params);
        PageDTO pageDTO = new PageDTO(page);
        return ResponseEntity.ok(pageDTO);
    }

    @Operation(
            tags = "Brand Controller",
            description = "Get All Models by filtering Brand Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Brands Not Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("{id}/models")
    public ResponseEntity<?> getBrandModelById(@PathVariable("id") Long brandId){
        List<Model> brands = modelService.getByBrandId(brandId);
        List<ModelDTO> list = brands.stream()
                .map(modelEntityMapper::toModelDTO)
                .toList();
        return ResponseEntity.ok(list);
    }

}
