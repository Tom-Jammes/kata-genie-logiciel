package re.forestier.edu.rpg.avatarclasses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DwarfTest {

    @Nested
    class AbilitiesByLevel {

        @Test
        @DisplayName("Abilities - Dwarf level 1")
        void testAbilitiesDwarfLevel1() {
            HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(1);
            assertEquals(4, abilities.get("ALC"));
            assertEquals(1, abilities.get("INT"));
            assertEquals(3, abilities.get("ATK"));
            assertEquals(3, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Dwarf level 2")
        void testAbilitiesDwarfLevel2() {
            HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(2);
            assertEquals(1, abilities.get("DEF"));
            assertEquals(5, abilities.get("ALC"));
            assertEquals(2, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Dwarf level 3")
        void testAbilitiesDwarfLevel3() {
            HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(3);
            assertEquals(4, abilities.get("ATK"));
            assertEquals(1, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Dwarf level 4")
        void testAbilitiesDwarfLevel4() {
            HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(4);
            assertEquals(2, abilities.get("DEF"));
            assertEquals(1, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Dwarf level 5")
        void testAbilitiesDwarfLevel5() {
            HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(5);
            assertEquals(1, abilities.get("CHA"));
            assertEquals(1, abilities.size());
        }
    }
}