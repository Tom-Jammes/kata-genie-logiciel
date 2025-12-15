package re.forestier.edu;
import re.forestier.edu.rpg.Display;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.avatarclasses.AvatarClass;


public class Main {
    public static void main(String[] args) {
        Player firstPlayer = new Player("Florian", "Ruzberg de Rivehaute", AvatarClass.DWARF, 100, 200, 5);
        firstPlayer.addMoney(400);

        firstPlayer.addXp(15);
        System.out.println(Display.displayPlayer(firstPlayer));
        System.out.println("------------------");
        firstPlayer.addXp(20);
        System.out.println(Display.displayPlayer(firstPlayer));
    }
}