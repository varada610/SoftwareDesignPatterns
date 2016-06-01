package compositePatternPackage;

import java.io.Serializable;

/**
 * Class represents expertise level of a component. Components can come in
 * different expertise level. Their cost calculations change based on their
 * expertise level.
 *
 * @author Aditee Nagle
 */
public abstract class ExpertiseLevelState implements Serializable {

    /**
     * Integer Multiplier for this level
     */
    protected int levelMultiplier;

    /**
     * Method to get levelMultiplier of this level.
     *
     * @return - sizeMultiplier
     */
    public int getLevelMultiplier() {
        return this.levelMultiplier;
    }

    /**
     * Method to return description of this level.
     *
     * @return - string with description
     */
    public abstract String getLevelDescription();
}
