package compositePatternPackage.productPackage.acrylicProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.BaseMaterial;

/**
 * Class that represents acrylic painting canvas in acrylic painting product
 * category.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class Canvas extends BaseMaterial {

    /**
     * base height of this canvas in inches
     */
    private int basicHeightInch;

    /**
     * base width of this canvas in inches
     */
    private int basicWidthInch;

    /**
     * Constructor no-args
     */
    public Canvas() {
        this(10, 8, new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param height - base height in inches
     * @param width - base width in inches
     */
    public Canvas(int height, int width) {
        this(height, width, new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param height - base height in inches
     * @param width - base width in inches
     * @param size - SizeState of this canvas
     * @param level - ExpertiseLevelState of this canvas
     */
    public Canvas(int height, int width, SizeState size, ExpertiseLevelState level) {
        this.basicHeightInch = height;
        this.basicWidthInch = width;
        this.sizeState = size;
        this.levelState = level;
        this.baseCost = 3.0;
        this.parent = null;
        System.out.println("Constructor::Canvas");
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tCanvas: "
                + getWidth() + "\" X " + getHeight()
                + "\" (" + levelState.getLevelDescription() + " Level)<br>");
    }

    public int getWidth() {
        if (this.sizeState != null) {
            return basicWidthInch * sizeState.getSizeMultiplier();
        } else {
            return basicWidthInch;
        }
    }

    public void setBasicWidth(int w) {
        this.basicWidthInch = w;
    }

    public int getHeight() {
        if (this.sizeState != null) {
            return basicHeightInch * sizeState.getSizeMultiplier();
        } else {
            return basicHeightInch;
        }
    }

    public void setBasicHeight(int h) {
        this.basicHeightInch = h;
    }

    public String toString() {
        return "Canvas Object";
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
