package compositePatternPackage.productPackage.toolsProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.Tools;

/**
 * Class that represents easel tool in all product categories.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class Easel extends Tools {

    /**
     * base height of this easel.
     */
    private int basicHeightInch;

    /**
     * Constructor
     */
    public Easel() {
        this(new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param size - SizeState of this tool
     * @param level - ExpertiseLevelState of this tool
     */
    public Easel(SizeState size, ExpertiseLevelState level) {
        this.basicHeightInch = 20;
        this.baseCost = 10;
        this.sizeState = size;
        this.levelState = level;
        this.parent = null;
        System.out.println("Constructor::Easel");
    }

    public void setBasicHeightInch(int height) {
        this.basicHeightInch = height;
    }

    public int getBasicHeightInch() {
        return this.basicHeightInch;
    }

    public int getHeight() {
        return (getBasicHeightInch() * sizeState.getSizeMultiplier());
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tEasel: "
                + getHeight() + "\" ("
                + levelState.getLevelDescription() + " Level) <br>");
    }

    public String toString() {
        return "Easel Object";
    }

    /**
     * Calculate final cost using sizeMultiplier and levelMultiplier.
     *
     * @return - cost of this product
     */
    public double getCost() {
        double totalCost = baseCost * levelState.getLevelMultiplier() * sizeState.getSizeMultiplier();
        System.out.println(toString() + " cost: " + totalCost);
        return totalCost;
    }
}
