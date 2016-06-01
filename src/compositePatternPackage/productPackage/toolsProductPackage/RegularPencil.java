package compositePatternPackage.productPackage.toolsProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.productPackage.Tools;

/**
 * Class that represents Regular pencil tool in all product categories.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class RegularPencil extends Tools {

    /**
     * Constructor.
     */
    public RegularPencil() {
        this(new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param level - ExpertiseLevelState of this tool
     */
    public RegularPencil(ExpertiseLevelState level) {
        this.baseCost = 0.1;
        this.sizeState = null;
        this.levelState = level;
        this.parent = null;
        System.out.println("Constructor::RegularPencil");
    }

    /**
     * Get details of this product including size.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tPencil ("
                + levelState.getLevelDescription() + " Level)<br>");
    }

    /**
     * Calculate final cost using sizeMultiplier and levelMultiplier.
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
        return "RegularPencil Object";
    }
}
