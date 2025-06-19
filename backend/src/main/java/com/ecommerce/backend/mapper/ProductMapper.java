package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.request.ProductRequest;
import com.ecommerce.backend.dto.response.ProductResponse;
import com.ecommerce.backend.model.product.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "categoryId", source = "product.category.id")
    ProductRequest toDto(Product product);

    @InheritInverseConfiguration
    Product toEntity(ProductRequest productRequest);

    ProductResponse toResponse(Product product);

    default Product updateEntity(@MappingTarget Product product, ProductRequest dto) {
        if (dto == null) return null;

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        return product;
    }

}
