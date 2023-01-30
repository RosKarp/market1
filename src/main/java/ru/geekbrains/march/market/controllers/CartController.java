package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.converters.CartConverter;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }
    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId) {
        cartService.addToCart(productId);
    }

    @GetMapping("/delete/{productId}")
    public void deleteProductFromCart(@PathVariable Long productId) {
        cartService.deleteProductFromCart(productId);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearAllCart();
    }

    @GetMapping("/subtract_one/{id}")
    public void subOne(@PathVariable Long id) {
        cartService.subtractOne(id);
    }

    @GetMapping("/add_one/{id}")
    public void addOne(@PathVariable Long id) {
        cartService.addOne(id);
    }
}
