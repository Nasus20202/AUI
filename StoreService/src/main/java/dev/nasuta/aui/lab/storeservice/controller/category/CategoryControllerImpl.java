package dev.nasuta.aui.lab.storeservice.controller.category;

import dev.nasuta.aui.lab.storeservice.controller.category.dto.CategoriesResponse;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.CategoryResponse;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.CreateCategoryRequest;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.UpdateCategoryRequest;
import dev.nasuta.aui.lab.storeservice.service.category.CategoryService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("Getting all categories");

        var categories = categoryService.getAll();
        return CategoriesResponse.from(categories);
    }

    @Override
    public CategoryResponse getCategory(UUID uuid) {
        return null;
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {
        return null;
    }

    @Override
    public CategoryResponse updateCategory(UUID uuid, UpdateCategoryRequest request) {
        return null;
    }

    @Override
    public void deleteCategory(UUID uuid) {

    }
}
