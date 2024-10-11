package dev.nasuta.aui.lab.storeservice.controller.category.mapper;

import dev.nasuta.aui.lab.storeservice.controller.category.dto.CreateCategoryRequest;
import dev.nasuta.aui.lab.storeservice.entity.Category;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CreateRequestToCategoryMapper implements Function<CreateCategoryRequest, Category> {
    @Override
    public Category apply(CreateCategoryRequest createCategoryRequest) {
        return Category.builder()
                .name(createCategoryRequest.getName())
                .description(createCategoryRequest.getDescription())
                .popularity(createCategoryRequest.getPopularity())
                .build();
    }
}
