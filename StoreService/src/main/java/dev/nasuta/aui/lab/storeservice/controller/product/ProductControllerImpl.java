package dev.nasuta.aui.lab.storeservice.controller.product;

import dev.nasuta.aui.lab.storeservice.controller.product.dto.CreateProductRequest;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.ProductResponse;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.ProductsResponse;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.UpdateProductRequest;
import dev.nasuta.aui.lab.storeservice.service.product.ProductService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Log
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductsResponse getProducts() {
        return null;
    }

    @Override
    public ProductResponse getProduct(UUID uuid) {
        return null;
    }

    @Override
    public ProductsResponse getProductsByCategory(UUID uuid) {
        return null;
    }

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(UUID uuid, UpdateProductRequest request) {
        return null;
    }

    @Override
    public void deleteProduct(UUID uuid) {

    }
}
