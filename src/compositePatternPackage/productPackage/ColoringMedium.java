package compositePatternPackage.productPackage;

import compositePatternPackage.KitLeaf;

/**
 * Abstract class that represents coloring medium in all product categories.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public abstract class ColoringMedium extends KitLeaf {

    protected Shade colorShade;

    protected Shade getShade() {
        return this.colorShade;
    }

    protected void setShade(Shade shade) {
        this.colorShade = shade;
    }
}
