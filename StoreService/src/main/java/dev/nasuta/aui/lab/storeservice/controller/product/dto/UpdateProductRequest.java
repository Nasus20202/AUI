package dev.nasuta.aui.lab.storeservice.controller.product.dto;

import dev.nasuta.aui.lab.storeservice.entity.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateProductRequest {
    String name;
    Integer price;
    Integer stock;

    public Product updateProduct(Product product) {
        if (name != null) {
            product.setName(name);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (stock != null) {
            product.setStock(stock);
        }
        return product;
    }
}
