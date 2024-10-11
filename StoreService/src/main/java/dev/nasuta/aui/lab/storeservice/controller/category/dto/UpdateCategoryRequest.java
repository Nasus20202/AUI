package dev.nasuta.aui.lab.storeservice.controller.category.dto;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateCategoryRequest {
    String name;
    String description;

    public Category updateCategory(Category category) {
        if (name != null) {
            category.setName(name);
        }
        if (description != null) {
            category.setDescription(description);
        }
        return category;
    }
}
