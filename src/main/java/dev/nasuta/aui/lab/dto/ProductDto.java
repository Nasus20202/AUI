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

    private int price;

    private int stock;

    private String category;

    public ProductDto(Product product) {
        this(product.getName(), product.getStock(), product.getPrice(), product.getCategory().getName());
    }

    @Override
    public int compareTo(ProductDto o) {
        if (this.price == o.price) {
            return this.name.compareTo(o.name);
        }
        return Integer.compare(this.price, o.price);
    }
}
