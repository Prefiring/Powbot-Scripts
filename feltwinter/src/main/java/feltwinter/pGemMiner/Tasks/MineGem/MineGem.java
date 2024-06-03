package feltwinter.pGemMiner.Tasks.MineGem;

import feltwinter.util.util;
import feltwinter.pGemMiner.Constants;              //Public.GFossilIsland.Constants;
import feltwinter.pGemMiner.pGemMiner;         //Public.GFossilIsland.GFossilIsland;
import feltwinter.pGemMiner.Task;               //Public.GFossilIsland.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.GameObject;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Objects;
import org.powbot.api.rt4.Players;

public class MineGem extends Task {
    private final Constants c = new Constants();
    pGemMiner main;             //GFossilIsland

    public MineGem(pGemMiner main) {
        super();
        super.name = "mineGems";
        this.main = main;
    }
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && !(Players.local().animation() == Constants.MINING_ANIM)
                && Constants.GEM_AREA.contains(c.p().tile());
    }

    @Override
    public void execute() {
        pGemMiner.currentState = util.state("Mining rocks");
        GameObject rocks = Objects.stream(10).type(GameObject.Type.INTERACTIVE).nearest().id(11380,11381).nearest().first();              //.name(Strings: "Rocks").nearest
        if (rocks.valid() && rocks.interact("Mine")) {
            Condition.wait(() ->  c.p().animation() == Constants.MINING_ANIM, 150, 20);
        }
    }
}


