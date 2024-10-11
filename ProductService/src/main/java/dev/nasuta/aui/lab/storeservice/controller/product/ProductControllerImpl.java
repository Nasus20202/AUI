package dev.nasuta.aui.lab.storeservice.controller.product;

import dev.nasuta.aui.lab.storeservice.controller.product.dto.CreateProductRequest;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.ProductResponse;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.ProductsResponse;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.UpdateProductRequest;
import dev.nasuta.aui.lab.storeservice.controller.product.mapper.CreateRequestToProductMapper;
import dev.nasuta.aui.lab.storeservice.controller.product.mapper.ProductToResponseMapper;
import dev.nasuta.aui.lab.storeservice.controller.product.mapper.ProductsToResponseMapper;
import dev.nasuta.aui.lab.storeservice.service.category.CategoryService;
import dev.nasuta.aui.lab.storeservice.service.product.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    private final ProductToResponseMapper productToResponseMapper;
    private final ProductsToResponseMapper productsToResponseMapper;
    private final CreateRequestToProductMapper createRequestToProductMapper;

    @Autowired
    public ProductControllerImpl(ProductService productService,
                                 CategoryService categoryService,
                                 ProductToResponseMapper productToResponseMapper,
                                 ProductsToResponseMapper productsToResponseMapper,
                                 CreateRequestToProductMapper createRequestToProductMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productToResponseMapper = productToResponseMapper;
        this.productsToResponseMapper = productsToResponseMapper;
        this.createRequestToProductMapper = createRequestToProductMapper;
    }

    @Override
    public ProductsResponse getProducts() {
        log.info("Get all products");

        return productsToResponseMapper.apply(productService.getAll());
    }

    @Override
    public ProductResponse getProduct(UUID uuid) {
        log.info("Get product with UUID: " + uuid);

        return productService.getById(uuid)
                .map(productToResponseMapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Override
    public ProductsResponse getProductsByCategory(UUID uuid) {
        log.info("Get products in category with UUID: " + uuid);

        var category = categoryService.getById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        return productsToResponseMapper.apply(productService.getByCategory(category));

    }

    @Override
    public ProductResponse createProduct(UUID uuid, CreateProductRequest request) {
        log.info("Create product with name: " + request.getName());

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is required");
        }
        if (request.getPrice() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than or equal to 0");
        }
        if (request.getStock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock must be greater than or equal to 0");
        }

        var category = categoryService.getById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        var product = createRequestToProductMapper.apply(request);
        product.setCategory(category);
        productService.create(product);

        return productToResponseMapper.apply(product);
    }

    @Override
    public ProductResponse updateProduct(UUID uuid, UpdateProductRequest request) {
        log.info("Update product with UUID: " + uuid);

        var product = productService.getById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if (request.getName() != null) {
            product.setName(request.getName());
        }
        if (request.getPrice() != null) {
            if (request.getPrice() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price must be greater than or equal to 0");
            }
            product.setPrice(request.getPrice());
        }
        if (request.getStock() != null) {
            if (request.getStock() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock must be greater than or equal to 0");
            }
            product.setStock(request.getStock());
        }

        productService.update(product);

        return productToResponseMapper.apply(product);
    }

    @Override
    public void deleteProduct(UUID uuid) {
        log.info("Delete product with UUID: " + uuid);

        if (productService.getById(uuid).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        productService.delete(uuid);
    }
}
