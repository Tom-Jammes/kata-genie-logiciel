package re.forestier.edu.rpg.inventory;

public class ItemNotInInventoryException extends RuntimeException {
    public ItemNotInInventoryException(String message) {
        super(message);
    }
}
