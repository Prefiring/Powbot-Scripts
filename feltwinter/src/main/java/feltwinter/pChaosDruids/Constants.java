package feltwinter.pChaosDruids;

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

    public static final Area BANK_AREA = new Area(new Tile(2949, 3367, 0), new Tile(2945, 3369, 0));    //fally west bank
    public static final Area DRUID_AREA = new Area(new Tile(2938, 9843, 0), new Tile(2928, 9853, 0));   //DRUIDS tav dung

    public static final String chaosDruid = "Chaos druid";

    public static final int HARRALANDER = 205;
    public static final int RANARR_WEED = 207;
    public static final int IRIT_LEAF = 209;
    public static final int AVANTOE = 211;
    public static final int KWUARM = 213;
    public static final int CADANTINE = 215;
    public static final int DWARF_WEED = 217;

    public static final String[] druidLoot = {"Grimy harralander", "Grimy ranarr weed", "Grimy irit leaf", "Grimy avantoe", "Grimy kwuarm", "Grimy cadantine", "Grimy dwarf weed", "Grimy lantadyme", "Law rune"};

    //public ArrayList<String> druidLoots = new ArrayList<>();


    //public static final int ATTACKING_ANIM = ;
    public static final int AFK = -1;

    public static final String[] TELEPORT_ITEMS = {"Rune pouch", "Divine rune pouch", };


}
