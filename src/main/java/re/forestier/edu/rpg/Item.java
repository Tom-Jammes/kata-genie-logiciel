package re.forestier.edu.rpg;

public enum Item {
    ;

    private final String name;
    private final String description;
    private final int value;
    private final double weight;

    Item(String name, String description, int value, double weight) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }
}
