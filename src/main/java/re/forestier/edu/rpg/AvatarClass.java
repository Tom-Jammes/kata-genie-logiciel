package re.forestier.edu.rpg;

public enum AvatarClass {
    ARCHER,
    ADVENTURER,
    DWARF;

    public static AvatarClass fromString(String className) {
        try {
            return valueOf(className);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
