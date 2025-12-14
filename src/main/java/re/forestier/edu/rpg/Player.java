package re.forestier.edu.rpg;

import re.forestier.edu.rpg.avatarclasses.AvatarClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player {
    private final static String[] objectList = {
            "Lookout Ring : Prevents surprise attacks",
            "Scroll of Stupidity : INT-2 when applied to an enemy",
            "Draupnir : Increases XP gained by 100%",
            "Magic Charm : Magic +10 for 5 rounds",
            "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?",
            "Combat Edge : Well, that's an edge",
            "Holy Elixir : Recover your HP"
    };
    private final static int MAXIMUM_LEVEL = 5;

    private final String playerName;
    private final String avatarName;
    private final AvatarClass avatarClass;
    private int money;
    private final int maxHP;
    private int currentHP;
    private int xp;
    private final HashMap<String, Integer> abilities;
    private ArrayList<String> inventory;

    public Player(String playerName, String avatarName, AvatarClass avatarClass, int maxHP, int money, ArrayList<String> inventory) {
        this.avatarClass = avatarClass;
        this.playerName = playerName;
        this.avatarName = avatarName;
        this.money = money;
        this.maxHP = maxHP;
        this.inventory = inventory;
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

    public void setXp(int xp) {
        this.xp = xp;
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
            System.out.println("Le joueur est KO !");
            return;
        }
        if(this.currentHP < this.maxHP/2) {
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
    public ArrayList<String> getInventory() {
        return inventory;
    }
    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }
    public void addObjectInventory(String objectName) {
        this.inventory.add(objectName);
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
        Random random = new Random();
        this.addObjectInventory(objectList[random.nextInt(objectList.length)]);

        HashMap<String, Integer> newAbilities = this.avatarClass.getAbilitiesByLevel(retrieveLevel());
        this.abilities.putAll(newAbilities);
    }

    private void heal() {
        this.currentHP += this.avatarClass.calculateHealthRegeneration(this);
    }
}