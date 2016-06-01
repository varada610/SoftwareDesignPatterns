/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compositePatternPackage.productPackage.pencildrawingproducts;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.BaseMaterial;

/**
 * Class that represents drawing paper in pencil drawing product category.
 *
 * @author Aditee Nagle
 */
public class DrawingPaper extends BaseMaterial {

    /**
     * base height of this drawing paper in inches
     */
    private int basicHeightInch;
    /**
     * base width of this drawing paper in inches
     */
    private int basicWidthInch;

    /**
     * Constructor no-args
     */
    public DrawingPaper() {
        this(10, 8, new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param height - base height in inches
     * @param width - base width in inches
     */
    public DrawingPaper(int height, int width) {
        this(height, width, new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor
     *
     * @param height - base height in inches
     * @param width - base width in inches
     * @param size - SizeState of this drawing paper
     * @param level - ExpertiseLevelState of this drawing paper
     */
    public DrawingPaper(int height, int width, SizeState size, ExpertiseLevelState level) {
        this.basicHeightInch = height;
        this.basicWidthInch = width;
        this.sizeState = size;
        this.levelState = level;
        this.baseCost = 3.0;
        this.parent = null;
        System.out.println("Constructor::DrawingPaper");
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tDrawing Paper: "
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
        return "DrawingPaper Object";
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
