package feltwinter.pGemMiner.Tasks.MineGem;

import feltwinter.util.util;
import feltwinter.pGemMiner.Constants;          //Public.GFossilIsland.Constants;
import feltwinter.pGemMiner.pGemMiner;       //Public.GFossilIsland.GFossilIsland;
import feltwinter.pGemMiner.Task;            //Public.GFossilIsland.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class GoToGems extends Task {
    private final Constants c = new Constants();
    pGemMiner main;

    public GoToGems(pGemMiner main) {
        super();
        super.name = "GoToGems";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && !Constants.GEM_AREA.contains(c.p().tile());
    }

    @Override
    public void execute() {
        pGemMiner.currentState = util.state("Walking to gems");
        Movement.moveTo(Constants.GEM_AREA.getRandomTile());
        GameObject hole = Objects.stream(10).type(GameObject.Type.INTERACTIVE).name("Hole").nearest().first();
        if (hole.valid()) {
            if (hole.inViewport() && hole.interact("Climb through")) {
                Condition.wait(() -> Constants.GEM_AREA.contains(c.p().tile()), 150, 20);
            } else {
                System.out.println("Turning camera to hole");
                Camera.turnTo(hole);
            }
        }
//        Movement.moveTo(Constants.TREE_AREA.getRandomTile());

    }
}
