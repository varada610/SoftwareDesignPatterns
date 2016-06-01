package compositePatternPackage;

/**
 * Class represents Advanced level state of a component.
 *
 * @author Aditee Nagle
 */
public class AdvancedLevelState extends ExpertiseLevelState {

    public AdvancedLevelState() {
        this.levelMultiplier = 3;
    }

    public String getLevelDescription() {
        return "Advanced";
    }

    public String toString() {
        return "AdvancedLevelState Object";
    }
}
