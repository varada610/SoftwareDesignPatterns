package compositePatternPackage.productPackage.pencildrawingproducts;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.ColoringMedium;
import compositePatternPackage.productPackage.Shade;

/**
 * Class that represents Colored pencil in pencil drawing product category.
 *
 * @author Aditee Nagle
 */
public class ColoredPencil extends ColoringMedium {

    /**
     * base height of this pencil in inches.
     */
    private int basicHeightInch;

    /**
     * Constructor
     *
     * @param shade - shade of this color pencil
     */
    public ColoredPencil(Shade shade) {
        this(shade, new SmallSizeState(), new BeginnerLevelState());
        System.out.println("Constructor::ColoredPencil " + shade);

    }

    /**
     * Constructor
     *
     * @param shade - shade of this color pencil
     * @param size - SizeState of this color pencil
     * @param level - ExpertiseLevelState of this color pencil
     */
    public ColoredPencil(Shade shade, SizeState size, ExpertiseLevelState level) {
        this.colorShade = shade;
        this.sizeState = size;
        this.levelState = level;
        this.basicHeightInch = 3;
        this.baseCost = 0.1;
        this.parent = null;
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tColored Pencil: " + this.colorShade + ", "
                + getPencilHeightInch() + "\" ("
                + levelState.getLevelDescription() + " Level)<br>");
    }

    public int getPencilHeightInch() {
        return (getBasicHeightInch() * sizeState.getSizeMultiplier());
    }

    public void setBasicHeightInch(int height) {
        this.basicHeightInch = height;
    }

    public int getBasicHeightInch() {
        return this.basicHeightInch;
    }

    public String toString() {
        return "Colored Pencil Object";
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
