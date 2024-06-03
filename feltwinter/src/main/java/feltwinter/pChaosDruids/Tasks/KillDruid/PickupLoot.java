package feltwinter.pChaosDruids.Tasks.KillDruid;

import feltwinter.util.util;
import feltwinter.pChaosDruids.Constants;      //Public.GFossilIsland.Constants;
import feltwinter.pChaosDruids.pChaosDruids;  //Public.GFossilIsland.GFossilIsland;
import feltwinter.pChaosDruids.Task;        //Public.GFossilIsland.Task;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

import java.lang.reflect.Array;

import static feltwinter.pChaosDruids.Constants.druidLoot;

public class PickupLoot extends Task {
    private final Constants c = new Constants();
    pChaosDruids main;

    public PickupLoot(pChaosDruids main) {
        super();
        super.name = "PickupLoot";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return druidLoot = GroundItems.stream().nearest().first();        //return GroundItems.stream().name("Grimy harralander").nearest().first().valid();
    }

    @Override
    public void execute() {
        pChaosDruids.currentState = util.state("Picking Up Loot");
        GroundItem druidLoot = GroundItems.stream().nearest().first();
        if (druidLoot.inViewport() && Inventory.isNotEmpty()) {
            druidLoot.interact("Take");
            Condition.wait(() -> !druidLoot.valid(),150,20);
        }
    }
}

