package com.acm.mvcandrestapi.util;

import com.acm.mvcandrestapi.dto.UserDto;
import com.acm.mvcandrestapi.model.User;

import java.util.List;

public class UserMapper {

    public static UserDto modelToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User dtoToModel(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    public static List<UserDto> modelListToDtoList(List<User> userList) {
        return userList.stream()
                .map(UserMapper::modelToDto)
                .toList();
    }

    public static List<User> dtoListToModelList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(UserMapper::dtoToModel)
                .toList();
    }

}
