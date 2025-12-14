package re.forestier.edu;

import org.junit.jupiter.api.Test;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class DisplayTest {

    @Test
    void testAffichageBase() {
        Player player = new Player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        player.addXp(20);
        player.setInventory(new ArrayList<>());

        verify(Affichage.afficherJoueur(player));
    }
}
