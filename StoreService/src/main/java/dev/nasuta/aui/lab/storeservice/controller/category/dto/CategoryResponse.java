package dev.nasuta.aui.lab.storeservice.controller.category.dto;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.entity.Product;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class CategoryResponse {
    UUID id;
    String name;
    String description;
    List<ProductDto> products;

    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .products(category.getProducts().stream()
                        .map(ProductDto::from)
                        .toList())
                .build();
    }

    @Value
    @Builder
    public static class ProductDto {
        UUID id;
        String name;

        public static ProductDto from(Product product) {
            return ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .build();
        }
    }
}
