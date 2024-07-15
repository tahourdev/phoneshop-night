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
    // This defines a mapping rule for converting ModelDTO to Model. It maps the brandId field of ModelDTO to the brand field of Model.
    Model toModel(ModelDTO modelDTO);

    //Convert Model to DTO
    @Mapping(target = "brandId", source = "brand.id")
    // This defines a mapping rule for converting Model to ModelDTO. It maps the id field of the brand object in Model to the brandId field of ModelDTO.
    ModelDTO toModelDTO(Model entity);
}
