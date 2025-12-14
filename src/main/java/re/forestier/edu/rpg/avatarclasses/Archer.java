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

        HashMap<String, Integer> level1 = new HashMap<>();
        level1.put("INT", 1);
        level1.put("ATK", 3);
        level1.put("CHA", 1);
        level1.put("VIS", 3);
        abilities.put(1, level1);

        HashMap<String, Integer> level2 = new HashMap<>();
        level2.put("DEF", 1);
        level2.put("CHA", 2);
        abilities.put(2, level2);

        HashMap<String, Integer> level3 = new HashMap<>();
        level3.put("ATK", 3);
        abilities.put(3, level3);

        HashMap<String, Integer> level4 = new HashMap<>();
        level4.put("DEF", 2);
        abilities.put(4, level4);

        HashMap<String, Integer> level5 = new HashMap<>();
        level5.put("ATK", 4);
        abilities.put(5, level5);

        return abilities;
    }
}
