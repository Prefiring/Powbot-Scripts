package feltwinter.pChaosDruids.Tasks.KillDruid;

import feltwinter.util.util;
import feltwinter.pChaosDruids.Constants;         //Public.GFossilIsland.Constants;
import feltwinter.pChaosDruids.pChaosDruids;       //Public.GFossilIsland.GFossilIsland;
import feltwinter.pChaosDruids.Task;           //Public.GFossilIsland.Task;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class GoToDruid extends Task {
    private final Constants c = new Constants();
    pChaosDruids main;

    public GoToDruid(pChaosDruids main) {
        super();
        super.name = "GoToGems";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && !Constants.DRUID_AREA.contains(c.p().tile());
    }

    @Override
    public void execute() {
        pChaosDruids.currentState = util.state("Walking to gems");
        Movement.moveTo(Constants.DRUID_AREA.getRandomTile());
        GameObject hole = Objects.stream(10).type(GameObject.Type.INTERACTIVE).name("Hole").nearest().first();
        if (hole.valid()) {
            if (hole.inViewport() && hole.interact("Climb through")) {
                Condition.wait(() -> Constants.DRUID_AREA.contains(c.p().tile()), 150, 20);
            } else {
                System.out.println("Turning camera to hole");
                Camera.turnTo(hole);
            }
        }
//        Movement.moveTo(Constants.TREE_AREA.getRandomTile());

    }
}
