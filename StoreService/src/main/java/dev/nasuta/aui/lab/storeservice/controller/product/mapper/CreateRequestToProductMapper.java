package dev.nasuta.aui.lab.storeservice.controller.product.mapper;

import dev.nasuta.aui.lab.storeservice.controller.product.dto.CreateProductRequest;
import dev.nasuta.aui.lab.storeservice.entity.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateRequestToProductMapper implements Function<CreateProductRequest, Product> {
    @Override
    public Product apply(CreateProductRequest createProductRequest) {
        return Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .stock(createProductRequest.getStock())
                .build();
    }
}
