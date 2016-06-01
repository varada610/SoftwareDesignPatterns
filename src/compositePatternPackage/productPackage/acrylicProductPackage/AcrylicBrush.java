package compositePatternPackage.productPackage.acrylicProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.Brush;
import compositePatternPackage.productPackage.BrushTip;

/**
 * Class that represents acrylic painting brush in acrylic painting product
 * category.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class AcrylicBrush extends Brush {

    /**
     * Constructor
     *
     * @param tip - BrushTip type of this brush
     */
    public AcrylicBrush(BrushTip tip) {
        this(tip, new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param tip - BrushTip type of this brush
     * @param size - SizeState of this brush
     * @param level - ExpertiseLevelState of this brush
     */
    public AcrylicBrush(BrushTip tip, SizeState size, ExpertiseLevelState level) {
        this.basicDimension = 4;     //default dimension set here
        this.tipType = tip;
        this.sizeState = size;
        this.levelState = level;
        this.baseCost = 2;
        this.parent = null;
        System.out.println("Constructor::AcrylicBrush " + tipType);
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tAcrylic Brush: " + this.tipType + ", "
                + "Size " + getDimension() + " ("
                + levelState.getLevelDescription() + " Level)<br>");
    }

    public String toString() {
        return "AcrylicBrush Object";
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
