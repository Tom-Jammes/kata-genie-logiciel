package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public class Dwarf extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        int regen = 1;
        if(player.getInventory().contains("Holy Elixir")) {
            regen += 1;
        }
        return regen;
    }

    @Override
    protected HashMap<Integer, HashMap<String, Integer>> initializeAbilities() {
        HashMap<Integer, HashMap<String, Integer>> abilities = new HashMap<>();

        abilities.put(1, abilities("ALC", 4, "INT", 1, "ATK", 3));
        abilities.put(2, abilities("DEF", 1, "ALC", 5));
        abilities.put(3, abilities("ATK", 4));
        abilities.put(4, abilities("DEF", 2));
        abilities.put(5, abilities("CHA", 1));

        return abilities;
    }
}
