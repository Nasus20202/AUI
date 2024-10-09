package dev.nasuta.aui.lab.storeservice.service.product;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.entity.Product;
import dev.nasuta.aui.lab.storeservice.repository.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> find(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Product> findAllByNameAndCategory(String name, Category category) {
        return productRepository.findByNameIgnoreCaseAndCategory(name, category);
    }

    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(UUID id) {
        productRepository.findById(id).ifPresent(productRepository::delete);
    }
}
