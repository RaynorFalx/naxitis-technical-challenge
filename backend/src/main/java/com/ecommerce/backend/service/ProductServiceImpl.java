package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.request.ProductRequest;
import com.ecommerce.backend.dto.response.ProductResponse;
import com.ecommerce.backend.mapper.ProductMapper;
import com.ecommerce.backend.model.product.Category;
import com.ecommerce.backend.model.product.Product;
import com.ecommerce.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        return productMapper.toResponse(productRepository.save(makeNewProduct(productRequest)));
    }

    @Override
    public ProductRequest updateProduct(Long productId, ProductRequest productRequest) {
        return productMapper.toDto(makeUpdate(productId, productRequest));
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(findProductById(productId).getId());
    }

    @Override
    public ProductRequest getProductById(Long productId) {
        return productMapper.toDto(findProductById(productId));
    }

    private Product findProductById(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id:" +  productId));
    }

    //TODO Extract this "make" methods to a helper class
    private Product makeNewProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);

        return Product.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(categoryService.findCategoryById(productRequest.categoryId()))
                .build();
    }

    private Product makeUpdate(Long productId, ProductRequest productRequest) {
        Category category = categoryService.findCategoryById(productRequest.categoryId());
        Product product = productMapper.updateEntity(findProductById(productId), productRequest);

        product.setCategory(category);
        productRepository.save(product);

        return product;
    }
}
