package dev.nasuta.aui.lab.storeservice.service.product;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(UUID id);

    List<Product> findByCategory(Category category);

    List<Product> findByName(String name);

    List<Product> findByNameAndCategory(String name, Category category);

    void create(Product product);

    void update(Product product);

    void delete(UUID id);
}
