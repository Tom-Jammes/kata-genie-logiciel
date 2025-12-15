package re.forestier.edu.rpg.avatarclasses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    @Nested
    class AbilitiesByLevel {
        @Test
        @DisplayName("Abilities - Adventurer level 1")
        void testAbilitiesAdventurerLevel1() {
            HashMap<String, Integer> abilities = AvatarClass.ADVENTURER.getAbilitiesByLevel(1);
            assertEquals(1, abilities.get("INT"));
            assertEquals(1, abilities.get("DEF"));
            assertEquals(3, abilities.get("ATK"));
            assertEquals(2, abilities.get("CHA"));
            assertEquals(4, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Adventurer level 2")
        void testAbilitiesAdventurerLevel2() {
            HashMap<String, Integer> abilities = AvatarClass.ADVENTURER.getAbilitiesByLevel(2);
            assertEquals(2, abilities.get("INT"));
            assertEquals(3, abilities.get("CHA"));
            assertEquals(2, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Adventurer level 3")
        void testAbilitiesAdventurerLevel3() {
            HashMap<String, Integer> abilities = AvatarClass.ADVENTURER.getAbilitiesByLevel(3);
            assertEquals(1, abilities.get("ALC"));
            assertEquals(5, abilities.get("ATK"));
            assertEquals(2, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Adventurer level 4")
        void testAbilitiesAdventurerLevel4() {
            HashMap<String, Integer> abilities = AvatarClass.ADVENTURER.getAbilitiesByLevel(4);
            assertEquals(3, abilities.get("DEF"));
            assertEquals(1, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Adventurer level 5")
        void testAbilitiesAdventurerLevel5() {
            HashMap<String, Integer> abilities = AvatarClass.ADVENTURER.getAbilitiesByLevel(5);
            assertEquals(1, abilities.get("VIS"));
            assertEquals(4, abilities.get("DEF"));
            assertEquals(2, abilities.size());
        }
    }
}