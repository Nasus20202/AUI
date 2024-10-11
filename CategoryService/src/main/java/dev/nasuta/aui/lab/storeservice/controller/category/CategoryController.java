package dev.nasuta.aui.lab.storeservice.controller.category;

import dev.nasuta.aui.lab.storeservice.controller.category.dto.CategoriesResponse;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.CategoryResponse;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.CreateCategoryRequest;
import dev.nasuta.aui.lab.storeservice.controller.category.dto.UpdateCategoryRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface CategoryController {
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    CategoriesResponse getCategories();

    @GetMapping("/categories/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    CategoryResponse getCategory(@PathVariable UUID uuid);

    @PutMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    CategoryResponse createCategory(@RequestBody CreateCategoryRequest request);

    @PatchMapping("/categories/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    CategoryResponse updateCategory(@PathVariable UUID uuid, @RequestBody UpdateCategoryRequest request);

    @DeleteMapping("/categories/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategory(@PathVariable UUID uuid);
}
