package dev.nasuta.aui.lab.productservice.controller.product.dto;

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
    UUID categoryId;
}
