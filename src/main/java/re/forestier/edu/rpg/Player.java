package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

import static re.forestier.edu.rpg.AvatarClass.fromString;

public class Player {

    private final int MAXIMUM_LEVEL = 5;

    public String playerName;
    public String avatarName;
    private final AvatarClass avatarClass;

    public Integer money;

    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    public Player(String playerName, String avatarName, String avatarClassName, int money, ArrayList<String> inventory) {
        this.avatarClass = fromString(avatarClassName);
        if (this.avatarClass == null) {
            return;
        }

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = money;
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(avatarClass.toString()).get(1);
    }

    public String getAvatarClass () {
        return this.avatarClass == null ? null : this.avatarClass.toString();
    }

    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }

        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int retrieveLevel() {

        return retrieveLevelFromXp(1,0);
    }

    private int retrieveLevelFromXp(int currentLvl, int xpForCurrentLvl) {

        int xpNextLvl = currentLvl * 10 + (currentLvl+1)*xpForCurrentLvl/4; // Formule to calculate the xp needed for lvlUp

        if (this.xp < xpNextLvl || currentLvl == MAXIMUM_LEVEL) {
            return currentLvl;
        }
        return retrieveLevelFromXp(currentLvl+1, xpNextLvl);
    }

    public int getXp() {
        return this.xp;
    }
}