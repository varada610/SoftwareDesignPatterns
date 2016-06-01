package compositePatternPackage.productPackage.toolsProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.Tools;

/**
 * Class that represents palette tool in all product categories.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class Palette extends Tools {

    /**
     * Constructor.
     */
    public Palette() {
        this(new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param size - SizeState of this tool
     * @param level - ExpertiseLevelState of this tool
     */
    public Palette(SizeState size, ExpertiseLevelState level) {
        this.baseCost = 1.5;
        this.sizeState = size;
        this.levelState = level;
        this.parent = null;
        System.out.println("Constructor::Palette");
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tPalette: "
                + sizeState.getSizeDescription() + " ("
                + levelState.getLevelDescription() + " Level)<br>");
    }

    public String toString() {
        return "Palette Object";
    }

    /**
     * Calculate final cost using sizeMultiplier and levelMultiplier.
     *
     * @return - cost of this product
     */
    @Override
    public double getCost() {
        double totalCost = baseCost * levelState.getLevelMultiplier() * sizeState.getSizeMultiplier();
        System.out.println(toString() + " cost: " + totalCost);
        return totalCost;
    }
}
