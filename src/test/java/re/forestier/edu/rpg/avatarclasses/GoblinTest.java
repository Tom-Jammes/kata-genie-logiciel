package re.forestier.edu.rpg.avatarclasses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoblinTest {

    @Nested
    class AbilitiesByLevel {
        @Test
        @DisplayName("Abilities - Goblin level 1")
        void testAbilitiesGoblinLevel1() {
            HashMap<String, Integer> abilities = AvatarClass.GOBLIN.getAbilitiesByLevel(1);
            assertEquals(2, abilities.get("INT"));
            assertEquals(2, abilities.get("ATK"));
            assertEquals(1, abilities.get("ALC"));
            assertEquals(3, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Goblin level 2")
        void testAbilitiesGoblinLevel2() {
            HashMap<String, Integer> abilities = AvatarClass.GOBLIN.getAbilitiesByLevel(2);
            assertEquals(3, abilities.get("ATK"));
            assertEquals(4, abilities.get("ALC"));
            assertEquals(2, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Goblin level 3")
        void testAbilitiesGoblinLevel3() {
            HashMap<String, Integer> abilities = AvatarClass.GOBLIN.getAbilitiesByLevel(3);
            assertEquals(1, abilities.get("VIS"));
            assertEquals(1, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Goblin level 4")
        void testAbilitiesGoblinLevel4() {
            HashMap<String, Integer> abilities = AvatarClass.GOBLIN.getAbilitiesByLevel(4);
            assertEquals(1, abilities.get("DEF"));
            assertEquals(1, abilities.size());
        }

        @Test
        @DisplayName("Abilities - Goblin level 5")
        void testAbilitiesGoblinLevel5() {
            HashMap<String, Integer> abilities = AvatarClass.GOBLIN.getAbilitiesByLevel(5);
            assertEquals(2, abilities.get("DEF"));
            assertEquals(4, abilities.get("ATK"));
            assertEquals(2, abilities.size());
        }
    }
}