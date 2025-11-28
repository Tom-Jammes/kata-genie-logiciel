package re.forestier.edu.rpg.avatarclasses;

import re.forestier.edu.rpg.Player;

public class Adventurer extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        int regen = 1;
        if(player.retrieveLevel() >= 3) {
            regen += 1;
        }
        return regen;
    }
}
