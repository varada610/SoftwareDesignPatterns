package compositePatternPackage;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a kit.
 *
 * @author Aditee Nagle
 */
public class Kit extends KitComponent implements Serializable {

    /**
     * Name of this kit, used for saving in repository.
     */
    private String name;

    /**
     * Components included in this kit.
     */
    private Set<KitComponent> children;

    /**
     * Constructor
     *
     * @param name - name of this kit
     * @param size - SizeState of this kit
     * @param level - ExpertiseLevelState of this kit
     */
    public Kit(String name, SizeState size, ExpertiseLevelState level) {
        this.name = name;
        this.sizeState = size;
        this.levelState = level;
        this.children = new HashSet<KitComponent>();
        this.parent = null;
        System.out.println("Constructor::Kit " + name);
    }

    /**
     * Calculate total cost by adding costs of included components.
     *
     * @return - total cost of this kit
     */
    @Override
    public double getCost() {
        double totalCost = 0;
        for (KitComponent child : children) {
            totalCost += child.getCost();
        }
        return totalCost;
    }

    /**
     * Get detailed description of this kit.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        String kitDetails = "Kit: " + name + "<br>";
        for (KitComponent child : children) {
            kitDetails = kitDetails + child.showDetails();
        }
        return kitDetails;
    }

    /**
     * Method to add components to a composite kit
     *
     * @param component - component to be added
     */
    public void add(KitComponent component) {
        System.out.println("Adding " + component.toString() + " to " + this.getKitName());
        component.setParent(this);
        if (!component.isComposite()) {
            component.setSizeState(sizeState);
            component.setLevelState(levelState);
        }
        children.add(component);

    }

    /**
     * Method to remove component from composite kit.
     *
     * @param component - component to be removed
     * @return - true if removed, false otherwise
     */
    public boolean remove(KitComponent component) {
        boolean success = children.remove(component);
        if (!success) {
            for (KitComponent child : children) {
                success = child.remove(component);
                if (success) {
                    break;
                }
            }
        }
        return success;
    }

    /**
     * Set SizeState of this component. SizeState of all leaf components in this
     * kit is also set to specified size.
     *
     * @param size - SizeState
     */
    @Override
    public void setSizeState(SizeState size) {
        this.sizeState = size;
        for (KitComponent child : children) {
            if (!child.isComposite()) {
                child.setSizeState(size);
            }
        }
    }

    /**
     * Set ExpertiseLevelState of this component. ExpertiseLevelState of all
     * leaf components in this kit is also set to specified level.
     *
     * @param level - ExpertiseLevelState
     */
    @Override
    public void setLevelState(ExpertiseLevelState level) {
        this.levelState = level;
        for (KitComponent child : children) {
            if (!child.isComposite()) {
                child.setLevelState(level);
            }
        }
    }

    public String getKitName() {
        return this.name;
    }

    public String toString() {
        return "Kit Object";
    }

    /**
     * Method to check if this component is a composite or leaf.
     *
     * @return - true if composite, false if leaf
     */
    @Override
    public boolean isComposite() {
        return true;
    }
}
