package dev.nasuta.aui.lab.categoryservice.controller.category.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CategoryResponse {
    UUID id;
    String name;
    String description;
    int popularity;
}
