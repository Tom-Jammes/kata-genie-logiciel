package re.forestier.edu.rpg;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Validation globale de tous les items")
    void testAllItemsAreValid() {
        for (Item item : Item.values()) {
            assertNotNull(item.getName());
            assertFalse(item.getName().isBlank(), "Item " + item + " has a blank name");

            assertFalse(item.getDescription().isBlank(), "Item " + item + " has a blank description");

            assertTrue(item.getValue() >= 0, "Item " + item + " has a negative value");
            assertTrue(item.getWeight() >= 0,  "Item " + item + " has a negative weight");
        }
    }

    @Test
    @DisplayName("Noms uniques")
    void testUniqueNames() {
        Set<String> names = new HashSet<>();
        for (Item item : Item.values()) {
            assertTrue(names.add(item.getName()),
                    "Duplicate name: " + item.getName());
        }
    }
}