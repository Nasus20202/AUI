package dev.nasuta.aui.lab.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product implements Serializable {
    private String name;

    private int price;

    private int stock;

    private Category category;

    public static ProductBuilder builder() {
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
