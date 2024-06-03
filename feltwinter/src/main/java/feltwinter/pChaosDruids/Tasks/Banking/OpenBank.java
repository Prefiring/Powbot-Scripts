package feltwinter.pChaosDruids.Tasks.Banking;

import feltwinter.util.util;
import feltwinter.pChaosDruids.Constants;      //Public.GFossilIsland.Constants;
import feltwinter.pChaosDruids.pChaosDruids;     //Public.GFossilIsland.GFossilIsland;
import feltwinter.pChaosDruids.Task;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class OpenBank extends Task {
    private final Constants c = new Constants();
    pChaosDruids main;

    public OpenBank(pChaosDruids main) {
        super();
        super.name = "OpenBank";            //openBank
        this.main = main;
    }

    @Override
    public boolean activate() {
        return Bank.present()
                && !Bank.opened()
                && Inventory.isFull()
                && (c.p().movementAnimation() == c.p().idleAnimation());
    }

    @Override
    public void execute() {
        pChaosDruids.currentState = util.state("Opening Bank");
        if (Bank.inViewport() && Bank.open()) {
            Condition.wait(Bank::opened, 100, 20);
        } else {
            GameObject banker = Objects.stream().name("Banker").nearest().first();   //GameObject needs to change to NPC maybe ok tho?
            if (banker.valid()) {
                Camera.turnTo(banker);
            }
        }
    }
}