package com.acm.mvcandrestapi.controller;

import com.acm.mvcandrestapi.dto.UserDto;
import com.acm.mvcandrestapi.model.Cart;
import com.acm.mvcandrestapi.model.Product;
import com.acm.mvcandrestapi.model.User;
import com.acm.mvcandrestapi.service.ICartService;
import com.acm.mvcandrestapi.service.IProductService;
import com.acm.mvcandrestapi.service.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IProductService productService;
    private final ICartService cartService;

    @GetMapping({"/", "/login"})
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult,
            Model model,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        Optional<User> maybeUser = userService.findUserByUsername(userDto.getUsername());
        if (maybeUser.isEmpty() || !maybeUser.get().getPassword().equals(userDto.getPassword())) {
            model.addAttribute("error", "Access Denied: Invalid username or password");
            return "login";
        }

        User loggedInUser = maybeUser.get();
        session.setAttribute("loggedInUser", loggedInUser);

        model.addAttribute("username", loggedInUser.getUsername());
        model.addAttribute("products", productService.getAllProducts());
        return "dashboard";
    }

    @GetMapping("/carts")
    public String showCartsForLoggedUser(
            Model model,
            HttpSession session
    ) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", loggedInUser.getUsername());
        model.addAttribute("products", productService.getAllProducts());

        Long userId = loggedInUser.getId();
        List<Cart> carts = cartService.getCartsByUserId(userId);

        for (Cart cart : carts) {
            cart.getProducts().forEach(cp -> {
                try {
                    Product fullProduct = productService.getProductById(cp.getProductId());
                    cp.setProduct(fullProduct);
                } catch (Exception ignored) {
                }
            });
        }

        model.addAttribute("carts", carts);
        return "dashboard";
    }

    /*@GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }*/

}
