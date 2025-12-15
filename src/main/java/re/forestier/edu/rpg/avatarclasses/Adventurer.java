package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public class Adventurer extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        int regen = 1;
        if(player.retrieveLevel() >= 3) {
            regen += 1;
        }
        return regen;
    }

    @Override
    protected HashMap<Integer, HashMap<String, Integer>> initializeAbilities() {
        HashMap<Integer, HashMap<String, Integer>> abilities = new HashMap<>();

        abilities.put(1, createAbilities("INT", 1, "DEF", 1, "ATK", 3, "CHA", 2));
        abilities.put(2, createAbilities("INT", 2, "CHA", 3));
        abilities.put(3, createAbilities("ATK", 5, "ALC", 1));
        abilities.put(4, createAbilities("DEF", 3));
        abilities.put(5, createAbilities("VIS", 1, "DEF", 4));

        return abilities;
    }

}
