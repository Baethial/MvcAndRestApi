package com.acm.mvcandrestapi.util;

import com.acm.mvcandrestapi.dto.CartDto;
import com.acm.mvcandrestapi.model.Cart;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {

    public static Cart dtoToModel(CartDto dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUserId(dto.getUserId());
        cart.setDate(dto.getDate());
        // Map the list of CartDto.CartProductDto → Cart.CartProduct
        List<Cart.CartProduct> mappedProducts = dto.getProducts()
                .stream()
                .map(cpDto -> {
                    Cart.CartProduct cp = new Cart.CartProduct();
                    cp.setProductId(cpDto.getProductId());
                    cp.setQuantity(cpDto.getQuantity());
                    return cp;
                })
                .collect(Collectors.toList());
        cart.setProducts(mappedProducts);
        return cart;
    }

    public static List<Cart> dtoListToModelList(List<CartDto> dtos) {
        return dtos.stream().map(CartMapper::dtoToModel).collect(Collectors.toList());
    }
}
