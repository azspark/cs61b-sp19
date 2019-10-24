package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testChooseAction() {
        // Test Attack action
        Clorus clorus = new Clorus(1);
        HashMap<Direction, Occupant> onePlip = new HashMap<Direction, Occupant>();

        onePlip.put(Direction.TOP, new Plip());
        onePlip.put(Direction.BOTTOM, new Empty());
        onePlip.put(Direction.LEFT, new Empty());
        onePlip.put(Direction.RIGHT, new Empty());
        assertEquals(clorus.chooseAction(onePlip), new Action(Action.ActionType.ATTACK, Direction.TOP));

    }
}
