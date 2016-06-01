package compositePatternPackage.productPackage.toolsProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.Tools;

/**
 * Class that represents eraser tool in all product categories.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class Eraser extends Tools {

    /**
     * Constructor
     */
    public Eraser() {
        this(new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param size - SizeState of this tool
     * @param level - ExpertiseLevelState of this tool
     */
    public Eraser(SizeState size, ExpertiseLevelState level) {
        this.baseCost = 0.5;
        this.sizeState = size;
        this.levelState = level;
        this.parent = null;
        System.out.println("Constructor::Eraser");
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tEraser: "
                + sizeState.getSizeDescription() + " ("
                + levelState.getLevelDescription() + " Level)<br>");
    }

    public String toString() {
        return "Eraser Object";
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
