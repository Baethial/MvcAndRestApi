package com.acm.mvcandrestapi.service.impl;

import com.acm.mvcandrestapi.dto.ProductDto;
import com.acm.mvcandrestapi.model.Product;
import com.acm.mvcandrestapi.service.IProductService;
import com.acm.mvcandrestapi.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final RestTemplate restTemplate;

    @Override
    public Product getProductById(Long id) {
        return restTemplate.getForObject("/products/" + id, Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        ProductDto[] productDtoList = restTemplate.getForObject("/products", ProductDto[].class);

        if(productDtoList.length == 0) {
            return Collections.emptyList();
        }

        return ProductMapper.dtoListToModelList(Arrays.asList(productDtoList));
    }

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = ProductMapper.dtoToModel(productDto);
        System.out.println(product);
        return restTemplate.postForObject("/products", product, Product.class);
    }

    @Override
    public void deleteProductById(Long id) {
        restTemplate.delete("/products/" + id);
    }
}
