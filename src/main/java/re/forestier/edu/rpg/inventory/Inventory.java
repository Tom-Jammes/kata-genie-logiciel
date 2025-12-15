package re.forestier.edu.rpg.inventory;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> items;
    private final double maximumWeight;

    public Inventory(double maximumWeight) {
        if (!isWeightValid(maximumWeight)) {
            throw new IllegalArgumentException("Maximum weight must be positive");
        }
        this.maximumWeight = maximumWeight;
        items = new ArrayList<>();
    }

    /* ============== BASIC GETTERS ============== */

    public double getMaximumWeight() {
        return maximumWeight;
    }

    public int size() {
        return items.size();
    }

    public double getWeight() {
        double weight = 0;
        for (Item item : items) {
            weight += item.getWeight();
        }
        return weight;
    }

    /* ============== ITEM MANAGEMENT ============== */

    public void addItem(Item item) {
        if (isMaxWeightWillBeExceeded(item)) {
            throw new MaximumWeightReachedException("Impossible to add " + item.getName() + "Maximum weight reached");
        }
        if (items.contains(item)) {
            throw new AlreadyInInventoryException("Item " + item.getName() + " already in inventory");
        }
        items.add(item);
    }

    public void removedItem(Item item) {
        if (!items.contains(item)) {
            throw new ItemNotInInventoryException("Item " + item.getName() + " not in inventory");
        }
        items.remove(item);
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

    public int sellItem(Item item) {
        int itemValue = item.getValue();
        removedItem(item);
        return itemValue;
    }

    /* ============== PRIVATE METHODS ============== */

    private boolean isWeightValid(double weight) {
        return weight > 0;
    }

    private boolean isMaxWeightWillBeExceeded(Item item) {
        return getWeight() + item.getWeight() > maximumWeight;
    }
}
