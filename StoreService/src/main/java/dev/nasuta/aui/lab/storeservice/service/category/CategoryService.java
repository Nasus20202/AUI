package dev.nasuta.aui.lab.storeservice.service.category;

import dev.nasuta.aui.lab.storeservice.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    List<Category> findAll();

    Optional<Category> findById(UUID id);

    List<Category> findByName(String name);

    void create(Category category);

    void update(Category category);

    void delete(UUID id);
}
