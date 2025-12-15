package re.forestier.edu.rpg;

public class AlreadyInInventoryException extends RuntimeException {
    public AlreadyInInventoryException(String message) {
        super(message);
    }
}
