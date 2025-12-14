package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public abstract class AvatarClassAbstract {

    protected final HashMap<Integer, HashMap<String, Integer>> abilitiesByLevel;

    protected AvatarClassAbstract() {
        this.abilitiesByLevel = initializeAbilities();
    }

    protected abstract HashMap<Integer, HashMap<String, Integer>> initializeAbilities();

    public abstract int calculateHealthRegeneration(Player player);


    public HashMap<String, Integer> getAbilitiesByLevel(int level) {
        return new HashMap<>(abilitiesByLevel.getOrDefault(level, new HashMap<>()));
    }
}
