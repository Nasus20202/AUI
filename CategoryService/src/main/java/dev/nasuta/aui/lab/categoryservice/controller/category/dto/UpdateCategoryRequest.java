package dev.nasuta.aui.lab.categoryservice.controller.category.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateCategoryRequest {
    String name;
    String description;
    Integer popularity;
}
