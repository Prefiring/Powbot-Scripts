package feltwinter.pGemMiner;

import feltwinter.pGemMiner.Tasks.Banking.BankItems;         //Public.GFossilIsland.Tasks.Common.BankItems;
import feltwinter.pGemMiner.Tasks.Banking.CloseBank;         //Public.GFossilIsland.Tasks.Common.CloseBank;
import feltwinter.pGemMiner.Tasks.Banking.GoToBank;          //Public.GFossilIsland.Tasks.Common.GoToBank;
import feltwinter.pGemMiner.Tasks.Banking.OpenBank;          //Public.GFossilIsland.Tasks.Common.OpenBank;
import feltwinter.pGemMiner.Tasks.MineGem.GoToGems;
import feltwinter.pGemMiner.Tasks.MineGem.MineGem;
import feltwinter.pGemMiner.Tasks.MineGem.PickupBirdsNest;  //Public.GFossilIsland.Tasks.CutTrees.PickupBirdsNest
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
        name = "pGemMiner",
        description = "Mines gems in Shilo Village",
        version = "0.0.1",
        author = "Prefiring"
)

public class pGemMiner extends AbstractScript {
    private ArrayList<Task> farmSeaweedTasks = new ArrayList<>();
    private ArrayList<Task> mineGemTasks = new ArrayList<>();          //cutTreesTasks
    private final Constants c = new Constants();
    // Script vars
    public static String currentState = "null";

    // Script uploader
    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("pGemMiner", "notprefiring+2@gmail.com", "127.0.0.1:5695", true, false); //GFossilIsland
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
        return mineGemTasks;
    }

    public void onStart() {
        currentState = util.state("Starting pGemMiner by Prefiring...");
        Condition.wait(() -> Players.local().valid(), 500, 50);
        currentState = util.state("Checking Camera");
        util.cameraCheck();


        // mine gem tasks
        mineGemTasks.add(new PickupBirdsNest(this));
        mineGemTasks.add(new MineGem(this));
        mineGemTasks.add(new GoToBank(this));
        mineGemTasks.add(new OpenBank(this));
        mineGemTasks.add(new BankItems(this));
        mineGemTasks.add(new CloseBank(this));
        mineGemTasks.add(new GoToGems(this));


        Paint paint = new PaintBuilder()
                .addString(() -> "Current State: "      +currentState)
                .trackSkill(Skill.Mining)
                .trackInventoryItem(Constants.UNCUT_OPAL, "Opals", TrackInventoryOption.QuantityChange)        //.trackInventoryItem(Constants.TEAK_LOG_ID, "Teak Logs", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.UNCUT_JADE, "Jades", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.UNCUT_RED_TOPAZ, "Red Topaz", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.UNCUT_SAPPHIRE, "Sapphires", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.UNCUT_EMERALD, "Emeralds", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.UNCUT_RUBY, "Rubies", TrackInventoryOption.QuantityChange)
                .trackInventoryItem(Constants.UNCUT_DIAMOND, "Diamonds", TrackInventoryOption.QuantityChange)
                .build();
        addPaint(paint);
    }
}