package feltwinter.pGemMiner;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Player;
import org.powbot.api.rt4.Players;

import java.util.ArrayList;

public class Constants {

    public Constants() {
        super();
    }

    public ArrayList<String> userTaskList = new ArrayList<String>();

    public Player p() {
        return Players.local();
    }

    public static final Area BANK_AREA = new Area(new Tile(2849, 2956, 0), new Tile(2855, 2952, 0));
    public static final Area GEM_AREA = new Area(new Tile(2818, 2994, 0), new Tile(2829, 3004, 0));

    public static final int DEPLETED_ROCK = 11391;

    public static final int UNCUT_OPAL = 1625;
    public static final int UNCUT_JADE = 1627;
    public static final int UNCUT_RED_TOPAZ = 1629;
    public static final int UNCUT_SAPPHIRE = 1623;
    public static final int UNCUT_EMERALD = 1621;
    public static final int UNCUT_RUBY = 1619;
    public static final int UNCUT_DIAMOND = 1617;


    public static final int MINING_ANIM = 624;
    public static final String[] TELEPORT_ITEMS = {"Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe",
            "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe", "Crystal pickaxe"};


}
