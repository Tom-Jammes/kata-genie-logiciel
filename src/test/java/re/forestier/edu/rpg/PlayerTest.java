package re.forestier.edu.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private ArrayList<String> emptyInventory;
    private ArrayList<String> notEmptyInventory;

    @BeforeEach
    void setUp() {
        emptyInventory = new ArrayList<>();
        notEmptyInventory = new ArrayList<>();
        notEmptyInventory.add("Sword");
        notEmptyInventory.add("Potion");
    }

    @Nested
    class Constructor {
        @Test
        @DisplayName("Constructeur avec classe ARCHER valide")
        void testConstructorWithValidArcherClass() {
            Player p = new Player("John", "Robin", "ARCHER", 100, notEmptyInventory);

            assertNotNull(p);
            assertEquals("John", p.playerName);
            assertEquals("Robin", p.Avatar_name);
            assertEquals("ARCHER", p.getAvatarClass());
            assertEquals(100, p.money);
            assertEquals(notEmptyInventory, p.inventory);
            assertNotNull(p.abilities);
        }

        @Test
        @DisplayName("Constructeur avec classe ADVENTURER valide")
        void testConstructorWithValidAdventurerClass() {
            Player p = new Player("Alice", "Lara", "ADVENTURER", 200, notEmptyInventory);

            assertNotNull(p);
            assertEquals("Alice", p.playerName);
            assertEquals("Lara", p.Avatar_name);
            assertEquals("ADVENTURER", p.getAvatarClass());
            assertEquals(200, p.money);
        }

        @Test
        @DisplayName("Constructeur avec classe DWARF valide")
        void testConstructorWithValidDwarfClass() {
            Player p = new Player("Bob", "Gimli", "DWARF", 50, notEmptyInventory);

            assertNotNull(p);
            assertEquals("Bob", p.playerName);
            assertEquals("Gimli", p.Avatar_name);
            assertEquals("DWARF", p.getAvatarClass());
        }

        @Test
        @DisplayName("Impossible to create a player with an invalid avatarClass")
        void mustNotCreateAPlayerWithAnInvalidAvatarClass() {
            Player p = new Player("Alice", "Alice the skeleton", "InvalidAvatarClass", 100, notEmptyInventory);
            assertNull(p.playerName);
            assertNull(p.Avatar_name);
            assertNull(p.getAvatarClass());
            assertNull(p.money);
        }

        @Test
        @DisplayName("Constructeur avec argent à zéro")
        void testConstructorWithZeroMoney() {
            Player p = new Player("Poor", "Avatar", "ARCHER", 0, notEmptyInventory);

            assertEquals(0, p.money);
        }

        @Test
        @DisplayName("Constructeur avec inventaire vide")
        void testConstructorWithEmptyInventory() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, emptyInventory);

            assertNotNull(p.inventory);
            assertEquals(0, p.inventory.size());
        }

        @Test
        @DisplayName("Constructeur avec inventaire non vide")
        void testConstructorWithNotEmptyInventory() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            assertNotNull(p.inventory);
            assertEquals(2, p.inventory.size());
            assertEquals("Sword", p.inventory.get(0));
            assertEquals("Potion", p.inventory.get(1));
        }
    }

    @Nested
    class Money {
        @Test
        @DisplayName("removeMoney - retrait valide")
        void testRemoveMoneyValid() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.removeMoney(50);

            assertEquals(50, p.money);
        }

        @Test
        @DisplayName("removeMoney - retrait total")
        void testRemoveMoneyAll() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.removeMoney(100);

            assertEquals(0, p.money);
        }

        @Test
        @DisplayName("removeMoney - montant négatif provoque une exception")
        void testRemoveMoneyNegativeResult() {
            Player p = new Player("John", "Avatar", "ARCHER", 50, notEmptyInventory);

            try {
                p.removeMoney(100);
                fail();
            } catch (IllegalArgumentException e) {
                assertEquals("Player can't have a negative money!", e.getMessage());
                assertEquals(50, p.money); // L'argent ne doit pas avoir changé
            }
        }

        @Test
        @DisplayName("removeMoney - retrait de zéro")
        void testRemoveMoneyZero() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.removeMoney(0);

            assertEquals(100, p.money);
        }

        @Test
        @DisplayName("addMoney - ajout valide")
        void testAddMoneyValid() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.addMoney(50);

            assertEquals(150, p.money);
        }

        @Test
        @DisplayName("addMoney - ajout de zéro")
        void testAddMoneyZero() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.addMoney(0);

            assertEquals(100, p.money);
        }

        @Test
        @DisplayName("addMoney - multiple ajouts successifs")
        void testAddMoneyMultipleTimes() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.addMoney(25);
            p.addMoney(25);
            p.addMoney(50);

            assertEquals(200, p.money);
        }
    }

    @Nested
    class RetrieveLvl {
        @Test
        @DisplayName("retrieveLevel - niveau 1 avec XP = 0")
        void testRetrieveLevelOne_XpZero() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            assertEquals(1, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 1 avec XP = 9")
        void testRetrieveLevelOne_XpNine() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 9;

            assertEquals(1, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 2 avec XP = 10")
        void testRetrieveLevelTwo_XpTen() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 10;

            assertEquals(2, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 2 avec XP = 26")
        void testRetrieveLevelTwo_XpTwentySix() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 26;

            assertEquals(2, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 3 avec XP = 27")
        void testRetrieveLevelThree_XpTwentySeven() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 27;

            assertEquals(3, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 3 avec XP = 56")
        void testRetrieveLevelThree_XpFiftySix() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 56;

            assertEquals(3, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 4 avec XP = 57")
        void testRetrieveLevelFour_XpFiftySeven() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 57;

            assertEquals(4, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 4 avec XP = 110")
        void testRetrieveLevelFour_XpOneHundredTen() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 110;

            assertEquals(4, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 5 avec XP = 111")
        void testRetrieveLevelFive_XpOneHundredEleven() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 111;

            assertEquals(5, p.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 5 avec XP très élevé")
        void testRetrieveLevelFive_HighXp() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 10000;

            assertEquals(5, p.retrieveLevel());
        }
    }

    @Nested
    class GetXp {
        @Test
        @DisplayName("getXp retourne la valeur correcte")
        void testGetXp() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);
            p.xp = 50;

            assertEquals(50, p.getXp());
        }

        @Test
        @DisplayName("getXp d'un nouveau joueur")
        void testGetXpZero() {
            Player p = new Player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            assertEquals(0, p.getXp());
        }
    }
}
