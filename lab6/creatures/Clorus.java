package creatures;

import huglife.*;
import org.reflections.vfs.CommonsVfs2UrlType;

import java.awt.Color;
import java.util.Map;
import java.util.List;

/**
 * @author Wending Peng
 */
public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    /* Units of energy lost on a MOVE action. */
    private static final double moveEnergyLost = 0.03;
    /* Units of energy gained on a STAY action. */
    private static final double stayEnergyGained = 0.01;
    /* Energy percentage given and retained for replication. */
    private static final double repEnergyGiven = 0.5;

    /* Create a clorus with energy equals to E. */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /* Create a clorus with energy equals to 1. */
    public Clorus() {
        this(1);
    }

    /* Set color. */
    public Color color() {
        return color(r, g, b);
    }

    public void move() {
        energy -= moveEnergyLost;
    }


    public void stay() {
        energy += stayEnergyGained;
    }

    /* When attacks a creature, gain that creature's energy. */
    public void attack(Creature attackedCreature) {
        energy += attackedCreature.energy();
        /* And the attacked creature dies done by the simulator. */
    }

    /* When a Clorus replicates, it keeps half of the energy and the rest goes to
     * the offspring. */
    public Clorus replicate() {
        energy *= repEnergyGiven;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (plips.size() > 0) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plips));
        }
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(empties));
        }
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(empties));
    }
}
