package com.acm.mvcandrestapi.service.impl;

import com.acm.mvcandrestapi.dto.CartDto;
import com.acm.mvcandrestapi.model.Cart;
import com.acm.mvcandrestapi.service.ICartService;
import com.acm.mvcandrestapi.util.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final RestTemplate restTemplate;

    @Override
    public List<Cart> getCartsByUserId(Long userId) {
        try {
            CartDto[] dtos = restTemplate.getForObject("/carts/user/" + userId, CartDto[].class);
            if (dtos == null) {
                return Collections.emptyList();
            }
            return CartMapper.dtoListToModelList(Arrays.asList(dtos));
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
