package compositePatternPackage;

/**
 * Class represents small size state of a component.
 *
 * @author Aditee Nagle
 */
public class SmallSizeState extends SizeState {

    public SmallSizeState() {
        this.sizeMultiplier = 1;
    }

    public String getSizeDescription() {
        return "Small";
    }

    public String toString() {
        return "SmallSizeState Object";
    }
}
