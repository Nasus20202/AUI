package dev.nasuta.aui.lab.storeservice.controller.category.mapper;

import dev.nasuta.aui.lab.storeservice.controller.category.dto.CategoryResponse;
import dev.nasuta.aui.lab.storeservice.entity.Category;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryToResponseMapper implements Function<Category, CategoryResponse> {
    @Override
    public CategoryResponse apply(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .popularity(category.getPopularity())
                .build();
    }
}
