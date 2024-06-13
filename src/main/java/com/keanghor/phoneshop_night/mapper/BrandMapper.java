package com.keanghor.phoneshop_night.mapper;
import com.keanghor.phoneshop_night.dto.BrandDTO;
import com.keanghor.phoneshop_night.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toBrand(BrandDTO brandDTO);

    BrandDTO toBrandDTO(Brand entity);
}
