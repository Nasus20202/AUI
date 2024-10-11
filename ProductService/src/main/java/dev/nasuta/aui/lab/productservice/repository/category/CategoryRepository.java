package dev.nasuta.aui.lab.productservice.repository.category;

import dev.nasuta.aui.lab.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAll();

    Optional<Category> findById(UUID id);
}
