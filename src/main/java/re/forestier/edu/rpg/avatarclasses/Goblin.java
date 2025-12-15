package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

import java.util.HashMap;

public class Goblin extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        return 0;
    }

    @Override
    protected HashMap<Integer, HashMap<String, Integer>> initializeAbilities() {
        return new HashMap<>();
    }
}
