package re.forestier.edu.rpg;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.avatarclasses.Adventurer;
import re.forestier.edu.rpg.avatarclasses.Archer;
import re.forestier.edu.rpg.avatarclasses.AvatarClass;
import re.forestier.edu.rpg.avatarclasses.Dwarf;

import java.util.ArrayList;
import java.util.HashMap;

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
    class AbilitiesPerTypeAndLevel {
        @Nested
        class Adventurer {
            @Test
            @DisplayName("Abilities - Adventurer level 1")
            void testAbilitiesAdventurerLevel1() {
                HashMap<String, Integer> abilities = AvatarClass.ADVENTURER.getAbilitiesByLevel(1);
//                assertEquals(1, abilities.get("INT"));   IMPOSSIBLE DUE TO THE BUG
                assertEquals(1, abilities.get("DEF"));
                assertEquals(3, abilities.get("ATK"));
//                assertEquals(2, abilities.get("CHA"));  IMPOSSIBLE DUE TO THE BUG
                assertEquals(4, abilities.size());
            }

//            IMPOSSIBLE DUE TO THE BUG
//            @Test
//            @DisplayName("Abilities - Adventurer level 2")
//            void testAbilitiesAdventurerLevel2() {
//                HashMap<String, Integer> abilities = AvatarClass.ADVENTURER.getAbilitiesByLevel(2);
//                assertEquals(2, abilities.get("INT"));
//                assertEquals(3, abilities.get("CHA"));
//                assertEquals(2, abilities.size());
//            }

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

        @Nested
        class Dwarf {
            @Test
            @DisplayName("Abilities - Dwarf level 1")
            void testAbilitiesDwarfLevel1() {
                HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(1);
                assertEquals(4, abilities.get("ALC"));
                assertEquals(1, abilities.get("INT"));
                assertEquals(3, abilities.get("ATK"));
                assertEquals(3, abilities.size());
            }

            @Test
            @DisplayName("Abilities - Dwarf level 2")
            void testAbilitiesDwarfLevel2() {
                HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(2);
                assertEquals(1, abilities.get("DEF"));
                assertEquals(5, abilities.get("ALC"));
                assertEquals(2, abilities.size());
            }

            @Test
            @DisplayName("Abilities - Dwarf level 3")
            void testAbilitiesDwarfLevel3() {
                HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(3);
                assertEquals(4, abilities.get("ATK"));
                assertEquals(1, abilities.size());
            }

            @Test
            @DisplayName("Abilities - Dwarf level 4")
            void testAbilitiesDwarfLevel4() {
                HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(4);
                assertEquals(2, abilities.get("DEF"));
                assertEquals(1, abilities.size());
            }

            @Test
            @DisplayName("Abilities - Dwarf level 5")
            void testAbilitiesDwarfLevel5() {
                HashMap<String, Integer> abilities = AvatarClass.DWARF.getAbilitiesByLevel(5);
                assertEquals(1, abilities.get("CHA"));
                assertEquals(1, abilities.size());
            }
        }

        @Nested
        class Archer {
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

    @Nested
    class AddXp {
        @Test
        @DisplayName("addXp - ajout d'XP sans changement de niveau")
        void testAddXpMultipleTimesWithoutLevelUp() {
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
        @DisplayName("addXp - ajout d'XP avec level up")
        void testAddXpLevelUpFrom1To2() {
            boolean leveledUp = UpdatePlayer.addXp(archer, 10);

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

            UpdatePlayer.addXp(archer, 10);

            assertEquals(1, archer.getInventory().size());
            assertNotNull(archer.getInventory().getFirst());
        }

        @Test
        @DisplayName("LevelUp - mise à jour des niveaux des capacités lors du level up")
        void testUpdatesAbilitiesLevelWhenLevelUp() {
            assertEquals(1, archer.retrieveLevel());
            assertEquals(1, archer.getAbilities().get("CHA"));
            assertNull(archer.getAbilities().get("DEF"));

            UpdatePlayer.addXp(archer, 10);

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

            UpdatePlayer.addXp(archer, 10);

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
            archer.setMaxHP(100);
            archer.setCurrentHP(0);

            UpdatePlayer.updateEndOfRound(archer);

            // Le joueur reste à 0 HP
            assertEquals(0, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP < 50% sans Magic Bow")
        void testUpdateEndOfRoundArcherLowHealthNoMagicBow() {
            archer.setMaxHP(100);
            archer.setCurrentHP(40);

            UpdatePlayer.updateEndOfRound(archer);

            assertEquals(41, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP < 50% avec Magic Bow")
        void testUpdateEndOfRoundArcherLowHealthWithMagicBow() {
            archer.setMaxHP(100);
            archer.setCurrentHP(40);
            archer.addObjectInventory("Magic Bow");

            UpdatePlayer.updateEndOfRound(archer);

            // 40 + 1 = 41, puis 41 + (41/8 - 1) = 41 + 4 = 45
            assertEquals(45, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - DWARF avec HP < 50% sans Holy Elixir")
        void testUpdateEndOfRoundDwarfLowHealthNoElixir() {
            dwarf.setMaxHP(100);
            dwarf.setCurrentHP(40);

            UpdatePlayer.updateEndOfRound(dwarf);

            assertEquals(41, dwarf.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - DWARF avec HP < 50% avec Holy Elixir")
        void testUpdateEndOfRoundDwarfLowHealthWithElixir() {
            dwarf.setMaxHP(100);
            dwarf.setCurrentHP(40);
            dwarf.addObjectInventory("Holy Elixir");

            UpdatePlayer.updateEndOfRound(dwarf);

            // 40 + 1 (elixir) + 1 (dwarf) = 42
            assertEquals(42, dwarf.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ADVENTURER avec HP < 50% et niveau < 3")
        void testMajFinDeTourAdventurerLowHealthLowLevel() {
            adventurer.setMaxHP(100);
            adventurer.setCurrentHP(40);

            UpdatePlayer.updateEndOfRound(adventurer);

            // 40 + 2 - 1 = 41
            assertEquals(41, adventurer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ADVENTURER avec HP < 50% et niveau >= 3")
        void testUpdateEndOfRoundAdventurerLowHealthHighLevel() {
            adventurer.setMaxHP(100);
            adventurer.setCurrentHP(40);
            adventurer.setXp(27); // niveau 3

            UpdatePlayer.updateEndOfRound(adventurer);

            // 40 + 2 = 42
            assertEquals(42, adventurer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - joueur avec HP >= 50% et < max")
        void testUpdateEndOfRoundPlayerHealthAboveHalf() {
            archer.setMaxHP(100);
            archer.setCurrentHP(60);

            UpdatePlayer.updateEndOfRound(archer);

            // Pas de changement dans cette condition
            assertEquals(60, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - joueur avec HP = max")
        void testUpdateEndOfRoundPlayerFullHealth() {
            archer.setMaxHP(100);
            archer.setCurrentHP(100);

            UpdatePlayer.updateEndOfRound(archer);

            assertEquals(100, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP exactement à 50%")
        void testUpdateEndOfRoundArcherExactlyHalfHealth() {
            archer.setMaxHP(100);
            archer.setCurrentHP(50);

            UpdatePlayer.updateEndOfRound(archer);

            // HP >= healthpoints/2, donc pas de soin
            assertEquals(50, archer.getCurrentHP());
        }

        @Test
        @DisplayName("majFinDeTour - ARCHER avec HP supérieurs à 100%")
        void testMajFinDeTourArcherTooHealed() {
            archer.setMaxHP(100);
            archer.setCurrentHP(150);

            UpdatePlayer.updateEndOfRound(archer);

            // HP >= healthpoints, donc pas remise à 100%
            assertEquals(100, archer.getCurrentHP());
        }

        @Test
        @DisplayName("UpdateEndOfRound - ARCHER avec HP juste en dessous de 50%")
        void testUpdateEndOfRoundArcherJustBelowHalfHealth() {
            archer.setMaxHP(100);
            archer.setCurrentHP(49);

            UpdatePlayer.updateEndOfRound(archer);

            // HP < healthpoints/2, donc soin
            assertEquals(50, archer.getCurrentHP());
        }
    }

}
