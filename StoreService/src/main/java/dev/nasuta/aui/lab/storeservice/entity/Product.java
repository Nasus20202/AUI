package dev.nasuta.aui.lab.storeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product {
    @Id
    @ToString.Exclude
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "stock")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public static ProductBuilder autoBuilder() {
        return new AutoProductBuilder();
    }

    public static class AutoProductBuilder extends ProductBuilder {
        @Override
        public Product build() {
            if (super.category == null) {
                throw new IllegalStateException("Category is required");
            }
            var product = super.build();
            super.category.getProducts().add(product);
            return product;
        }
    }
}
