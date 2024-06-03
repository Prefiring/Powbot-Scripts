package feltwinter.pGemMiner.Tasks.Banking;

import feltwinter.pGemMiner.Constants;      //Public.GFossilIsland.Constants;
import feltwinter.pGemMiner.pGemMiner;  //Public.GFossilIsland.GFossilIsland;
import feltwinter.pGemMiner.Task;       //Public.GFossilIsland.Task;
import feltwinter.util.util;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class BankItems extends Task {
    private final Constants c = new Constants();
    pGemMiner main;

    public BankItems(pGemMiner main) {
        super();
        super.name = "bankGems";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }

    @Override
    public void execute() {
        pGemMiner.currentState = util.state("Banking items");
        if (Bank.depositAllExcept(Constants.TELEPORT_ITEMS)) {
            // This should probably check inventory for only items in axe list but cant think of the code rn
            Condition.wait(() -> Inventory.isEmpty()
                    || Inventory.stream().name("Uncut Opal").isEmpty(), 150, 20);
        }
    }
}
