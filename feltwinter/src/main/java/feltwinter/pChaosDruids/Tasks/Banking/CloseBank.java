package feltwinter.pChaosDruids.Tasks.Banking;

import feltwinter.util.util;
import feltwinter.pChaosDruids.Constants;
import feltwinter.pChaosDruids.pChaosDruids;
import feltwinter.pChaosDruids.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class CloseBank extends Task {
    private final Constants c = new Constants();
    pChaosDruids main;

    public CloseBank(pChaosDruids main) {
        super();
        super.name = "CloseBank";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Bank.opened() && (Inventory.isEmpty() || Inventory.stream().name("Grimy harralander" + "Grimy ranarr weed" + "Grimy kwuarm" + "Grimy avantoe" ).isEmpty());
    }

    @Override
    public void execute() {
        pChaosDruids.currentState = util.state("Closing Bank");
        if (Bank.close()) {
            Condition.wait(() -> !Bank.opened(), 100, 20);
        }
    }
}
