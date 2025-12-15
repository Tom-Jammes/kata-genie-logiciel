package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public enum AvatarClass {
    ARCHER(new Archer()),
    ADVENTURER(new Adventurer()),
    DWARF(new Dwarf()),
    GOBLIN(new Goblin());

    private final AvatarClassAbstract avatarClass;

    AvatarClass(AvatarClassAbstract avatarClass) {
        this.avatarClass = avatarClass;
    }

    public int calculateHealthRegeneration(Player p) {
        return avatarClass.calculateHealthRegeneration(p);
    }

    public HashMap<String, Integer> getAbilitiesByLevel(int level) {return avatarClass.getAbilitiesByLevel(level);}
}
