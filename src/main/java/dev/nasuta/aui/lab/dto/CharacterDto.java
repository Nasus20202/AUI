package dev.nasuta.aui.lab.dto;

import dev.nasuta.aui.lab.entity.Character;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CharacterDto implements Comparable<CharacterDto> {
    private String name;

    private int level;

    private String profession;

    public CharacterDto(Character character) {
        this(character.getName(), character.getLevel(), character.getProfession().getName());
    }

    @Override
    public int compareTo(CharacterDto o) {
        if (this.level == o.level) {
            return this.name.compareTo(o.name);
        }
        return Integer.compare(this.level, o.level);
    }
}
