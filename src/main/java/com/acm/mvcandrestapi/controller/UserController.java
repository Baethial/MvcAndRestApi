package com.acm.mvcandrestapi.controller;

import com.acm.mvcandrestapi.model.User;
import com.acm.mvcandrestapi.service.IProductService;
import com.acm.mvcandrestapi.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IProductService productService;

    @GetMapping("/login")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute User user, BindingResult result, Model model) {

        Optional<User> theUser = userService.findUserByUsername(user.getUsername());

        if (theUser.isEmpty()) {
            model.addAttribute("error", "User not found");
            return "login";
        }

        if (!theUser.get().getPassword().equals(user.getPassword())) {
            model.addAttribute("error", "Access Denied");
            return "login";
        }

        model.addAttribute("username", user.getUsername());
        //model.addAttribute("products", userService.findAllUsers());
        model.addAttribute("products", productService.getAllProducts());

        return "dashboard";
    }

}
