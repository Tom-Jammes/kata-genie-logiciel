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
        @DisplayName("addXp - level up de plusieurs niveaux")
        void testAddXpLevelUpManyLevels() {
            archer.xp = 0;
            assertEquals(1, archer.retrieveLevel());

            boolean leveledUp = UpdatePlayer.addXp(archer, 111);

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

    @Nested
    class UpdateEndOfRound {
        @Test
        @DisplayName("majFinDeTour - joueur KO (HP = 0)")
        void testMajFinDeTourPlayerKO() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 0;

            UpdatePlayer.majFinDeTour(archer);

            // Le joueur reste à 0 HP
            assertEquals(0, archer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP < 50% sans Magic Bow")
        void testMajFinDeTourArcherLowHealthNoMagicBow() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 40;

            UpdatePlayer.majFinDeTour(archer);

            assertEquals(41, archer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP < 50% avec Magic Bow")
        void testMajFinDeTourArcherLowHealthWithMagicBow() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 40;
            archer.inventory.add("Magic Bow");

            UpdatePlayer.majFinDeTour(archer);

            // 40 + 1 = 41, puis 41 + (41/8 - 1) = 41 + 4 = 45
            assertEquals(45, archer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - DWARF avec HP < 50% sans Holy Elixir")
        void testMajFinDeTourDwarfLowHealthNoElixir() {
            dwarf.healthpoints = 100;
            dwarf.currenthealthpoints = 40;

            UpdatePlayer.majFinDeTour(dwarf);

            assertEquals(41, dwarf.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - DWARF avec HP < 50% avec Holy Elixir")
        void testMajFinDeTourDwarfLowHealthWithElixir() {
            dwarf.healthpoints = 100;
            dwarf.currenthealthpoints = 40;
            dwarf.inventory.add("Holy Elixir");

            UpdatePlayer.majFinDeTour(dwarf);

            // 40 + 1 (elixir) + 1 (dwarf) = 42
            assertEquals(42, dwarf.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - ADVENTURER avec HP < 50% et niveau < 3")
        void testMajFinDeTourAdventurerLowHealthLowLevel() {
            adventurer.healthpoints = 100;
            adventurer.currenthealthpoints = 40;
            adventurer.xp = 0; // niveau 1

            UpdatePlayer.majFinDeTour(adventurer);

            // 40 + 2 - 1 = 41
            assertEquals(41, adventurer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - ADVENTURER avec HP < 50% et niveau >= 3")
        void testMajFinDeTourAdventurerLowHealthHighLevel() {
            adventurer.healthpoints = 100;
            adventurer.currenthealthpoints = 40;
            adventurer.xp = 27; // niveau 3

            UpdatePlayer.majFinDeTour(adventurer);

            // 40 + 2 = 42
            assertEquals(42, adventurer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - joueur avec HP >= 50% et < max")
        void testMajFinDeTourPlayerHealthAboveHalf() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 60;

            UpdatePlayer.majFinDeTour(archer);

            // Pas de changement dans cette condition
            assertEquals(60, archer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - joueur avec HP = max")
        void testMajFinDeTourPlayerFullHealth() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 100;

            UpdatePlayer.majFinDeTour(archer);

            assertEquals(100, archer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP exactement à 50%")
        void testMajFinDeTourArcherExactlyHalfHealth() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 50;

            UpdatePlayer.majFinDeTour(archer);

            // HP >= healthpoints/2, donc pas de soin
            assertEquals(50, archer.currenthealthpoints);
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP juste en dessous de 50%")
        void testMajFinDeTourArcherJustBelowHalfHealth() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 49;

            UpdatePlayer.majFinDeTour(archer);

            // HP < healthpoints/2, donc soin
            assertEquals(50, archer.currenthealthpoints);
        }
    }

}
