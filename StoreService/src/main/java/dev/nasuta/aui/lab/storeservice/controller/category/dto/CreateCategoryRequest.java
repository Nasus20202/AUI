package dev.nasuta.aui.lab.storeservice.controller.category.dto;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCategoryRequest {
    String name;
    String description;

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .description(description)
                .build();
    }
}
