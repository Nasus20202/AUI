package dev.nasuta.aui.lab.categoryservice.util;

import dev.nasuta.aui.lab.categoryservice.entity.Category;
import dev.nasuta.aui.lab.categoryservice.repository.category.CategoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log
public class DataInitializer {
    private final CategoryRepository categoryRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void insertExampleData() {
        if (categoryRepository.count() > 0) {
            return;
        }

        log.info("Inserting example data");

        var categories = List.of(
                Category.builder()
                        .name("Fruits")
                        .description("Fresh and tasty fruits")
                        .popularity(5)
                        .id(UUID.fromString("3fab9887-b93e-44ef-b0fb-815898584723"))
                        .build(),
                Category.builder()
                        .name("Vegetables")
                        .description("Healthy vegetables")
                        .popularity(5)
                        .id(UUID.fromString("ee3888b5-327b-4d98-addb-ff604e690545"))
                        .build(),
                Category.builder()
                        .name("Diary")
                        .description("Milk and yoghurts")
                        .popularity(3)
                        .id(UUID.fromString("7e8df219-68cf-4153-bef3-fcbd8ae728e1"))
                        .build(),
                Category.builder()
                        .name("Baking")
                        .description("Breads and buns")
                        .popularity(4)
                        .id(UUID.fromString("b287eb0f-b54d-48c9-8958-3d499426003f"))
                        .build()
        );

        categoryRepository.saveAll(categories);
    }
}
