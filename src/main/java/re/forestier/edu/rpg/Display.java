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

        /* ====================== ABILITIES (sorted alphabetically) ====================== */
        display.append("\n");
        display.append("Capacités :");
        player.getAbilities().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    display.append("\n").append("   ")
                            .append(entry.getKey()).append(" : ").append(entry.getValue());
                });
        display.append("\n");

        /* ====================== INVENTORY (sorted alphabetically) ====================== */
        display.append("\n");
        display.append("Inventaire :");
        player.getInventory().getItems().stream()
                .sorted()
                .forEach(name -> {
                    display.append("\n").append("   ").append(name);
                });
        display.append("\n");

        return display.toString();
    }

    public static String displayMarkdown(Player player) {
        StringBuilder display = new StringBuilder();

        /* ====================== GLOBAL INFOS ====================== */
        display.append("# ").append("Joueur ").append(player.getAvatarName())
                .append(" joué par ").append(player.getPlayerName())
                .append(" *(").append(player.getAvatarClass()).append(")*");
        display.append("\n");

        /* ====================== XP STATS ====================== */
        display.append("## ").append("Niveau : ").append(player.retrieveLevel())
                .append(" (XP totale : ").append(player.getXp()).append(")");
        display.append("\n");

        /* ====================== ABILITIES (sorted alphabetically) ====================== */
        display.append("## ").append("Capacités :");
        player.getAbilities().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    display.append("\n").append("* ")
                            .append(entry.getKey()).append(" : ").append(entry.getValue());
                });
        display.append("\n");

        /* ====================== INVENTORY (sorted alphabetically) ====================== */
        display.append("## ").append("Inventaire :");
        if (player.getInventory().size() == 0) {
            display.append("\n*(Aucun item)*");
        } else {
            player.getInventory().getItems().stream()
                    .sorted()
                    .forEach(item -> {
                        display.append("\n").append("* ").append(item.getName());
                    });
        }

        return display.toString();
    }
}
