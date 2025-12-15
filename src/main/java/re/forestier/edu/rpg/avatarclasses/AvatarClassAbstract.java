package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public abstract class AvatarClassAbstract {

    protected final HashMap<Integer, HashMap<String, Integer>> abilitiesByLevel;

    protected AvatarClassAbstract() {
        this.abilitiesByLevel = initializeAbilities();
    }

    public HashMap<String, Integer> getAbilitiesByLevel(int level) {
        return new HashMap<>(abilitiesByLevel.getOrDefault(level, new HashMap<>()));
    }

    protected abstract HashMap<Integer, HashMap<String, Integer>> initializeAbilities();

    /**
     * Helper method to facilitate abilities initialization
     */
    protected HashMap<String, Integer> createAbilities(Object... pairs) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < pairs.length; i += 2) {
            map.put((String) pairs[i], (Integer) pairs[i + 1]);
        }
        return map;
    }

    public abstract int calculateHealthRegeneration(Player player);
}
