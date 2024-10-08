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
public class Character implements Serializable {
    private String name;

    private int level;

    private Profession profession;

    public static CharacterBuilder builder() {
        return new AutoCharacterBuilder();
    }

    public static class AutoCharacterBuilder extends CharacterBuilder {
        @Override
        public Character build() {
            if (super.profession == null) {
                throw new IllegalStateException("Profession is required");
            }
            var character = super.build();
            super.profession.getCharacters().add(character);
            return character;
        }
    }
}
