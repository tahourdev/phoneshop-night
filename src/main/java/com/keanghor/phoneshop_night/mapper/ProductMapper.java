package com.keanghor.phoneshop_night.mapper;

import com.keanghor.phoneshop_night.dto.ProductDTO;
import com.keanghor.phoneshop_night.dto.ProductImportDTO;
import com.keanghor.phoneshop_night.entity.Product;
import com.keanghor.phoneshop_night.entity.ProductImportHistory;
import com.keanghor.phoneshop_night.service.ColorService;
import com.keanghor.phoneshop_night.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

//    Import Product
@Mapping(target = "dateImport", source = "productImportDTO.importDate")
@Mapping(target = "pricePerUnit", source = "productImportDTO.importPrice")
@Mapping(target = "product", source = "product")
@Mapping(target = "id", ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDTO productImportDTO, Product product);
}
