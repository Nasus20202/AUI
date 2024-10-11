package dev.nasuta.aui.lab.storeservice.controller.product.dto;

import dev.nasuta.aui.lab.storeservice.entity.Product;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class ProductsResponse {
    List<ProductDto> products;

    public static ProductsResponse from(List<Product> products) {
        return builder()
                .products(products.stream()
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
            return builder()
                    .id(product.getId())
                    .name(product.getName())
                    .build();
        }
    }
}
