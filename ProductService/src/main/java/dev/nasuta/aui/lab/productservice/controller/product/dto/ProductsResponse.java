package dev.nasuta.aui.lab.productservice.controller.product.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class ProductsResponse {
    List<Product> products;

    @Value
    @Builder
    public static class Product {
        UUID id;
        String name;
    }
}
