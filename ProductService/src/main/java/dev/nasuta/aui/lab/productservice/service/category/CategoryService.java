package dev.nasuta.aui.lab.productservice.service.category;

import dev.nasuta.aui.lab.productservice.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAll();

    Optional<Category> getById(UUID id);

    void create(Category category);

    void delete(UUID id);
}
