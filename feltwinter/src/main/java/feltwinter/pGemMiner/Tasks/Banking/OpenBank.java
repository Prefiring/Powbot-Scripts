package feltwinter.pGemMiner.Tasks.Banking;

import feltwinter.util.util;
import feltwinter.pGemMiner.Constants;      //Public.GFossilIsland.Constants;
import feltwinter.pGemMiner.pGemMiner;     //Public.GFossilIsland.GFossilIsland;
import feltwinter.pGemMiner.Task;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class OpenBank extends Task {
    private final Constants c = new Constants();
    pGemMiner main;

    public OpenBank(pGemMiner main) {
        super();
        super.name = "openBank";
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
        pGemMiner.currentState = util.state("Opening bank");
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
