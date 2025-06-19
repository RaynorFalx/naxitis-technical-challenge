package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.ImageDto;
import com.ecommerce.backend.model.product.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto toDto(ProductImage productImage);
    ProductImage toEntity(ImageDto imageRequest);

}
