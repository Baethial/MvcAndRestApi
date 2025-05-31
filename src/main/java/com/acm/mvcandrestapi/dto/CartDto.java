package com.acm.mvcandrestapi.dto;

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
public class CartDto {
    private Long id;
    private Long userId;
    private Date date;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CartProductDto {
        private Long productId;
        private Integer quantity;
    }

    private List<CartProductDto> products;
}
