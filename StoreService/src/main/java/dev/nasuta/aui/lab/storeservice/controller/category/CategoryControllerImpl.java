package dev.nasuta.aui.lab.storeservice.controller.category;

import dev.nasuta.aui.lab.storeservice.controller.category.dto.CategoriesResponse;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.CategoryResponse;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.CreateCategoryRequest;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.UpdateCategoryRequest;
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
    public CategoriesResponse getCategories() {
        log.info("Get all categories");

        return CategoriesResponse.from(categoryService.getAll());
    }

    @Override
    public CategoryResponse getCategory(UUID uuid) {
        log.info("Get category with UUID: " + uuid);

        return categoryService.getById(uuid)
                .map(CategoryResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {
        log.info("Create category with name: " + request.getName());

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }
        if (request.getPopularity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Popularity must be greater than or equal to 0");
        }

        var category = request.toEntity();
        categoryService.create(category);

        return CategoryResponse.from(category);
    }

    @Override
    public CategoryResponse updateCategory(UUID uuid, UpdateCategoryRequest request) {
        log.info("Update category with UUID: " + uuid);

        var category = categoryService.getById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (request.getPopularity() != null && request.getPopularity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Popularity must be greater than or equal to 0");
        }

        category = request.updateCategory(category);
        categoryService.update(category);

        return CategoryResponse.from(category);
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
