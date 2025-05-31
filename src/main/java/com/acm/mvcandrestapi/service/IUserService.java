package com.acm.mvcandrestapi.service;

import com.acm.mvcandrestapi.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAllUsers();
    Optional<User> findUserByUsername(String username);
}
