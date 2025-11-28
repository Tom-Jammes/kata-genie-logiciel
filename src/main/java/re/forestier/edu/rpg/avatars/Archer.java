package re.forestier.edu.rpg.avatars;

import re.forestier.edu.rpg.Player;

public class Archer extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        int regen = 1;
        if(player.inventory.contains("Magic Bow")) {
           regen += player.currenthealthpoints/8-1;
        }
        return regen;
    }
}
