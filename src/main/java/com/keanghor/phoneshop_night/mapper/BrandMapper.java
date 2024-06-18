package com.keanghor.phoneshop_night.mapper;
import com.keanghor.phoneshop_night.dto.BrandDTO;
import com.keanghor.phoneshop_night.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    // convert dto to brand
    Brand toBrand(BrandDTO brandDTO);

    // convert brand to dto
    BrandDTO toBrandDTO(Brand entity);
}
