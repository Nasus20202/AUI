package dev.nasuta.aui.lab.storeservice.controller.product.dto;

import dev.nasuta.aui.lab.storeservice.entity.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateProductRequest {
    String name;
    int price;
    int stock;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }
}
