package feltwinter.pGemMiner.Tasks.Banking;

import feltwinter.util.util;
import feltwinter.pGemMiner.Constants;      //Public.GFossilIsland.Constants;
import feltwinter.pGemMiner.pGemMiner;     //Public.GFossilIsland.GFossilIsland;
import feltwinter.pGemMiner.Task;       //Public.GFossilIsland.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class CloseBank extends Task {
    private final Constants c = new Constants();
    pGemMiner main;

    public CloseBank(pGemMiner main) {
        super();
        super.name = "CloseBank";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Bank.opened() && (Inventory.isEmpty() || Inventory.stream().name("Uncut opal" + "Uncut jade" + "Uncut red topaz" + "Uncut sapphire" + "Uncut emerald" + "Uncut ruby" + "Uncut diamond").isEmpty());
    }

    @Override
    public void execute() {
        pGemMiner.currentState = util.state("Closing bank");
        if (Bank.close()) {
            Condition.wait(() -> !Bank.opened(), 100, 20);
        }
    }
}
