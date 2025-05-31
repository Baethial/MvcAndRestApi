package com.acm.mvcandrestapi.service;

import com.acm.mvcandrestapi.dto.ProductDto;
import com.acm.mvcandrestapi.model.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(ProductDto productDto);
    void deleteProductById(Long id);
}
