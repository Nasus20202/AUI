package dev.nasuta.aui.lab.dto;

import dev.nasuta.aui.lab.entity.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductDto implements Comparable<ProductDto> {
    private String name;

    private String brand;

    private double price;

    private String category;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.category = product.getCategory().getName();
    }

    @Override
    public int compareTo(ProductDto o) {
        return this.name.compareTo(o.name);
    }
}
