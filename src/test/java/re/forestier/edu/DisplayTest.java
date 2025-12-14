package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.avatarclasses.AvatarClass;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;

public class DisplayTest {

    @Test
    void testAffichageBase() {
        Player player = new Player("Florian", "Gnognak le Barbare", AvatarClass.ADVENTURER, 200, new ArrayList<>());
        player.addXp(20);
        player.setInventory(new ArrayList<>());

        verify(Affichage.afficherJoueur(player));
    }
}
