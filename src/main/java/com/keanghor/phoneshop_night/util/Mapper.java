package com.keanghor.phoneshop_night.util;

import com.keanghor.phoneshop_night.dto.BrandDTO;
import com.keanghor.phoneshop_night.entity.Brand;

public class Mapper {

    //convert dto to entity
    public static Brand toBrandDTO(BrandDTO dto){
        Brand brand = new Brand();
//        brand.setId(dto.getId());
        brand.setName(dto.getName());
        return brand;
    }

    // convert entity to dto
    public static BrandDTO toBrandDTO(Brand brand){
        BrandDTO brandDTO1 = new BrandDTO();
        brandDTO1.setName(brand.getName());
        return brandDTO1;
    }
}
