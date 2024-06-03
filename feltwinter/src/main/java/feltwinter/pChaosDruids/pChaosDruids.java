package feltwinter.pChaosDruids;
import feltwinter.pChaosDruids.Constants;
import feltwinter.pChaosDruids.Task;
import feltwinter.pChaosDruids.Tasks.Banking.BankItems;         //pGemMiner.Tasks.Banking.BankItems;
import feltwinter.pChaosDruids.Tasks.Banking.CloseBank;       //pGemMiner.Tasks.Banking.CloseBank;
import feltwinter.pChaosDruids.Tasks.Banking.GoToBank;       //pGemMiner.Tasks.Banking.GoToBank;
import feltwinter.pChaosDruids.Tasks.Banking.OpenBank;         //pGemMiner.Tasks.Banking.OpenBank;
import feltwinter.pChaosDruids.Tasks.KillDruid.AttackDruid;
import feltwinter.pChaosDruids.Tasks.KillDruid.GoToDruid;       //pGemMiner.Tasks.MineGem.GoToGems
import feltwinter.pChaosDruids.Tasks.KillDruid.AttackDruid;      //pGemMiner.Tasks.MineGem.MineGem;
import feltwinter.pChaosDruids.Tasks.KillDruid.PickupLoot;  //Public.GFossilIsland.Tasks.CutTrees.PickupBirdsNest
import feltwinter.util.util;

import org.powbot.api.Condition;
import org.powbot.api.rt4.Players;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.api.script.paint.TrackInventoryOption;
import org.powbot.mobile.service.ScriptUploader;

import java.util.ArrayList;

@ScriptManifest(
        name = "pChaosDruids",
        description = "Kills druids for herbs in Tarverly Dungeon",
        version = "0.0.1",
        author = "Prefiring"
)

public class pChaosDruids extends AbstractScript {
    private ArrayList<Task> farmSeaweedTasks = new ArrayList<>();
    private ArrayList<Task> killDruidTasks = new ArrayList<>();          //cutTreesTasks
    private final Constants c = new Constants();
    // Script vars
    public static String currentState = "null";

    // Script uploader
    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("pChaosDruids", "notprefiring+1@gmail.com", "127.0.0.1:5695", true, false); //GFossilIsland
    }
    @Override
    public void poll() {
        for (Task task : getTaskList()) {
            if (task.activate()) {
                task.execute();
            }
        }
    }

    public ArrayList<Task> getTaskList() {
        return killDruidTasks;
    }

    public void onStart() {
        currentState = util.state("Starting pChaosDruids by Prefiring...");
        Condition.wait(() -> Players.local().valid(), 500, 50);
        currentState = util.state("Checking Camera");
        util.cameraCheck();


        // mine gem tasks
        killDruidTasks.add(new PickupLoot(this));
        killDruidTasks.add(new AttackDruid(this));
        killDruidTasks.add(new GoToBank(this));
        killDruidTasks.add(new OpenBank(this));
        killDruidTasks.add(new BankItems(this));
        killDruidTasks.add(new CloseBank(this));
        killDruidTasks.add(new GoToDruid(this));



        Paint paint = new PaintBuilder()
                .addString(() -> "Current State: "      +currentState)
                .trackSkill(Skill.Mining)
                .trackInventoryItem(Constants.HARRALANDER, "Harralander", TrackInventoryOption.QuantityChange)        //.trackInventoryItem(Constants.TEAK_LOG_ID, "Teak Logs", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.RANARR_WEED, "Ranarr", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.IRIT_LEAF, "Irit", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.AVANTOE, "Avantoe", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.KWUARM, "Kwuarm", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.CADANTINE, "Cadantine", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.DWARF_WEED, "Dwarf Weed", TrackInventoryOption.QuantityChange)
                .build();
        addPaint(paint);
    }
}