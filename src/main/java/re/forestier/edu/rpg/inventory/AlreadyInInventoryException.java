package re.forestier.edu.rpg.inventory;

public class AlreadyInInventoryException extends RuntimeException {
    public AlreadyInInventoryException(String message) {
        super(message);
    }
}
