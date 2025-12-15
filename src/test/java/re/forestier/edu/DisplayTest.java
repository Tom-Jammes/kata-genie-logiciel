package re.forestier.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Display;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.avatarclasses.AvatarClass;
import re.forestier.edu.rpg.inventory.Item;

import static org.approvaltests.Approvals.verify;

public class DisplayTest {

    @Test
    void testDisplayPlayerAsString() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 100, 200, 5);
        player.addXp(20);
        player.getInventory().clear();

        verify(Display.displayPlayer(player));
    }

    @Test
    @DisplayName("Affichage en markdown du player avec inventaire vide")
    void testDisplayMarkdownPlayerEmptyInventory() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 100, 200, 5);
        player.addXp(20);
        player.getInventory().clear();

        verify(Display.displayMarkdown(player));
    }

    @Test
    @DisplayName("Affichage en markdown du player avec inventaire non vide")
    void testDisplayMarkdownPlayerNotEmptyInventory() {
        Player player = new Player("Tom", "Legolas", AvatarClass.ARCHER, 100, 200, 5);
        player.addXp(20);
        player.getInventory().clear();
        player.addObjectInventory(Item.SCROLL_OF_STUPIDITY);

        verify(Display.displayMarkdown(player));
    }
}
