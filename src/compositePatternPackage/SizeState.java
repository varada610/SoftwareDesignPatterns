package compositePatternPackage;

import java.io.Serializable;

/**
 * Class represents size of a component. Components can come in different sizes.
 * Their dimensions and cost calculations change based on their size.
 *
 * @author Aditee Nagle
 */
public abstract class SizeState implements Serializable {

    /**
     * Integer Multiplier for this state
     */
    protected int sizeMultiplier;

    /**
     * Method to get sizeMultiplier of this state.
     *
     * @return - sizeMultiplier
     */
    public int getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    /**
     * Method to return description of this state.
     *
     * @return - string with description
     */
    public abstract String getSizeDescription();

}
