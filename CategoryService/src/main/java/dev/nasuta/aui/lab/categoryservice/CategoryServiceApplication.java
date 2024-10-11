package dev.nasuta.aui.lab.categoryservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CategoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryServiceApplication.class, args);
    }

    @Bean
    @Qualifier("product-service")
    public RestTemplate productServiceRestTemplate(@Value("${store.product-service.url}") String productServiceUrl) {
        return new RestTemplateBuilder()
                .rootUri(productServiceUrl)
                .build();
    }
}
