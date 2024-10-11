package dev.nasuta.aui.lab.storeservice.controller.product;

import dev.nasuta.aui.lab.storeservice.controller.product.dto.CreateProductRequest;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.ProductResponse;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.ProductsResponse;
import dev.nasuta.aui.lab.storeservice.controller.product.dto.UpdateProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface ProductController {
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ProductsResponse getProducts();

    @GetMapping("/products/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ProductResponse getProduct(@PathVariable UUID uuid);

    @GetMapping("/categories/{uuid}/products")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ProductsResponse getProductsByCategory(@PathVariable UUID uuid);

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    ProductResponse createProduct(@RequestBody CreateProductRequest request);

    @PutMapping("/products/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ProductResponse updateProduct(@PathVariable UUID uuid, @RequestBody UpdateProductRequest request);

    @DeleteMapping("/products/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable UUID uuid);
}
