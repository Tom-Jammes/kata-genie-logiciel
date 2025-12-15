package re.forestier.edu.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.avatarclasses.AvatarClass;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private ArrayList<String> notEmptyInventory;

    private Player archer;
    private Player adventurer;
    private Player dwarf;

    @BeforeEach
    void setUp() {
        notEmptyInventory = new ArrayList<>();
        notEmptyInventory.add("Sword");
        notEmptyInventory.add("Potion");

        archer = new Player("John", "Robin", AvatarClass.ARCHER, 100,100, new ArrayList<>());
        adventurer = new Player("Alice", "Lara", AvatarClass.ADVENTURER, 100,100, new ArrayList<>());
        dwarf = new Player("Bob", "Gimli", AvatarClass.DWARF, 100,100, notEmptyInventory);
    }

    @Nested
    class Constructor {
        @Test
        @DisplayName("Constructeur avec classe valide")
        void testConstructorWithValidArcherClass() {
            Player archerPlayer = new Player("John", "Robin", AvatarClass.ARCHER, 100, 100, notEmptyInventory);

            assertNotNull(archerPlayer);
            assertEquals("John", archerPlayer.getPlayerName());
            assertEquals("Robin", archerPlayer.getAvatarName());
            assertEquals(AvatarClass.ARCHER, archerPlayer.getAvatarClass());
            assertEquals(100, archerPlayer.getMoney());
            assertEquals(notEmptyInventory, archerPlayer.getInventory());
            assertNotNull(archerPlayer.getAbilities());

            Player adventurerPlayer = new Player("Alice", "Lara", AvatarClass.ADVENTURER, 100, 200, notEmptyInventory);

            assertNotNull(adventurerPlayer);
            assertEquals("Alice", adventurerPlayer.getPlayerName());
            assertEquals("Lara", adventurerPlayer.getAvatarName());
            assertEquals(AvatarClass.ADVENTURER, adventurerPlayer.getAvatarClass());
            assertEquals(200, adventurerPlayer.getMoney());
        }

        @Test
        @DisplayName("Impossible to create a player with an invalid avatarClass")
        void mustNotCreateAPlayerWithAnInvalidAvatarClass() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Player("Alice", "Alice the skeleton", AvatarClass.valueOf("InvalidAvatarClass"), 100, 100, notEmptyInventory);
            });
        }

        @Test
        @DisplayName("Constructeur avec argent à zéro")
        void testConstructorWithZeroMoney() {
            Player p = new Player("Poor", "Avatar", AvatarClass.ARCHER, 100, 0, notEmptyInventory);

            assertEquals(0, p.getMoney());
        }

        @Test
        @DisplayName("Constructeur avec inventaire vide")
        void testConstructorWithEmptyInventory() {
            assertNotNull(archer.getInventory());
            assertEquals(0, archer.getInventory().size());
        }

        @Test
        @DisplayName("Constructeur avec inventaire non vide")
        void testConstructorWithNotEmptyInventory() {
            assertNotNull(dwarf.getInventory());
            assertEquals(2, dwarf.getInventory().size());
            assertEquals("Sword", dwarf.getInventory().get(0));
            assertEquals("Potion", dwarf.getInventory().get(1));
        }
    }

    @Nested
    class Money {
        @Test
        @DisplayName("removeMoney - retrait valide")
        void testRemoveMoneyValid() {
            archer.removeMoney(50);
            assertEquals(50, archer.getMoney());
        }

        @Test
        @DisplayName("removeMoney - retrait total")
        void testRemoveMoneyAll() {
            archer.removeMoney(100);
            assertEquals(0, archer.getMoney());
        }

        @Test
        @DisplayName("removeMoney - montant négatif provoque une exception")
        void testRemoveMoneyNegativeResult() {
            Player p = new Player("John", "Avatar", AvatarClass.ARCHER, 100, 50, notEmptyInventory);

            try {
                p.removeMoney(100);
                fail();
            } catch (IllegalArgumentException e) {
                assertEquals("Player can't have a negative money!", e.getMessage());
                assertEquals(50, p.getMoney()); // L'argent ne doit pas avoir changé
            }
        }

        @Test
        @DisplayName("removeMoney - retrait de zéro")
        void testRemoveMoneyZero() {
            archer.removeMoney(0);
            assertEquals(100, archer.getMoney());
        }

        @Test
        @DisplayName("addMoney - ajout valide")
        void testAddMoneyValid() {
            archer.addMoney(50);
            assertEquals(150, archer.getMoney());
        }

        @Test
        @DisplayName("addMoney - ajout de zéro")
        void testAddMoneyZero() {
            archer.addMoney(0);
            assertEquals(100, archer.getMoney());
        }

        @Test
        @DisplayName("addMoney - multiple ajouts successifs")
        void testAddMoneyMultipleTimes() {
            archer.addMoney(25);
            archer.addMoney(25);
            archer.addMoney(50);

            assertEquals(200, archer.getMoney());
        }
    }

    @Nested
    class RetrieveLvl {
        @Test
        @DisplayName("retrieveLevel - niveau 1 avec XP = 0")
        void testRetrieveLevelOne_XpZero() {
            assertEquals(1, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 1 avec XP = 9")
        void testRetrieveLevelOne_XpNine() {
            archer.addXp(9);
            assertEquals(1, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 2 avec XP = 10")
        void testRetrieveLevelTwo_XpTen() {
            archer.addXp(10);
            assertEquals(2, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 2 avec XP = 26")
        void testRetrieveLevelTwo_XpTwentySix() {
            archer.addXp(26);
            assertEquals(2, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 3 avec XP = 27")
        void testRetrieveLevelThree_XpTwentySeven() {
            archer.addXp(27);
            assertEquals(3, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 3 avec XP = 56")
        void testRetrieveLevelThree_XpFiftySix() {
            archer.addXp(56);
            assertEquals(3, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 4 avec XP = 57")
        void testRetrieveLevelFour_XpFiftySeven() {
            archer.addXp(57);
            assertEquals(4, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 4 avec XP = 110")
        void testRetrieveLevelFour_XpOneHundredTen() {
            archer.addXp(110);
            assertEquals(4, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 5 avec XP = 111")
        void testRetrieveLevelFive_XpOneHundredEleven() {
            archer.addXp(111);
            assertEquals(5, archer.retrieveLevel());
        }

        @Test
        @DisplayName("retrieveLevel - niveau 5 avec XP très élevé")
        void testRetrieveLevelFive_HighXp() {
            archer.addXp(10000);
            assertEquals(5, archer.retrieveLevel());
        }
    }

    @Nested
    class Xp {
        @Test
        @DisplayName("getXp retourne la valeur correcte")
        void testGetXp() {
            archer.addXp(50);

            assertEquals(50, archer.getXp());
        }

        @Test
        @DisplayName("getXp d'un nouveau joueur")
        void testGetXpZero() {
            assertEquals(0, archer.getXp());
        }
        @Test
        @DisplayName("addXp - ajout d'XP sans changement de niveau")
        void testAddXpMultipleTimesWithoutLevelUp() {
            archer.addXp(2);
            assertEquals(2, archer.getXp());

            archer.addXp(3);
            assertEquals(5, archer.getXp());

            assertEquals(1, archer.retrieveLevel());
        }

        @Test
        @DisplayName("addXp - ajout de 0 XP")
        void testAddXpZero() {
            archer.addXp(5);

            boolean leveledUp = archer.addXp(0);

            assertFalse(leveledUp);
            assertEquals(5, archer.getXp());
        }

        @Test
        @DisplayName("addXp - ajout d'XP avec level up")
        void testAddXpLevelUpFrom1To2() {
            boolean leveledUp = archer.addXp(10);

            assertTrue(leveledUp);
            assertEquals(10, archer.getXp());
            assertEquals(2, archer.retrieveLevel());
        }
    }

    @Nested
    class BehaviorLevelUp {

        @Test
        @DisplayName("LevelUp - ajout d'un objet aléatoire lors du level up")
        void testAddsRandomObjectOnLevelUp() {
            archer.getInventory().clear();

            archer.addXp(10);

            assertEquals(1, archer.getInventory().size());
            assertNotNull(archer.getInventory().getFirst());
        }

        @Test
        @DisplayName("LevelUp - mise à jour des niveaux des capacités lors du level up")
        void testUpdatesAbilitiesLevelWhenLevelUp() {
            assertEquals(1, archer.retrieveLevel());
            assertEquals(1, archer.getAbilities().get("CHA"));
            assertNull(archer.getAbilities().get("DEF"));

            archer.addXp(10);

            assertEquals(2, archer.retrieveLevel());
            assertEquals(2, archer.getAbilities().get("CHA"));
            assertEquals(1, archer.getAbilities().get("DEF"));
        }

        @Test
        @DisplayName("LevelUp - Player garde les capacités du niveau précédent quand levelUp")
        void testKeepPreviousAbilitiesWhenLevelUp() {
            assertEquals(1, archer.retrieveLevel());
            assertEquals(1, archer.getAbilities().get("INT"));
            assertEquals(3, archer.getAbilities().get("ATK"));
            assertEquals(3, archer.getAbilities().get("VIS"));

            archer.addXp(10);

            assertEquals(2, archer.retrieveLevel());
            assertEquals(1, archer.getAbilities().get("INT"));
            assertEquals(3, archer.getAbilities().get("ATK"));
            assertEquals(3, archer.getAbilities().get("VIS"));
        }
    }

    @Nested
    class UpdateEndOfRound {
        @Test
        @DisplayName("UpdateEndOfRound - joueur KO (HP = 0)")
        void testUpdateEndOfRoundPlayerKO() {
            archer.setCurrentHP(0);

            archer.updateEndOfRound();

            // Le joueur reste à 0 HP
            assertEquals(0, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP < 50% sans Magic Bow")
        void testUpdateEndOfRoundArcherLowHealthNoMagicBow() {
            archer.setCurrentHP(40);

            archer.updateEndOfRound();

            assertEquals(41, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP < 50% avec Magic Bow")
        void testUpdateEndOfRoundArcherLowHealthWithMagicBow() {
            archer.setCurrentHP(40);
            archer.addObjectInventory("Magic Bow");

            archer.updateEndOfRound();

            // 40 + 1 = 41, puis 41 + (41/8 - 1) = 41 + 4 = 45
            assertEquals(45, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - DWARF avec HP < 50% sans Holy Elixir")
        void testUpdateEndOfRoundDwarfLowHealthNoElixir() {
            dwarf.setCurrentHP(40);

            dwarf.updateEndOfRound();

            assertEquals(41, dwarf.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - DWARF avec HP < 50% avec Holy Elixir")
        void testUpdateEndOfRoundDwarfLowHealthWithElixir() {
            dwarf.setCurrentHP(40);
            dwarf.addObjectInventory("Holy Elixir");

            dwarf.updateEndOfRound();

            // 40 + 1 (elixir) + 1 (dwarf) = 42
            assertEquals(42, dwarf.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ADVENTURER avec HP < 50% et niveau < 3")
        void testMajFinDeTourAdventurerLowHealthLowLevel() {
            adventurer.setCurrentHP(40);

            adventurer.updateEndOfRound();

            // 40 + 2 - 1 = 41
            assertEquals(41, adventurer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ADVENTURER avec HP < 50% et niveau >= 3")
        void testUpdateEndOfRoundAdventurerLowHealthHighLevel() {
            adventurer.setCurrentHP(40);
            adventurer.addXp(27); // niveau 3

            adventurer.updateEndOfRound();

            // 40 + 2 = 42
            assertEquals(42, adventurer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - joueur avec HP >= 50% et < max")
        void testUpdateEndOfRoundPlayerHealthAboveHalf() {
            archer.setCurrentHP(60);

            archer.updateEndOfRound();

            // Pas de changement dans cette condition
            assertEquals(60, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - joueur avec HP = max")
        void testUpdateEndOfRoundPlayerFullHealth() {
            archer.setCurrentHP(100);

            archer.updateEndOfRound();

            assertEquals(100, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP exactement à 50%")
        void testUpdateEndOfRoundArcherExactlyHalfHealth() {
            archer.setCurrentHP(50);

            archer.updateEndOfRound();

            // HP >= healthpoints/2, donc pas de soin
            assertEquals(50, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP supérieurs à 100%")
        void testMajFinDeTourArcherTooHealed() {
            archer.setCurrentHP(150);

            archer.updateEndOfRound();

            // HP >= healthpoints, donc pas remise à 100%
            assertEquals(100, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP juste en dessous de 50%")
        void testUpdateEndOfRoundArcherJustBelowHalfHealth() {
            archer.setCurrentHP(49);

            archer.updateEndOfRound();

            // HP < healthpoints/2, donc soin
            assertEquals(50, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - GOBLIN avec HP en dessous de 50%")
        void testUpdateEndOfRoundGoblinBelowHalfHealth() {
            Player goblin = new Player("Martin", "Garnuff", AvatarClass.GOBLIN, 100, 50, new ArrayList<>());
            goblin.setCurrentHP(40);

            goblin.updateEndOfRound();

            // HP < healthpoints/2, donc soin
            assertEquals(41, goblin.getCurrentHP());
        }
    }
}
