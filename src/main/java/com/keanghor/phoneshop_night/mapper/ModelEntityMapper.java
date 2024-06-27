package com.keanghor.phoneshop_night.mapper;

import com.keanghor.phoneshop_night.dto.ModelDTO;
import com.keanghor.phoneshop_night.entity.Model;
import com.keanghor.phoneshop_night.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelEntityMapper {
    ModelEntityMapper INSTANCT = Mappers.getMapper(ModelEntityMapper.class);

    //Convert DTO to Model
    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDTO modelDTO);

    //Convert Model to DTO
    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO toModelDTO(Model entity);
}
