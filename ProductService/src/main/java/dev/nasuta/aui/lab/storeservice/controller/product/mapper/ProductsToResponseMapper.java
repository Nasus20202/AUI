package dev.nasuta.aui.lab.storeservice.controller.product.mapper;

import dev.nasuta.aui.lab.storeservice.controller.product.dto.ProductsResponse;
import dev.nasuta.aui.lab.storeservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ProductsToResponseMapper implements Function<List<Product>, ProductsResponse> {

    @Override
    public ProductsResponse apply(List<Product> products) {
        return ProductsResponse.builder()
                .products(products.stream()
                        .map(product -> ProductsResponse.Product.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .build())
                        .toList())
                .build();
    }
}
