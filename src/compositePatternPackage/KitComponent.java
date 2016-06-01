package compositePatternPackage;

import java.io.Serializable;

/**
 * Abstract class that represents components that can be added to kits.
 *
 * @author Aditee Nagle
 */
public abstract class KitComponent implements Cloneable, Serializable {

    protected SizeState sizeState;
    protected ExpertiseLevelState levelState;
    protected Kit parent;

    /**
     * Calculate cost of this component
     *
     * @return - cost
     */
    public abstract double getCost();

    /**
     * Get details of this component
     *
     * @return - string containing details
     */
    public abstract String showDetails();

    /**
     * Method to add components to a composite kit
     *
     * @param component - component to be added
     */
    public abstract void add(KitComponent component);

    /**
     * Method to remove component from composite kit.
     *
     * @param component - component to be removed
     * @return - true if removed, false otherwise
     */
    public abstract boolean remove(KitComponent component);

    /**
     * Set SizeState of this component.
     *
     * @param size - SizeState
     */
    public void setSizeState(SizeState size) {
        this.sizeState = size;
    }

    /**
     * Get SizeState of this component.
     *
     * @return - SizeState
     */
    public SizeState getSizeState() {
        return this.sizeState;
    }

    /**
     * Set ExpertiseLevelState of this component.
     *
     * @param level - ExpertiseLevelState
     */
    public void setLevelState(ExpertiseLevelState level) {
        this.levelState = level;
    }

    /**
     * Get ExpertiseLevelState of this component.
     *
     * @return - ExpertiseLevelState
     */
    public ExpertiseLevelState getLevelState() {
        return this.levelState;
    }

    /**
     * Set parent of this component.
     *
     * @param parentKit - parent of type Kit
     */
    public void setParent(Kit parentKit) {
        this.parent = parentKit;
    }

    /**
     * Get parent of this component.
     *
     * @return - parent of type Kit
     */
    public Kit getParent() {
        return this.parent;
    }

    /**
     * Method to check if this component is a composite or leaf.
     *
     * @return - true if composite, false if leaf
     */
    public boolean isComposite() {
        return false;
    }

}
