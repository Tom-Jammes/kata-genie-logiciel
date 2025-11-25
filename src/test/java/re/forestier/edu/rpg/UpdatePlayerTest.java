package re.forestier.edu.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePlayerTest {

    private player archer;
    private player adventurer;
    private player dwarf;

    @BeforeEach
    void setUp() {
        ArrayList<String> inventory = new ArrayList<>();
        archer = new player("John", "Robin", "ARCHER", 100, inventory);
        adventurer = new player("Alice", "Lara", "ADVENTURER", 100, inventory);
        dwarf = new player("Bob", "Gimli", "DWARF", 100, inventory);
    }

    @Nested
    class AddXp {
        @Test
        @DisplayName("addXp - ajout d'XP sans changement de niveau")
        void testAddXpMultipleTimesWithoutLevelUp() {
            archer.xp = 0;

            UpdatePlayer.addXp(archer, 2);
            assertEquals(2, archer.xp);

            UpdatePlayer.addXp(archer, 3);
            assertEquals(5, archer.xp);

            assertEquals(1, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - ajout de 0 XP")
        void testAddXpZero() {
            archer.xp = 5;

            boolean leveledUp = UpdatePlayer.addXp(archer, 0);

            assertFalse(leveledUp);
            assertEquals(5, archer.xp);
        }

        @Test
        @DisplayName("addXp - level up de 1 à 2")
        void testAddXpLevelUpFrom1To2() {
            archer.xp = 0;

            boolean leveledUp = UpdatePlayer.addXp(archer, 10);

            assertTrue(leveledUp);
            assertEquals(10, archer.xp);
            assertEquals(2, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - level up de 2 à 3")
        void testAddXpLevelUpFrom2To3() {
            archer.xp = 10;

            boolean leveledUp = UpdatePlayer.addXp(archer, 17);

            assertTrue(leveledUp);
            assertEquals(27, archer.xp);
            assertEquals(3, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - level up de 3 à 4")
        void testAddXpLevelUpFrom3To4() {
            archer.xp = 27;

            boolean leveledUp = UpdatePlayer.addXp(archer, 30);

            assertTrue(leveledUp);
            assertEquals(57, archer.xp);
            assertEquals(4, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - level up de 4 à 5")
        void testAddXpLevelUpFrom4To5() {
            archer.xp = 57;

            boolean leveledUp = UpdatePlayer.addXp(archer, 54);

            assertTrue(leveledUp);
            assertEquals(111, archer.xp);
            assertEquals(5, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - ajout d'un objet aléatoire lors du level up")
        void testAddXpAddsRandomObjectOnLevelUp() {
            archer.xp = 0;
            archer.inventory.clear();

            UpdatePlayer.addXp(archer, 10);

            assertEquals(1, archer.inventory.size());
            assertNotNull(archer.inventory.getFirst());
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up ARCHER")
        void testAddXpUpdatesAbilitiesForArcher() {
            archer.xp = 0;

            assertNotEquals(UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(2), archer.abilities);

            UpdatePlayer.addXp(archer, 10);

            assertNotEquals(UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(2), archer.abilities);
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up ADVENTURER")
        void testAddXpUpdatesAbilitiesForAdventurer() {
            adventurer.xp = 10;

            assertNotEquals(UpdatePlayer.abilitiesPerTypeAndLevel().get("ADVENTURER").get(3), adventurer.abilities);

            UpdatePlayer.addXp(adventurer, 17);

            assertNotEquals(UpdatePlayer.abilitiesPerTypeAndLevel().get("ADVENTURER").get(3), adventurer.abilities);
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up DWARF")
        void testAddXpUpdatesAbilitiesForDwarf() {
            dwarf.xp = 0;

            assertNotEquals(UpdatePlayer.abilitiesPerTypeAndLevel().get("DWARF").get(2), dwarf.abilities);

            UpdatePlayer.addXp(dwarf, 10);

            assertNotEquals(UpdatePlayer.abilitiesPerTypeAndLevel().get("DWARF").get(2), dwarf.abilities);
        }

    }

}
