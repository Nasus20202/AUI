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

    private String brand;

    private double price;

    private Category category;
}
