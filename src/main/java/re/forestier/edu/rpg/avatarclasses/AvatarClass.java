package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public enum AvatarClass {
    ARCHER(new Archer()),
    ADVENTURER(new Adventurer()),
    DWARF(new Dwarf());

    private final AvatarClassAbstract avatarClass;

    AvatarClass(AvatarClassAbstract avatarClass) {
        this.avatarClass = avatarClass;
    }

    public static AvatarClass fromString(String className) {
        try {
            return valueOf(className);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public int calculateHealthRegeneration(Player p) {
        return avatarClass.calculateHealthRegeneration(p);
    }

    public HashMap<String, Integer> getAbilitiesByLevel(int level) {return avatarClass.getAbilitiesByLevel(level);}
}
