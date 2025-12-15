package re.forestier.edu.rpg;

import re.forestier.edu.rpg.avatarclasses.AvatarClass;
import re.forestier.edu.rpg.inventory.Inventory;
import re.forestier.edu.rpg.inventory.Item;

import java.util.HashMap;
import java.util.Random;

public class Player {
    private final static int MAXIMUM_LEVEL = 5;
    private static final double HEALING_THRESHOLD = 0.5; // 50%

    private final String playerName;
    private final String avatarName;
    private final AvatarClass avatarClass;
    private int money;
    private final int maxHP;
    private int currentHP;
    private int xp;
    private final HashMap<String, Integer> abilities;
    private final Inventory inventory;

    public Player(String playerName, String avatarName, AvatarClass avatarClass, int maxHP, int money, int maxInventoryWeight) {
        this.avatarClass = avatarClass;
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = money;
        this.maxHP = maxHP;
        this.inventory = new Inventory(maxInventoryWeight);
        this.abilities = this.avatarClass.getAbilitiesByLevel(1);
    }

    /* ====================== MONEY ====================== */
    public void removeMoney(int amount) {
        if (money < amount) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money -= amount;
    }
    public void addMoney(int amount) {
        money += amount;
    }
    public Integer getMoney() {
        return money;
    }

    /* ====================== XP ====================== */
    public int retrieveLevel() {
        return retrieveLevelFromXp(1,0);
    }
    public int getXp() {
        return this.xp;
    }
    public boolean addXp(int xp) {
        int currentLevel = this.retrieveLevel();
        this.xp += xp;
        int newLevel = this.retrieveLevel();

        if (newLevel != currentLevel) {
            levelUp();
            return true;
        }
        return false;
    }

    /* ====================== HP ====================== */
    public void updateEndOfRound() {
        if(this.currentHP == 0) {
            return;
        }
        if(this.currentHP < this.maxHP * HEALING_THRESHOLD) {
            heal();
        }
        if(this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }
    public int getCurrentHP() {
        return currentHP;
    }
    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    /* ====================== INVENTORY ====================== */
    public Inventory getInventory() {
        return inventory;
    }
    public void addObjectInventory(Item item) {
        this.inventory.addItem(item);
    }

    /* ====================== OTHER GETTERS ====================== */
    public String getPlayerName() {
        return playerName;
    }
    public String getAvatarName() {
        return avatarName;
    }
    public HashMap<String, Integer> getAbilities() {
        return abilities;
    }
    public AvatarClass getAvatarClass () {
        return this.avatarClass;
    }

    /* ====================== PRIVATE METHODS ====================== */
    private int retrieveLevelFromXp(int currentLvl, int xpForCurrentLvl) {
        int xpNextLvl = currentLvl * 10 + (currentLvl+1)*xpForCurrentLvl/4; // Formule to calculate the xp needed for lvlUp

        if (this.xp < xpNextLvl || currentLvl == MAXIMUM_LEVEL) {
            return currentLvl;
        }
        return retrieveLevelFromXp(currentLvl+1, xpNextLvl);
    }

    private void levelUp() {
        this.addObjectInventory(Item.random());

        HashMap<String, Integer> newAbilities = this.avatarClass.getAbilitiesByLevel(retrieveLevel());
        this.abilities.putAll(newAbilities);
    }

    private void heal() {
        this.currentHP += this.avatarClass.calculateHealthRegeneration(this);
    }
}