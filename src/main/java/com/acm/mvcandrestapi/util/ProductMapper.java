package com.acm.mvcandrestapi.util;

import com.acm.mvcandrestapi.dto.ProductDto;
import com.acm.mvcandrestapi.model.Product;

import java.util.List;

public class ProductMapper {
    public static ProductDto modelToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory())
                .image(product.getImage())
                .rating(RatingMapper.modelToDto(product.getRating()))
                .build();
    }

    public static Product dtoToModel(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .title(productDto.getTitle())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .category(productDto.getCategory())
                .image(productDto.getImage())
                .rating(RatingMapper.dtoToModel(productDto.getRating()))
                .build();
    }

    public static List<ProductDto> modelListToDtoList(List<Product> productList) {
        return productList.stream()
                .map(ProductMapper::modelToDto)
                .toList();
    }

    public static List<Product> dtoListToModelList(List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(ProductMapper::dtoToModel)
                .toList();
    }
}
