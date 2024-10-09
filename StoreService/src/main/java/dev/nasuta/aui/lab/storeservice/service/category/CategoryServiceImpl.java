package dev.nasuta.aui.lab.storeservice.service.category;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.repository.category.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> find(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAllByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    @Override
    public void create(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
    }
}
