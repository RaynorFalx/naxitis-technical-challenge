package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.request.ProductRequest;
import com.ecommerce.backend.dto.response.ProductResponse;

public interface ProductService {
    void deleteProduct(Long id);
    ProductRequest getProductById(Long productId);
    ProductResponse addProduct(ProductRequest productRequest);
    ProductRequest updateProduct(Long productId, ProductRequest productRequest);
}
