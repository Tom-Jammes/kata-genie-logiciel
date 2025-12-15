package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public class Archer extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        int regen = 1;
        if(player.getInventory().contains("Magic Bow")) {
           regen += player.getCurrentHP()/8-1;
        }
        return regen;
    }

    @Override
    protected HashMap<Integer, HashMap<String, Integer>> initializeAbilities() {
        HashMap<Integer, HashMap<String, Integer>> abilities = new HashMap<>();

        abilities.put(1, createAbilities("INT", 1, "ATK", 3, "CHA", 1, "VIS", 3));
        abilities.put(2, createAbilities("DEF", 1, "CHA", 2));
        abilities.put(3, createAbilities("ATK", 3));
        abilities.put(4, createAbilities("DEF", 2));
        abilities.put(5, createAbilities("ATK", 4));

        return abilities;
    }
}
