package re.forestier.edu.rpg;

public class Affichage {

    public static String afficherJoueur(Player player) {
        StringBuilder display = new StringBuilder();

        /* Header */
        display.append("Joueur ").append(player.getAvatarName())
                .append(" joué par ").append(player.getPlayerName());
        display.append("\n");

        /* Stats */
        display.append("Niveau : ").append(player.retrieveLevel())
                .append(" (XP totale : ").append(player.getXp()).append(")");
        display.append("\n");

        /* Capacities */
        display.append("\n");
        display.append("Capacités :");
        player.getAbilities().forEach((name, level) -> {
            display.append("\n").append("   ").append(name).append(" : ").append(level);
        });
        display.append("\n");

        /* Inventory */
        display.append("\n");
        display.append("Inventaire :");
        player.getInventory().forEach((name) -> {
            display.append("\n").append("   ").append(name);
        });
        display.append("\n");

        return display.toString();
    }
}
