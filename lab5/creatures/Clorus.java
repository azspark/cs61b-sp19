package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;
import static huglife.HugLifeUtils.*;

import javax.crypto.spec.DESedeKeySpec;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    public Clorus(double e) {
        super("clorus");
    }

    public Clorus() {
        super("clorus");
        energy = 1;
    }

    public void move() {
        energy -= 0.03;
    }

    public void stay() {
        energy += 0.03;
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyDirection = new ArrayDeque<>();
        Deque<Direction> plipDirection = new ArrayDeque<>();

        for (Direction dir: neighbors.keySet()) {
            if (neighbors.get(dir).name().equals("empty")) {
                emptyDirection.addLast(dir);
            } else if (neighbors.get(dir).name().equals("plip")) {
                plipDirection.addLast(dir);
            }
        }

        if (emptyDirection.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        if (plipDirection.size() > 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipDirection));
        }

        if (energy > 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyDirection));
        }
        return new Action(Action.ActionType.STAY);
    }

    @Override
    public Color color() {
        return new Color(34,0,231);
    }
}
