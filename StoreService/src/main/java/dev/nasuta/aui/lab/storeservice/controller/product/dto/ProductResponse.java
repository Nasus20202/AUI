package dev.nasuta.aui.lab.storeservice.controller.product.dto;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.entity.Product;
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
    CategoryDto category;

    public static ProductResponse from(Product product) {
        return builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(CategoryDto.from(product.getCategory()))
                .build();
    }

    @Value
    @Builder
    public static class CategoryDto {
        UUID id;
        String name;

        public static CategoryDto from(Category category) {
            return builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        }
    }
}
