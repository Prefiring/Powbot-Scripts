package feltwinter.pChaosDruids.Tasks.Banking;

import feltwinter.util.util;
import feltwinter.pChaosDruids.Constants;
import feltwinter.pChaosDruids.pChaosDruids;
import feltwinter.pChaosDruids.Task;
import org.powbot.api.Condition;
import org.powbot.api.rt4.*;
import org.powbot.proto.rt4.Spellbook;

public class GoToBank extends Task {
    private final Constants c = new Constants();
    pChaosDruids main;

    public GoToBank(pChaosDruids main) {
        super();
        super.name = "GoToBank";            //goToBank
        this.main = main;
    }
    @Override
    public boolean activate() {
        return Inventory.isFull() && !Constants.BANK_AREA.contains(c.p().tile());
    }

    @Override
    public void execute() {
        pChaosDruids.currentState = util.state("BankTask");
        //if (Game.tab(Game.Tab.MAGIC)) {
            //Magic.magicspell().cast().           //GameObject hole = Objects.stream(10).type(GameObject.Type.INTERACTIVE).name("Hole").nearest().first(); //get rid of this soon
            //if (hole.valid() && hole.interact("Climb through")) {
                //Condition.wait(() -> !Constants.DRUID_AREA.contains(c.p().tile()), 150, 20);
            //}
        //}
        Movement.moveTo(Constants.BANK_AREA.getRandomTile());
    }
}