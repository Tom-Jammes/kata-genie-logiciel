package re.forestier.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.player;

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
        @DisplayName("Creation of a new player")
        void testPlayerName() {
            player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, notEmptyInventory);
            assertThat(p.playerName, is("Florian"));
            assertThat(p.Avatar_name, is("Grognak le barbare"));
            assertThat(p.getAvatarClass(), is("ADVENTURER"));
            assertThat(p.money, is(100));
            assertEquals(p.inventory,  notEmptyInventory);
            assertNotNull(p.abilities);
        }

        @Test
        @DisplayName("Impossible to create a player with an invalid avatarClass")
        void mustNotCreateAPlayerWithAnInvalidAvatarClass() {
            player p = new player("Alice", "Alice the skeleton", "InvalidAvatarClass", 100, notEmptyInventory);
            assertNull(p.playerName);
            assertNull(p.Avatar_name);
            assertNull(p.getAvatarClass());
            assertNull(p.money);
        }

        @Test
        @DisplayName("Constructeur avec argent à zéro")
        void testConstructorWithZeroMoney() {
            player p = new player("Poor", "Avatar", "ARCHER", 0, notEmptyInventory);

            assertEquals(0, p.money);
        }

        @Test
        @DisplayName("Constructeur avec inventaire vide")
        void testConstructorWithEmptyInventory() {
            player p = new player("John", "Avatar", "ARCHER", 100, emptyInventory);

            assertNotNull(p.inventory);
            assertEquals(0, p.inventory.size());
        }

        @Test
        @DisplayName("Constructeur avec inventaire non vide")
        void testConstructorWithNotEmptyInventory() {
            player p = new player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

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
            player p = new player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.removeMoney(50);

            assertEquals(50, p.money);
        }

        @Test
        @DisplayName("removeMoney - retrait total")
        void testRemoveMoneyAll() {
            player p = new player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.removeMoney(100);

            assertEquals(0, p.money);
        }

        @Test
        @DisplayName("removeMoney - montant négatif provoque une exception")
        void testRemoveMoneyNegativeResult() {
            player p = new player("John", "Avatar", "ARCHER", 50, notEmptyInventory);

            try {
                p.removeMoney(100);
            } catch (IllegalArgumentException e) {
                assertEquals("Player can't have a negative money!", e.getMessage());
                assertEquals(50, p.money); // L'argent ne doit pas avoir changé
            }
        }

        @Test
        @DisplayName("removeMoney - retrait de zéro")
        void testRemoveMoneyZero() {
            player p = new player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.removeMoney(0);

            assertEquals(100, p.money);
        }

        @Test
        @DisplayName("addMoney - ajout valide")
        void testAddMoneyValid() {
            player p = new player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.addMoney(50);

            assertEquals(150, p.money);
        }

        @Test
        @DisplayName("addMoney - ajout de zéro")
        void testAddMoneyZero() {
            player p = new player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.addMoney(0);

            assertEquals(100, p.money);
        }

        @Test
        @DisplayName("addMoney - multiple ajouts successifs")
        void testAddMoneyMultipleTimes() {
            player p = new player("John", "Avatar", "ARCHER", 100, notEmptyInventory);

            p.addMoney(25);
            p.addMoney(25);
            p.addMoney(50);

            assertEquals(200, p.money);
        }
    }
}
