package compositePatternPackage.productPackage.acrylicProductPackage;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.ColoringMedium;
import compositePatternPackage.productPackage.Shade;

/**
 * Class that represents acrylic painting color in acrylic painting product
 * category.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class AcrylicColor extends ColoringMedium {

    /**
     * Volume of paint contained in basic color tube.
     */
    private int basicVolumeOz;

    /**
     * Constructor
     *
     * @param shade - shade of this color
     */
    public AcrylicColor(Shade shade) {
        this(shade, new SmallSizeState(), new BeginnerLevelState());
        System.out.println("Constructor::AcrylicColor " + shade);

    }

    /**
     * Constructor
     *
     * @param shade - shade of this color
     * @param size - SizeState of this color
     * @param level - ExpertiseLevelState of this color
     */
    public AcrylicColor(Shade shade, SizeState size, ExpertiseLevelState level) {
        this.colorShade = shade;
        this.sizeState = size;
        this.levelState = level;
        this.basicVolumeOz = 2;
        this.baseCost = 6;
        this.parent = null;
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tAcrylic Color: " + this.colorShade + ", "
                + getPaintVolumeOz() + " oz tube ("
                + levelState.getLevelDescription() + " Level)<br>");
    }

    public int getPaintVolumeOz() {
        return (getBasicVolumeOz() * sizeState.getSizeMultiplier());
    }

    public void setBasicVolumeOz(int oz) {
        this.basicVolumeOz = oz;
    }

    public int getBasicVolumeOz() {
        return this.basicVolumeOz;
    }

    public String toString() {
        return "AcrylicColor Object";
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
