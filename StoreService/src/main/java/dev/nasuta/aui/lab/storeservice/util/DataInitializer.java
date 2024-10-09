package dev.nasuta.aui.lab.storeservice.util;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.entity.Product;
import dev.nasuta.aui.lab.storeservice.repository.category.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DataInitializer {
    private final CategoryRepository categoryRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init() {
        if (categoryRepository.count() > 0) {
            return;
        }

        var categories = List.of(
                Category.builder()
                        .name("Fruits")
                        .description("Fresh and tasty fruits")
                        .id(UUID.fromString("3fab9887-b93e-44ef-b0fb-815898584723"))
                        .build(),
                Category.builder()
                        .name("Vegetables")
                        .description("Healthy vegetables")
                        .id(UUID.fromString("ee3888b5-327b-4d98-addb-ff604e690545"))
                        .build(),
                Category.builder()
                        .name("Diary")
                        .description("Milk and yoghurts")
                        .id(UUID.fromString("7e8df219-68cf-4153-bef3-fcbd8ae728e1"))
                        .build(),
                Category.builder()
                        .name("Baking")
                        .description("Breads and buns")
                        .id(UUID.fromString("b287eb0f-b54d-48c9-8958-3d499426003f"))
                        .build()
        );

        Product.autoBuilder().
                category(categories.get(0)).
                name("Apple").
                price(199).
                stock(68).
                id(UUID.fromString("a76a11ef-9889-4efb-897e-f1606a9d67b9")).
                build();
        Product.autoBuilder().
                category(categories.get(0)).
                name("Pear").
                price(299).
                stock(21).
                id(UUID.fromString("c30bbc1d-1ed9-4933-94b7-23b8d9225180")).
                build();
        Product.autoBuilder().
                category(categories.get(1)).
                name("Carrot").
                price(199).
                stock(66).
                id(UUID.fromString("dd4d38c2-e201-4f1a-bc88-20d833a17ad2")).
                build();
        Product.autoBuilder().
                category(categories.get(1)).
                name("Tomato").
                price(249).
                stock(123).
                id(UUID.fromString("42a95c98-28b1-4be2-904a-5656bac31c04")).
                build();
        Product.autoBuilder().
                category(categories.get(1)).
                name("Potato").
                price(149).
                stock(72).
                id(UUID.fromString("555256f1-e1a7-40f3-a19d-6fdadabdaeca")).
                build();
        Product.autoBuilder().
                category(categories.get(2)).
                name("Milk").
                price(499).
                stock(16).
                id(UUID.fromString("5457ec51-9bde-4782-83c5-884ed496b60c")).
                build();
        Product.autoBuilder().
                category(categories.get(2)).
                name("Yoghurt").
                price(299).
                stock(7).
                id(UUID.fromString("658e0973-a69d-4fc1-b881-a9eda6b9790a")).
                build();
        Product.autoBuilder().
                category(categories.get(3)).
                name("Bread").
                price(599).
                stock(23).
                id(UUID.fromString("8c9e019e-3032-476a-943d-2d849910e216")).
                build();
        Product.autoBuilder().
                category(categories.get(3)).
                name("Bun").
                price(99).
                stock(42).
                id(UUID.fromString("4f8dbcd0-0fb5-42ba-af21-df0984e9b1e7")).
                build();

        categoryRepository.saveAll(categories);
    }
}
