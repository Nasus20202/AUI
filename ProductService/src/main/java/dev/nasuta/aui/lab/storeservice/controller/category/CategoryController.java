package dev.nasuta.aui.lab.storeservice.controller.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

public interface CategoryController {
    @PutMapping("/categories/{uuid}")
    @ResponseStatus(HttpStatus.CREATED)
    void createCategory(@PathVariable UUID uuid);

    @DeleteMapping("/categories/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCategory(@PathVariable UUID uuid);
}
