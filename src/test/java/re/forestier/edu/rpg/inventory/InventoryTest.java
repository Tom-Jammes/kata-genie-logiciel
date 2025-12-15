package re.forestier.edu.rpg.inventory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Nested
    class Constructor {
        @Test
        @DisplayName("On peut créer un inventaire avec un poids max correct")
        void mustCreateAnInventoryWithAValidMaxWeight() {
            Inventory inventory = new Inventory(5);

            assertNotNull(inventory);
            assertEquals(5, inventory.getMaximumWeight(), "Maximum weight must be equal to 5");
        }

        @Test
        @DisplayName("On ne peut pas créer un inventaire avec un poids max incorrect")
        void cannotCreateAnInventoryWithAInvalidMaxWeight() {
            assertThrows(IllegalArgumentException.class, () -> new Inventory(0));
            assertThrows(IllegalArgumentException.class, () -> new Inventory(-5));
        }

        @Test
        @DisplayName("L'inventaire est vide à l'initialisation")
        void mustBeEmptyWhenCreation() {
            Inventory inventory = new Inventory(5);

            assertEquals(0, inventory.size(), "Size must be equal to 0");
        }

        @Test
        @DisplayName("Le poids doit être égal à 0 à l'initialisation")
        void mustWeightBe0WhenCreation() {
            Inventory inventory = new Inventory(5);

            assertEquals(0, inventory.getWeight(), "Weight must be equal to 0");
        }
    }

    @Nested
    class Weight {
        @Test
        @DisplayName("Le poids augmente lorsqu'on ajoute un item")
        void mustWeightIncreaseWhenItemAdded() {
            Inventory inventory = new Inventory(5);

            inventory.addItem(Item.MAGIC_BOW);
            assertEquals(Item.MAGIC_BOW.getWeight(), inventory.getWeight(), "Weight must be equal to Magic Bow weight");

            inventory.addItem(Item.HOLY_ELIXIR);
            double predictedWeight = Item.MAGIC_BOW.getWeight() + Item.HOLY_ELIXIR.getWeight();
            assertEquals(predictedWeight, inventory.getWeight(), "Weight must be equal to Magic Bow + Holy Elixir weight");
        }

        @Test
        @DisplayName("Le poids diminue lorsqu'on retire un item")
        void mustWeightDecreaseWhenItemRemoved() {
            Inventory inventory = new Inventory(5);

            inventory.addItem(Item.MAGIC_BOW);
            inventory.addItem(Item.HOLY_ELIXIR);
            double predictedWeight = Item.MAGIC_BOW.getWeight() + Item.HOLY_ELIXIR.getWeight();
            assertEquals(predictedWeight, inventory.getWeight(), "Weight must be equal to Magic Bow + Holy Elixir weight");

            inventory.removedItem(Item.MAGIC_BOW);
            assertEquals(Item.HOLY_ELIXIR.getWeight(), inventory.getWeight(), "Weight must be equal to Holy Elixir weight");
        }

        @Test
        @DisplayName("Impossible d'ajouter un item si fait dépasser le poids max")
        void cannotAddItemIfMaxWeightReached() {
            Inventory inventory = new Inventory(2);

            inventory.addItem(Item.MAGIC_BOW);
            assertEquals(inventory.getMaximumWeight(), inventory.getWeight(), "Weight must be equal to max inventory weight");

            assertThrows(MaximumWeightReachedException.class, () -> inventory.addItem(Item.HOLY_ELIXIR));
        }
    }

    @Nested
    class Items {
        @Test
        @DisplayName("Lorsqu'un item est ajouté il est bien présent dans l'inventaire")
        void mustItemBeInInventoryWhenAdded() {
            Inventory inventory = new Inventory(5);

            assertFalse(inventory.contains(Item.MAGIC_BOW));
            inventory.addItem(Item.MAGIC_BOW);
            assertTrue(inventory.contains(Item.MAGIC_BOW));
        }

        @Test
        @DisplayName("Lorsqu'un item est retiré il n'est plus présent dans l'inventaire")
        void mustItemNotBeInInventoryWhenRemoved() {
            Inventory inventory = new Inventory(5);

            assertFalse(inventory.contains(Item.MAGIC_BOW));
            inventory.addItem(Item.MAGIC_BOW);
            assertTrue(inventory.contains(Item.MAGIC_BOW));
            inventory.removedItem(Item.MAGIC_BOW);
            assertFalse(inventory.contains(Item.MAGIC_BOW));
        }

        @Test
        @DisplayName("Lorsqu'on clear l'inventaire celui ci est vide")
        void mustClearRemoveAllItem() {
            Inventory inventory = new Inventory(5);

            inventory.addItem(Item.MAGIC_BOW);
            inventory.addItem(Item.HOLY_ELIXIR);
            assertEquals(2, inventory.size());

            inventory.clear();
            assertEquals(0, inventory.size());
        }

        @Test
        @DisplayName("Impossible d'ajouter un item déjà dans l'inventaire")
        void cannotAddItemAlreadyInInventory() {
            Inventory inventory = new Inventory(5);

            inventory.addItem(Item.MAGIC_BOW);
            assertTrue(inventory.contains(Item.MAGIC_BOW));

            assertThrows(AlreadyInInventoryException.class, () -> inventory.addItem(Item.MAGIC_BOW));
        }

        @Test
        @DisplayName("Impossible de retirer un item qui n'est pas dans l'inventaire")
        void cannotRemoveItemNotInInventory() {
            Inventory inventory = new Inventory(5);

            assertThrows(ItemNotInInventoryException.class, () -> inventory.removedItem(Item.MAGIC_BOW));
        }
    }

    @Nested
    class SellItem {
        @Test
        @DisplayName("Retour de la valeur de l'item lorsque celui est vendu")
        void mustReturnTheItemValueWhenItsSold() {
            Inventory inventory = new Inventory(5);

            inventory.addItem(Item.MAGIC_BOW);
            assertEquals(Item.MAGIC_BOW.getValue(), inventory.sell(Item.MAGIC_BOW));
        }

        @Test
        @DisplayName("Item retiré de l'inventaire lorsque celui-ci est vendu")
        void mustRemovedSoldItem() {
            Inventory inventory = new Inventory(5);

            inventory.addItem(Item.MAGIC_BOW);
            assertEquals(1, inventory.size());

            inventory.sell(Item.MAGIC_BOW);
            assertEquals(0, inventory.size());
        }

        @Test
        @DisplayName("Impossible de vendre un item qui n'est pas dans l'inventaire")
        void cannotSellItemNotInInventory() {
            Inventory inventory = new Inventory(5);

            assertThrows(ItemNotInInventoryException.class, () -> inventory.removedItem(Item.MAGIC_BOW));
        }

        @Test
        @DisplayName("Possibilité de vendre tous les items d'un coup")
        void canSellAllItems() {
            Inventory inventory = new Inventory(5);

            inventory.addItem(Item.MAGIC_BOW);
            inventory.addItem(Item.HOLY_ELIXIR);
            assertEquals(2, inventory.size());

            int expectedValue = Item.MAGIC_BOW.getValue() + Item.HOLY_ELIXIR.getValue();
            assertEquals(expectedValue, inventory.sellAll());
            assertEquals(0, inventory.size());
        }
    }
}