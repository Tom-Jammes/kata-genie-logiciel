package re.forestier.edu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Display;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.avatarclasses.AvatarClass;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;

public class DisplayTest {

    @Test
    void testDisplayPlayerAsString() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 100, 200, new ArrayList<>());
        player.addXp(20);
        player.setInventory(new ArrayList<>());

        verify(Display.displayPlayer(player));
    }

    @Test
    @DisplayName("Affichage en markdown du player avec inventaire vide")
    void testDisplayMarkdownPlayerEmptyInventory() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 100, 200, new ArrayList<>());
        player.addXp(20);
        player.setInventory(new ArrayList<>());

        verify(Display.displayMarkdown(player));
    }

    @Test
    @DisplayName("Affichage en markdown du player")
    void testDisplayMarkdownPlayerNotEmptyInventory() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 100, 200, new ArrayList<>());
        player.addXp(30);
        player.setInventory(new ArrayList<>());
        player.addObjectInventory("Scroll of Stupidity");

        verify(Display.displayMarkdown(player));
    }
}
