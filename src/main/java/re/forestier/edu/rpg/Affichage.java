package re.forestier.edu.rpg;

public class Affichage {

    public static String afficherJoueur(Player player) {
        StringBuilder display = new StringBuilder();

        /* Header */
        display.append("Joueur ").append(player.avatarName)
                .append(" joué par ").append(player.playerName);
        display.append("\n");

        /* Stats */
        display.append("Niveau : ").append(player.retrieveLevel())
                .append(" (XP totale : ").append(player.xp).append(")");
        display.append("\n");

        /* Capacities */
        display.append("\n");
        display.append("Capacités :");
        player.abilities.forEach((name, level) -> {
            display.append("\n").append("   ").append(name).append(" : ").append(level);
        });
        display.append("\n");

        /* Inventory */
        display.append("\n");
        display.append("Inventaire :");
        player.inventory.forEach((name) -> {
            display.append("\n").append("   ").append(name);
        });
        display.append("\n");

        return display.toString();
    }
}
