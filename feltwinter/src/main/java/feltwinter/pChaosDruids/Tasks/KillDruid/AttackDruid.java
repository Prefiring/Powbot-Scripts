package feltwinter.pChaosDruids.Tasks.KillDruid;


import feltwinter.util.util;
import feltwinter.pChaosDruids.Constants;      //feltwinter.pGemMiner.Constants;
import feltwinter.pChaosDruids.pChaosDruids;      //feltwinter.pGemMiner.pGemMiner;
import feltwinter.pChaosDruids.Task;           //feltwinter.pGemMiner.Task;

import org.powbot.api.Condition;
import org.powbot.api.rt4.*;

public class AttackDruid extends Task {        //OpenBank
    private final Constants c = new Constants();
    pChaosDruids main;     //pGemMiner

    public AttackDruid(pChaosDruids main) {
        super();
        super.name = "AttackDruid";        //openBank
        this.main = main;
    }

    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && !(Players.local().animation() == Constants.AFK)  //MINING_ANIM
                && Constants.DRUID_AREA.contains(c.p().tile());     //GEM_AREA
    }

    @Override
    public void execute() {
        pChaosDruids.currentState = util.state("Attacking Druid");        //pGemMiner
        Npc chaosDruid = Npcs.stream().name("Chaos druid").nearest().first();
        if (chaosDruid.inViewport() && chaosDruid.interact("Attack")) {
            chaosDruid.interact("Attack", "Chaos druid");
            Condition.wait(() -> !Players.local().interacting().healthBarVisible(), 150, 10);
        }
    }
}
