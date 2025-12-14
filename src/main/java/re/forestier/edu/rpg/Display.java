package re.forestier.edu.rpg;

import java.util.Map;

public class Display {

    public static String displayPlayer(Player player) {
        StringBuilder display = new StringBuilder();

        /* ====================== GLOBAL INFOS ====================== */
        display.append("Joueur ").append(player.getAvatarName())
                .append(" joué par ").append(player.getPlayerName());
        display.append("\n");

        /* ====================== XP STATS ====================== */
        display.append("Niveau : ").append(player.retrieveLevel())
                .append(" (XP totale : ").append(player.getXp()).append(")");
        display.append("\n");

        /* ====================== ABILITIES ====================== */
        display.append("\n");
        display.append("Capacités :"); // Abilities sorted by name
        player.getAbilities().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    display.append("\n").append("   ")
                            .append(entry.getKey()).append(" : ").append(entry.getValue());
                });
        display.append("\n");

        /* ====================== INVENTORY ====================== */
        display.append("\n");
        display.append("Inventaire :"); // Inventory sorted by object's name
        player.getInventory().stream()
                .sorted()
                .forEach(name -> {
                    display.append("\n").append("   ").append(name);
                });
        display.append("\n");

        return display.toString();
    }
}
