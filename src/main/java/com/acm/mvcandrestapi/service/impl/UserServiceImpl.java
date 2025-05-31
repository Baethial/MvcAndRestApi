package com.acm.mvcandrestapi.service.impl;

import com.acm.mvcandrestapi.dto.UserDto;
import com.acm.mvcandrestapi.model.User;
import com.acm.mvcandrestapi.service.IUserService;
import com.acm.mvcandrestapi.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final RestTemplate restTemplate;

    @Override
    public List<User> findAllUsers() {
        UserDto[] userDtoList = restTemplate.getForObject("/users", UserDto[].class);
        if (userDtoList.length == 0) {
            return Collections.emptyList();
        }
        return UserMapper.dtoListToModelList(Arrays.asList(userDtoList));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        System.out.println(username);
        List<User> userList = findAllUsers();
        System.out.println(userList);

        return userList.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }
}
