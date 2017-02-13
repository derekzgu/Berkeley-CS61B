package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import org.reflections.vfs.CommonsVfs2UrlType;

import java.awt.Color;
import java.util.Map;
import java.util.List;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug, Wending Peng
 */
public class Plip extends Creature {

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
    private static final double moveEnergyLost = 0.15;
    /* Units of energy gained on a STAY action. */
    private static final double stayEnergyGained = 0.2;
    /* Max energy of plip. */
    private static final double maxEnergy = 2;
    /* Energy percentage given and retained for replication. */
    private static final double repEnergyGiven = 0.5;
    /* probability to move when there are cloruses nearby. */
    private static final double moveProbability = 0.5;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 0;
        b = 76;
        energy = e > maxEnergy ? maxEnergy : e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }


    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = (int) (96 * energy) + 63;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= moveEnergyLost;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += stayEnergyGained;
        energy = energy > maxEnergy ? maxEnergy : energy;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        energy *= repEnergyGiven;
        return new Plip(energy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        Direction randomEmptyDirection = HugLifeUtils.randomEntry(empties);
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEmptyDirection);
        }
        if (getNeighborsOfType(neighbors, "clorus").size() > 0 && HugLifeUtils.random() < moveProbability) {
            return new Action(Action.ActionType.MOVE, randomEmptyDirection);
        }
        return new Action(Action.ActionType.STAY);
    }

}
