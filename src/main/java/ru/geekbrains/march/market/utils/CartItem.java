package ru.geekbrains.march.market.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public void incrementQuantity() {
        quantity++;
        price = price.add(pricePerProduct);
    }
    public void decrementQuantity() {
        if (quantity >= 1) {
            quantity--;
            price = price.subtract(pricePerProduct);
        }
    }
}
