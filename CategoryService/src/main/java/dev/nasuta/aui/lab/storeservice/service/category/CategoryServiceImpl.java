package dev.nasuta.aui.lab.storeservice.service.category;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.repository.category.CategoryRepository;
import dev.nasuta.aui.lab.storeservice.repository.event.EventRepository;
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

    private final EventRepository eventRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getById(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    @Override
    public void create(Category category) {
        categoryRepository.save(category);
        eventRepository.sendCreateCategoryEvent(category.getId());
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
        eventRepository.sendDeleteCategoryEvent(id);
    }
}
