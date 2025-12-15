package re.forestier.edu.rpg;

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

        }

        @Test
        @DisplayName("On ne peut pas créer un inventaire avec un poids max incorrect")
        void cannotCreateAnInventoryWithAInvalidMaxWeight() {

        }

        @Test
        @DisplayName("L'inventaire est vide à l'initialisation")
        void mustBeEmptyWhenCreation() {

        }

        @Test
        @DisplayName("Le poids doit être égal à 0 à l'initialisation")
        void mustWeightBe0WhenCreation() {

        }
    }

    @Nested
    class Weight {
        @Test
        @DisplayName("Le poids augmente lorsqu'on ajoute un item")
        void mustWeightIncreaseWhenItemAdded() {

        }

        @Test
        @DisplayName("Le poids diminue lorsqu'on retire un item")
        void mustWeightDecreaseWhenItemRemoved() {

        }

        @Test
        @DisplayName("Impossible d'ajouter un item si fait dépasser le poids max")
        void cannotAddItemIfMaxWeightReached() {

        }
    }

    @Nested
    class Items {
        @Test
        @DisplayName("Lorsqu'un item est ajouté il est bien présent dans l'inventaire")
        void mustItemBeInInventoryWhenAdded() {

        }

        @Test
        @DisplayName("Lorsqu'un item est retiré il n'est plus présent dans l'inventaire")
        void mustItemNotBeInInventoryWhenRemoved() {

        }

        @Test
        @DisplayName("Impossible d'ajouter un item déjà dans l'inventaire")
        void cannotAddItemAlreadyInInventory() {

        }

        @Test
        @DisplayName("Impossible de retirer un item qui n'est pas dans l'inventaire")
        void cannotRemoveItemNotInInventory() {

        }
    }

    @Nested
    class SellItem {
        @Test
        @DisplayName("Retour de la valeur de l'item lorsque celui est vendu")
        void mustReturnTheItemValueWhenItsSold() {

        }

        @Test
        @DisplayName("Item retiré de l'inventaire lorsque celui-ci est vendu")
        void mustRemovedSoldItem() {

        }
    }
}