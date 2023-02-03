package ru.geekbrains.march.market.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.entities.ProductEntity;
import ru.geekbrains.march.market.soap.Product;

@Component
public class ToSoapProductConverter {
    public Product entityToSoap(ProductEntity p) {
        Product product = new Product();
        product.setId(p.getId());
        product.setTitle(p.getTitle());
        product.setPrice(p.getPrice().toString());
        return product;
    }
}
