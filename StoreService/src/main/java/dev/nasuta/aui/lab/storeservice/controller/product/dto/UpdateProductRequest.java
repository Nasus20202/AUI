package dev.nasuta.aui.lab.storeservice.controller.product.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateProductRequest {
    String name;
    Integer price;
    Integer stock;
}
