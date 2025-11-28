package re.forestier.edu.rpg.avatars;

import re.forestier.edu.rpg.Player;

public class Dwarf extends AvatarClassAbstract {

    @Override
    public int calculateHealthRegeneration(Player player) {
        int regen = 1;
        if(player.inventory.contains("Holy Elixir")) {
            regen += 1;
        }
        return regen;
    }
}
