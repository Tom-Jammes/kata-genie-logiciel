package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public class Goblin extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        // No healing logic for now
        return 1;
    }

    @Override
    protected HashMap<Integer, HashMap<String, Integer>> initializeAbilities() {
        HashMap<Integer, HashMap<String, Integer>> abilities = new HashMap<>();

        abilities.put(1, createAbilities("INT", 2, "ATK", 2, "ALC", 1));
        abilities.put(2, createAbilities("ATK", 3, "ALC", 4));
        abilities.put(3, createAbilities("VIS", 1));
        abilities.put(4, createAbilities("DEF", 1));
        abilities.put(5, createAbilities("DEF", 2, "ATK", 4));

        return abilities;
    }
}
