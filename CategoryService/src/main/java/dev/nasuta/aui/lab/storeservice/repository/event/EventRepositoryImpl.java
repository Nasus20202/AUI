package dev.nasuta.aui.lab.storeservice.repository.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @Qualifier("product-service")
    private final RestTemplate productServiceRestTemplate;

    @Autowired
    public EventRepositoryImpl(RestTemplate productServiceRestTemplate) {
        this.productServiceRestTemplate = productServiceRestTemplate;
    }

    @Override
    public void sendCreateCategoryEvent(UUID categoryId) {
        productServiceRestTemplate.put("/categories/" + categoryId, null);
    }

    @Override
    public void sendDeleteCategoryEvent(UUID categoryId) {
        productServiceRestTemplate.delete("/categories/" + categoryId);
    }
}
