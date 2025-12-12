package re.forestier.edu.rpg;

import re.forestier.edu.rpg.avatarclasses.AvatarClass;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    private final int MAXIMUM_LEVEL = 5;

    private String playerName;
    private String avatarName;
    private final AvatarClass avatarClass;

    private Integer money;

    private int maxHP;
    private int currentHP;
    private int xp;

    private HashMap<String, Integer> abilities;
    private ArrayList<String> inventory;

    public Player(String playerName, String avatarName, String avatarClassName, int money, ArrayList<String> inventory) {
        this.avatarClass = AvatarClass.fromString(avatarClassName);
        if (this.avatarClass == null) {
            return;
        }

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = money;
        this.inventory = inventory;
        this.abilities = this.avatarClass.getAbilitiesByLevel(1);
    }

    public String getAvatarClass () {
        return this.avatarClass == null ? null : this.avatarClass.toString();
    }

    public AvatarClass getAvatarClassObject() {
        return this.avatarClass;
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

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void addXp(int xp) {
        this.xp += xp;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public Integer getMoney() {
        return money;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void heal() {
        this.currentHP += this.avatarClass.calculateHealthRegeneration(this);
    }

    public HashMap<String, Integer> getAbilities() {
        return abilities;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public void addObjectInventory(String objectName) {
        this.inventory.add(objectName);
    }
}