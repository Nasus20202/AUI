package dev.nasuta.aui.lab.entity;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Profession implements Serializable {
    private String name;

    private int baseArmor;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Character> characters = new ArrayList<>();
}