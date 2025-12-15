package re.forestier.edu.rpg.inventory;

public enum Item {
    LOOKOUT_RING(
            "Lookout Ring",
            "Prevents surprise attacks",
            50,
            0.1
    ),

    SCROLL_OF_STUPIDITY(
            "Scroll of Stupidity",
            "INT-2 when applied to an enemy",
            30,
            0.05
    ),

    DRAUPNIR(
            "Draupnir",
            "Increases XP gained by 100%",
            200,
            0.3
    ),

    MAGIC_CHARM(
            "Magic Charm",
            "Magic +10 for 5 rounds",
            75,
            0.1
    ),

    RUNE_STAFF_OF_CURSE(
            "Rune Staff of Curse",
            "May burn your enemies... Or yourself. Who knows?",
            150,
            2.5
    ),

    COMBAT_EDGE(
            "Combat Edge",
            "Well, that's an edge",
            40,
            1.0
    ),

    HOLY_ELIXIR(
            "Holy Elixir",
            "Recover your HP",
            100,
            0.5
    ),

    MAGIC_BOW(
            "Magic Bow",
            "A mystical bow with arcane power",
            500,
            2.0
    );

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
