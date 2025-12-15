package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.inventory.Item;

import java.util.HashMap;

public class Dwarf extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        int regen = 1;
        if(player.getInventory().contains(Item.HOLY_ELIXIR)) {
            regen += 1;
        }
        return regen;
    }

    @Override
    protected HashMap<Integer, HashMap<String, Integer>> initializeAbilities() {
        HashMap<Integer, HashMap<String, Integer>> abilities = new HashMap<>();

        abilities.put(1, createAbilities("ALC", 4, "INT", 1, "ATK", 3));
        abilities.put(2, createAbilities("DEF", 1, "ALC", 5));
        abilities.put(3, createAbilities("ATK", 4));
        abilities.put(4, createAbilities("DEF", 2));
        abilities.put(5, createAbilities("CHA", 1));

        return abilities;
    }
}
