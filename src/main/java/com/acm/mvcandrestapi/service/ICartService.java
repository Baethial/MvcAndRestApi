package com.acm.mvcandrestapi.service;

import com.acm.mvcandrestapi.model.Cart;

import java.util.List;

public interface ICartService {
    List<Cart> getCartsByUserId(Long userId);
}
