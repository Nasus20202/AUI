package dev.nasuta.aui.lab.storeservice.controller.category;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.service.category.CategoryService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    public CategoryControllerImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void createCategory(UUID uuid) {
        log.info("Create category with UUID: " + uuid);

        if (categoryService.getById(uuid).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        categoryService.create(Category.builder().id(uuid).build());
    }

    @Override
    public void deleteCategory(UUID uuid) {
        log.info("Delete category with UUID: " + uuid);

        if (categoryService.getById(uuid).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        categoryService.delete(uuid);
    }
}
