package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;

import static re.forestier.edu.rpg.AvatarClass.fromString;

public class Player {

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
        this.money = Integer.valueOf(money);
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

        money = Integer.parseInt(money.toString()) - amount;
    }
    public void addMoney(int amount) {
        money += amount;
    }
    public int retrieveLevel() {
        // (lvl-1) * 10 + round((lvl * xplvl-1)/4)
        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2,10); // 1*10 + ((2*0)/4)
        levels.put(3,27); // 2*10 + ((3*10)/4)
        levels.put(4,57); // 3*10 + ((4*27)/4)
        levels.put(5,111); // 4*10 + ((5*57)/4)
        //TODO : ajouter les prochains niveaux

        if (xp < levels.get(2)) {
            return 1;
        }
        else if (xp < levels.get(3)) {return 2;
        }
        if (xp < levels.get(4)) {
            return 3;
        }
        if (xp < levels.get(5)) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }
}