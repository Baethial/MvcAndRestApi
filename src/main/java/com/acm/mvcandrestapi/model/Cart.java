package com.acm.mvcandrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    private Long id;
    private Long userId;
    private Date date;

    private List<CartProduct> products;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartProduct {
        private Long productId;
        private Integer quantity;

        private Product product;
    }
}