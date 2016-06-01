package compositePatternPackage;

/**
 * Class represents large size state of a component.
 *
 * @author Aditee Nagle
 */
public class LargeSizeState extends SizeState {

    public LargeSizeState() {
        this.sizeMultiplier = 2;
    }

    public String getSizeDescription() {
        return "Large";
    }

    public String toString() {
        return "LargeSizeState Object";
    }
}
