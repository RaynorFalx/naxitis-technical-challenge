package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.CategoryDto;
import com.ecommerce.backend.dto.request.ProductRequest;
import com.ecommerce.backend.dto.response.ProductResponse;
import com.ecommerce.backend.mapper.ProductMapper;
import com.ecommerce.backend.model.product.Category;
import com.ecommerce.backend.model.product.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;


    private Product product;
    private Category category;
    private CategoryDto categoryDto;
    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .name("Category")
                .description("DESC")
                .build();

        product = Product.builder()
                .name("PRODUCT")
                .description("DESC")
                .price("1000.00")
                .category(category)
                .build();

        productRequest = new ProductRequest(
                "PRODUCT",
                "DESC",
                "1000.00",
                category.getId()
        );

        categoryDto = new CategoryDto(
                1L,
                "CAT",
                "DESC"
        );

        productResponse = new ProductResponse(
                10L,
                "PRODUCT",
                "DESC",
                "1000.00",
                categoryDto
        );
    }

    @Test
    @DisplayName("Add new product SUCCESS")
    void givenProductRequest_whenAddProduct_thenSuccess() {
        given(productMapper.toEntity(productRequest)).willReturn(product);
        given(categoryService.findCategoryById(product.getCategory().getId())).willReturn(category);
        given(productMapper.toResponse(product)).willReturn(productResponse);
        given(productRepository.save(any(Product.class))).willReturn(product);

        ProductResponse response = productService.addProduct(productRequest);

        verify(productRepository, times(1)).save(any(Product.class));
        assertNotNull(response);
    }

}