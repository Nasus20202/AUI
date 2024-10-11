package dev.nasuta.aui.lab.storeservice.controller.category.dto;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class CategoriesResponse {
    List<CategoryDto> categories;

    public static CategoriesResponse from(List<Category> categories) {
        return builder()
                .categories(categories.stream()
                        .map(CategoryDto::from)
                        .toList())
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
