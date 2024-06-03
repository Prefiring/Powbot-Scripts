package feltwinter.pChaosDruids.Tasks.Banking;

import feltwinter.pChaosDruids.Constants;      //Public.GFossilIsland.Constants;
import feltwinter.pChaosDruids.pChaosDruids;  //Public.GFossilIsland.GFossilIsland;
import feltwinter.pChaosDruids.Task;       //Public.GFossilIsland.Task;
import feltwinter.util.util;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class BankItems extends Task {
    private final Constants c = new Constants();
    pChaosDruids main;

    public BankItems(pChaosDruids main) {
        super();
        super.name = "BankLoot";        //bankGems
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Inventory.isFull() && Bank.opened();
    }

    @Override
    public void execute() {
        pChaosDruids.currentState = util.state("Banking Loot");
        if (Bank.depositAllExcept(Constants.TELEPORT_ITEMS)) {
            // This should probably check inventory for only items in axe list but cant think of the code rn
            Condition.wait(() -> Inventory.isEmpty()
                    || Inventory.stream().name("Grimy harralander" ).isEmpty(), 150, 20);
        }
    }
}