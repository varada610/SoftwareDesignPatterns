package compositePatternPackage.productPackage.toolsProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.productPackage.Tools;

/**
 * Class that represents sharpener tool in all product categories.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class Sharpener extends Tools {

    /**
     * Constructor.
     */
    public Sharpener() {
        this(new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param level - ExpertiseLevelState of this tool
     */
    public Sharpener(ExpertiseLevelState level) {
        this.baseCost = 0.25;
        this.sizeState = null;
        this.levelState = level;
        this.parent = null;
        System.out.println("Constructor::Sharpener");
    }

    /**
     * Get details of this product including level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tPencil Sharpener (" + levelState.getLevelDescription() + " Level)<br>");
    }

    /**
     * Calculate final cost using levelMultiplier.
     *
     * @return - cost of this product
     */
    @Override
    public double getCost() {
        double totalCost = baseCost * levelState.getLevelMultiplier();
        System.out.println(toString() + " cost: " + totalCost);
        return totalCost;
    }

    public String toString() {
        return "Sharpener Object";
    }
}
