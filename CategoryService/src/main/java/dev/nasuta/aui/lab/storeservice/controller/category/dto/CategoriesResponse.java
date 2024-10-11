package dev.nasuta.aui.lab.storeservice.controller.category.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class CategoriesResponse {
    List<Category> categories;

    @Value
    @Builder
    public static class Category {
        UUID id;
        String name;
    }
}
