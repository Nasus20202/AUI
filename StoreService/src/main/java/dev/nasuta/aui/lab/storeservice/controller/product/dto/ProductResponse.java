package dev.nasuta.aui.lab.storeservice.controller.product.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ProductResponse {
    UUID id;
    String name;
    int price;
    int stock;
    Category category;

    @Value
    @Builder
    public static class Category {
        UUID id;
        String name;
    }
}
