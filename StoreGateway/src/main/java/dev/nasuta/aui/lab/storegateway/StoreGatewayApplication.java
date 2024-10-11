package dev.nasuta.aui.lab.storegateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StoreGatewayApplication {
    private final String categoryServiceUrl;
    private final String productServiceUrl;

    public StoreGatewayApplication(
            @Value("${store.category-service.url}") String categoryServiceUrl,
            @Value("${store.product-service.url}") String productServiceUrl
    ) {
        this.categoryServiceUrl = categoryServiceUrl;
        this.productServiceUrl = productServiceUrl;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator storeRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("categories", route -> route
                        .path(
                                "/api/categories",
                                "/api/categories/{uuid}"
                        )
                        .uri(categoryServiceUrl)
                )
                .route("products", route -> route
                        .path(
                                "/api/products",
                                "/api/products/{uuid}",
                                "/api/categories/{uuid}/products"
                        )
                        .uri(productServiceUrl)
                )
                .build();
    }
}
