package re.forestier.edu.rpg;

public class ItemNotInInventoryException extends RuntimeException {
    public ItemNotInInventoryException(String message) {
        super(message);
    }
}
