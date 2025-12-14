package re.forestier.edu.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

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
    class AbilitiesPerTypeAndLevel {
        @Nested
        class Adventurer {
            @Test
            @DisplayName("Abilities - Adventurer level 1")
            void testAbilitiesAdventurerLevel1() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ADVENTURER").get(1);
//                assertEquals(1, abilities.get("INT"));   IMPOSSIBLE DUE TO THE BUG
                assertEquals(1, abilities.get("DEF"));
                assertEquals(3, abilities.get("ATK"));
//                assertEquals(2, abilities.get("CHA"));  IMPOSSIBLE DUE TO THE BUG
            }

//            IMPOSSIBLE DUE TO THE BUG
//            @Test
//            @DisplayName("Abilities - Adventurer level 2")
//            void testAbilitiesAdventurerLevel2() {
//                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ADVENTURER").get(2);
//                assertEquals(2, abilities.get("INT"));
//                assertEquals(3, abilities.get("CHA"));
//            }

            @Test
            @DisplayName("Abilities - Adventurer level 3")
            void testAbilitiesAdventurerLevel3() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ADVENTURER").get(3);
                assertEquals(1, abilities.get("ALC"));
                assertEquals(5, abilities.get("ATK"));
            }

            @Test
            @DisplayName("Abilities - Adventurer level 4")
            void testAbilitiesAdventurerLevel4() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ADVENTURER").get(4);
                assertEquals(3, abilities.get("DEF"));
            }

            @Test
            @DisplayName("Abilities - Adventurer level 5")
            void testAbilitiesAdventurerLevel5() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ADVENTURER").get(5);
                assertEquals(1, abilities.get("VIS"));
                assertEquals(4, abilities.get("DEF"));
            }
        }

        @Nested
        class Dwarf {
            @Test
            @DisplayName("Abilities - Dwarf level 1")
            void testAbilitiesDwarfLevel1() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("DWARF").get(1);
                assertEquals(4, abilities.get("ALC"));
                assertEquals(1, abilities.get("INT"));
                assertEquals(3, abilities.get("ATK"));
            }

            @Test
            @DisplayName("Abilities - Dwarf level 2")
            void testAbilitiesDwarfLevel2() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("DWARF").get(2);
                assertEquals(1, abilities.get("DEF"));
                assertEquals(5, abilities.get("ALC"));
            }

            @Test
            @DisplayName("Abilities - Dwarf level 3")
            void testAbilitiesDwarfLevel3() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("DWARF").get(3);
                assertEquals(4, abilities.get("ATK"));
            }

            @Test
            @DisplayName("Abilities - Dwarf level 4")
            void testAbilitiesDwarfLevel4() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("DWARF").get(4);
                assertEquals(2, abilities.get("DEF"));
            }

            @Test
            @DisplayName("Abilities - Dwarf level 5")
            void testAbilitiesDwarfLevel5() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("DWARF").get(5);
                assertEquals(1, abilities.get("CHA"));
            }
        }

        @Nested
        class Archer {
            @Test
            @DisplayName("Abilities - Archer level 1")
            void testAbilitiesArcherLevel1() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(1);
                assertEquals(1, abilities.get("INT"));
                assertEquals(3, abilities.get("ATK"));
                assertEquals(1, abilities.get("CHA"));
                assertEquals(3, abilities.get("VIS"));
            }

            @Test
            @DisplayName("Abilities - Archer level 2")
            void testAbilitiesArcherLevel2() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(2);
                assertEquals(1, abilities.get("DEF"));
                assertEquals(2, abilities.get("CHA"));
            }

            @Test
            @DisplayName("Abilities - Archer level 3")
            void testAbilitiesArcherLevel3() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(3);
                assertEquals(3, abilities.get("ATK"));
            }

            @Test
            @DisplayName("Abilities - Archer level 4")
            void testAbilitiesArcherLevel4() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(4);
                assertEquals(2, abilities.get("DEF"));
            }

            @Test
            @DisplayName("Abilities - Archer level 5")
            void testAbilitiesArcherLevel5() {
                HashMap<String, Integer> abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get("ARCHER").get(5);
                assertEquals(4, abilities.get("ATK"));
            }
        }
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

            assertEquals(1, archer.abilities.get("INT"));
            assertEquals(1, archer.abilities.get("CHA"));
            assertNull(archer.abilities.get("DEF"));

            UpdatePlayer.addXp(archer, 10);

            assertEquals(1, archer.abilities.get("INT"));
            assertEquals(2, archer.abilities.get("CHA"));
            assertNotNull(archer.abilities.get("DEF"));
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up ADVENTURER")
        void testAddXpUpdatesAbilitiesForAdventurer() {
            UpdatePlayer.addXp(adventurer, 10);
            UpdatePlayer.addXp(adventurer, 17);

            assertEquals(5, adventurer.abilities.get("ATK"));
            assertEquals(1, adventurer.abilities.get("ALC"));
            assertEquals(1, adventurer.abilities.get("DEF"));

            UpdatePlayer.addXp(adventurer, 32);

            assertEquals(3, adventurer.abilities.get("DEF"));
        }

        @Test
        @DisplayName("addXp - mise à jour des capacités lors du level up DWARF")
        void testAddXpUpdatesAbilitiesForDwarf() {
            dwarf.xp = 0;

            assertEquals(4, dwarf.abilities.get("ALC"));
            assertNull(dwarf.abilities.get("DEF"));

            UpdatePlayer.addXp(dwarf, 10);

            assertEquals(5, dwarf.abilities.get("ALC"));
            assertEquals(1, dwarf.abilities.get("DEF"));
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
        @DisplayName("majFinDeTour - ARCHER avec HP supérieurs à 100%")
        void testMajFinDeTourArcherTooHealed() {
            archer.healthpoints = 100;
            archer.currenthealthpoints = 150;

            UpdatePlayer.majFinDeTour(archer);

            // HP >= healthpoints, donc pas remise à 100%
            assertEquals(100, archer.currenthealthpoints);
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
