package ru.geekbrains.march.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.utils.Cart;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    private Product productById (Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден"));
    }

    public void addToCart(Long productId) {
        cart.add(productById(productId));
    }

    public void deleteProductFromCart(Long productId) {
        cart.removeItem(productById(productId));
        cart.recalculate();
    }

    public void clearAllCart() {
        cart.clear();
    }

    public void subtractOne(Long id) {cart.decrementProductQuantity(id);}

    public void addOne(Long id) {cart.incrementProductQuantity(id);}
}
