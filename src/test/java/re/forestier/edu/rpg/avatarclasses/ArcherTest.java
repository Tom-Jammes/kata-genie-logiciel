package re.forestier.edu.rpg.avatarclasses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArcherTest {

    @Nested
    class AbilitiesByLevel {

        @Test
        @DisplayName("Abilities - Archer level 1")
        void testAbilitiesArcherLevel1() {
            HashMap<String, Integer> abilities = AvatarClass.ARCHER.getAbilitiesByLevel(1);
            assertEquals(1, abilities.get("INT"));
            assertEquals(3, abilities.get("ATK"));
            assertEquals(1, abilities.get("CHA"));
            assertEquals(3, abilities.get("VIS"));
            assertEquals(4, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Archer level 2")
        void testAbilitiesArcherLevel2() {
            HashMap<String, Integer> abilities = AvatarClass.ARCHER.getAbilitiesByLevel(2);
            assertEquals(1, abilities.get("DEF"));
            assertEquals(2, abilities.get("CHA"));
            assertEquals(2, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Archer level 3")
        void testAbilitiesArcherLevel3() {
            HashMap<String, Integer> abilities = AvatarClass.ARCHER.getAbilitiesByLevel(3);
            assertEquals(3, abilities.get("ATK"));
            assertEquals(1, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Archer level 4")
        void testAbilitiesArcherLevel4() {
            HashMap<String, Integer> abilities = AvatarClass.ARCHER.getAbilitiesByLevel(4);
            assertEquals(2, abilities.get("DEF"));
            assertEquals(1, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Archer level 5")
        void testAbilitiesArcherLevel5() {
            HashMap<String, Integer> abilities = AvatarClass.ARCHER.getAbilitiesByLevel(5);
            assertEquals(4, abilities.get("ATK"));
            assertEquals(1, abilities.size());
        }
    }
}