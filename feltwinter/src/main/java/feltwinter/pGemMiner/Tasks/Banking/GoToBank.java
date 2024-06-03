package feltwinter.pGemMiner.Tasks.Banking;

import feltwinter.util.util;
import feltwinter.pGemMiner.Constants;      //Public.GFossilIsland.Constants;
import feltwinter.pGemMiner.pGemMiner;     //Public.GFossilIsland.GFossilIsland;
import feltwinter.pGemMiner.Task;      //Public.GFossilIsland.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Objects;

public class GoToBank extends Task {
    private final Constants c = new Constants();
    pGemMiner main;

    public GoToBank(pGemMiner main) {
        super();
        super.name = "goToBank";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Inventory.isFull() && !Constants.BANK_AREA.contains(c.p().tile());
    }

    @Override
    public void execute() {
        pGemMiner.currentState = util.state("Walking to bank");
        if (Constants.GEM_AREA.contains(c.p().tile())) {
            GameObject hole = Objects.stream(10).type(GameObject.Type.INTERACTIVE).name("Hole").nearest().first(); //get rid of this soon
            if (hole.valid() && hole.interact("Climb through")) {
                Condition.wait(() -> !Constants.GEM_AREA.contains(c.p().tile()), 150, 20);
            }
        }
        Movement.moveTo(Constants.BANK_AREA.getRandomTile());
    }
}
