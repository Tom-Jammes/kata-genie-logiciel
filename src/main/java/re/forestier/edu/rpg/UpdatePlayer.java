package re.forestier.edu.rpg;

import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

    private final static String[] objectList = {"Lookout Ring : Prevents surprise attacks","Scroll of Stupidity : INT-2 when applied to an enemy", "Draupnir : Increases XP gained by 100%", "Magic Charm : Magic +10 for 5 rounds", "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", "Combat Edge : Well, that's an edge", "Holy Elixir : Recover your HP"
    };

    public static boolean addXp(Player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.addXp(xp);
        int newLevel = player.retrieveLevel();

        if (newLevel != currentLevel) {
            levelUp(player);
            return true;
        }
        return false;
    }

    private static void levelUp(Player player) {
        Random random = new Random();
        player.addObjectInventory(objectList[random.nextInt(objectList.length)]);

        HashMap<String, Integer> newAbilities = player.getAvatarClassObject().getAbilitiesByLevel(player.retrieveLevel());
        newAbilities.forEach((ability, level) -> player.getAbilities().put(ability, level));
    }

    // majFinDeTour met à jour les points de vie
    public static void updateEndOfRound(Player player) {
        if(player.getCurrentHP() == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if(player.getCurrentHP() < player.getMaxHP()/2) {
            player.heal();
        }

        if(player.getCurrentHP() > player.getMaxHP()) {
            player.setCurrentHP(player.getMaxHP());
        }
    }
}