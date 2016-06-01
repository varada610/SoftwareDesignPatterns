package compositePatternPackage.productPackage;

import compositePatternPackage.KitLeaf;

/**
 * Abstract class that represents brushes in painting product categories.
 *
 * @author Aditee Nagle
 */
public abstract class Brush extends KitLeaf {

    /**
     * Basic dimension of this brush
     */
    protected int basicDimension;

    /**
     * BrushTip type of this brush
     */
    protected BrushTip tipType;

    /**
     * Calculate true dimension based on SizeState.
     *
     * @return - dimension
     */
    public int getDimension() {
        return (getBasicDimension() * sizeState.getSizeMultiplier());
    }

    public int getBasicDimension() {
        return this.basicDimension;
    }

    public void setBasicDimension(int dimension) {
        this.basicDimension = dimension;
    }

    protected BrushTip getBrushTipType() {
        return this.tipType;
    }

    protected void setBrushTipType(BrushTip tip) {
        this.tipType = tip;
    }

}
