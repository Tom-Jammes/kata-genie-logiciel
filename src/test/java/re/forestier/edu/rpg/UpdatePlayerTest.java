package re.forestier.edu.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.avatarclasses.Adventurer;
import re.forestier.edu.rpg.avatarclasses.Archer;
import re.forestier.edu.rpg.avatarclasses.Dwarf;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePlayerTest {

    private Player archer;
    private Player adventurer;
    private Player dwarf;

    @BeforeEach
    void setUp() {
        ArrayList<String> inventory = new ArrayList<>();
        archer = new Player("John", "Robin", "ARCHER", 100, inventory);
        adventurer = new Player("Alice", "Lara", "ADVENTURER", 100, inventory);
        dwarf = new Player("Bob", "Gimli", "DWARF", 100, inventory);
    }

    @Nested
    class AddXp {
        @Test
        @DisplayName("addXp - ajout d'XP sans changement de niveau")
        void testAddXpMultipleTimesWithoutLevelUp() {
            archer.setXp(0);

            UpdatePlayer.addXp(archer, 2);
            assertEquals(2, archer.getXp());

            UpdatePlayer.addXp(archer, 3);
            assertEquals(5, archer.getXp());

            assertEquals(1, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - ajout de 0 XP")
        void testAddXpZero() {
            archer.setXp(5);

            boolean leveledUp = UpdatePlayer.addXp(archer, 0);

            assertFalse(leveledUp);
            assertEquals(5, archer.getXp());
        }

        @Test
        @DisplayName("addXp - level up de 1 à 2")
        void testAddXpLevelUpFrom1To2() {
            archer.setXp(0);

            boolean leveledUp = UpdatePlayer.addXp(archer, 10);

            assertTrue(leveledUp);
            assertEquals(10, archer.getXp());
            assertEquals(2, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - level up de 2 à 3")
        void testAddXpLevelUpFrom2To3() {
            archer.setXp(10);

            boolean leveledUp = UpdatePlayer.addXp(archer, 17);

            assertTrue(leveledUp);
            assertEquals(27, archer.getXp());
            assertEquals(3, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - level up de 3 à 4")
        void testAddXpLevelUpFrom3To4() {
            archer.setXp(27);

            boolean leveledUp = UpdatePlayer.addXp(archer, 30);

            assertTrue(leveledUp);
            assertEquals(57, archer.getXp());
            assertEquals(4, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - level up de 4 à 5")
        void testAddXpLevelUpFrom4To5() {
            archer.setXp(57);

            boolean leveledUp = UpdatePlayer.addXp(archer, 54);

            assertTrue(leveledUp);
            assertEquals(111, archer.getXp());
            assertEquals(5, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - level up de plusieurs niveaux")
        void testAddXpLevelUpManyLevels() {
            archer.setXp(0);
            assertEquals(1, archer.retrieveLevel());

            boolean leveledUp = UpdatePlayer.addXp(archer, 111);

            assertTrue(leveledUp);
            assertEquals(111, archer.getXp());
            assertEquals(5, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - ajout d'un objet aléatoire lors du level up")
        void testAddXpAddsRandomObjectOnLevelUp() {
            archer.setXp(0);
            archer.getInventory().clear();

            UpdatePlayer.addXp(archer, 10);

            assertEquals(1, archer.getInventory().size());
            assertNotNull(archer.getInventory().getFirst());
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up ARCHER")
        void testAddXpUpdatesAbilitiesForArcher() {
            Archer archerClass = new Archer();

            assertNotEquals(archerClass.getAbilitiesByLevel(2), archer.getAbilities());

            UpdatePlayer.addXp(archer, 10);

            assertNotEquals(archerClass.getAbilitiesByLevel(2), archer.getAbilities());
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up ADVENTURER")
        void testAddXpUpdatesAbilitiesForAdventurer() {
            Adventurer adventurerClass = new Adventurer();

            assertNotEquals(adventurerClass.getAbilitiesByLevel(3), adventurer.getAbilities());

            UpdatePlayer.addXp(adventurer, 17);

            assertNotEquals(adventurerClass.getAbilitiesByLevel(3), adventurer.getAbilities());
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up DWARF")
        void testAddXpUpdatesAbilitiesForDwarf() {
            Dwarf dwarfClass = new Dwarf();

            assertNotEquals(dwarfClass.getAbilitiesByLevel(2), dwarf.getAbilities());

            UpdatePlayer.addXp(dwarf, 10);

            assertNotEquals(dwarfClass.getAbilitiesByLevel(2), dwarf.getAbilities());
        }

    }

    @Nested
    class UpdateEndOfRound {
        @Test
        @DisplayName("majFinDeTour - joueur KO (HP = 0)")
        void testMajFinDeTourPlayerKO() {
            archer.setMaxHP(100);
            archer.setCurrentHP(0);

            UpdatePlayer.majFinDeTour(archer);

            // Le joueur reste à 0 HP
            assertEquals(0, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP < 50% sans Magic Bow")
        void testMajFinDeTourArcherLowHealthNoMagicBow() {
            archer.setMaxHP(100);
            archer.setCurrentHP(40);

            UpdatePlayer.majFinDeTour(archer);

            assertEquals(41, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP < 50% avec Magic Bow")
        void testMajFinDeTourArcherLowHealthWithMagicBow() {
            archer.setMaxHP(100);
            archer.setCurrentHP(40);
            archer.addObjectInventory("Magic Bow");

            UpdatePlayer.majFinDeTour(archer);

            // 40 + 1 = 41, puis 41 + (41/8 - 1) = 41 + 4 = 45
            assertEquals(45, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - DWARF avec HP < 50% sans Holy Elixir")
        void testMajFinDeTourDwarfLowHealthNoElixir() {
            dwarf.setMaxHP(100);
            dwarf.setCurrentHP(40);

            UpdatePlayer.majFinDeTour(dwarf);

            assertEquals(41, dwarf.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - DWARF avec HP < 50% avec Holy Elixir")
        void testMajFinDeTourDwarfLowHealthWithElixir() {
            dwarf.setMaxHP(100);
            dwarf.setCurrentHP(40);
            dwarf.addObjectInventory("Holy Elixir");

            UpdatePlayer.majFinDeTour(dwarf);

            // 40 + 1 (elixir) + 1 (dwarf) = 42
            assertEquals(42, dwarf.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ADVENTURER avec HP < 50% et niveau < 3")
        void testMajFinDeTourAdventurerLowHealthLowLevel() {
            adventurer.setMaxHP(100);
            adventurer.setCurrentHP(40);
            adventurer.setXp(0); // niveau 1

            UpdatePlayer.majFinDeTour(adventurer);

            // 40 + 2 - 1 = 41
            assertEquals(41, adventurer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ADVENTURER avec HP < 50% et niveau >= 3")
        void testMajFinDeTourAdventurerLowHealthHighLevel() {
            adventurer.setMaxHP(100);
            adventurer.setCurrentHP(40);
            adventurer.setXp(27); // niveau 3

            UpdatePlayer.majFinDeTour(adventurer);

            // 40 + 2 = 42
            assertEquals(42, adventurer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - joueur avec HP >= 50% et < max")
        void testMajFinDeTourPlayerHealthAboveHalf() {
            archer.setMaxHP(100);
            archer.setCurrentHP(60);

            UpdatePlayer.majFinDeTour(archer);

            // Pas de changement dans cette condition
            assertEquals(60, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - joueur avec HP = max")
        void testMajFinDeTourPlayerFullHealth() {
            archer.setMaxHP(100);
            archer.setCurrentHP(100);

            UpdatePlayer.majFinDeTour(archer);

            assertEquals(100, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP exactement à 50%")
        void testMajFinDeTourArcherExactlyHalfHealth() {
            archer.setMaxHP(100);
            archer.setCurrentHP(50);

            UpdatePlayer.majFinDeTour(archer);

            // HP >= healthpoints/2, donc pas de soin
            assertEquals(50, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP juste en dessous de 50%")
        void testMajFinDeTourArcherJustBelowHalfHealth() {
            archer.setMaxHP(100);
            archer.setCurrentHP(49);

            UpdatePlayer.majFinDeTour(archer);

            // HP < healthpoints/2, donc soin
            assertEquals(50, archer.getCurrentHP());
        }
    }

}
