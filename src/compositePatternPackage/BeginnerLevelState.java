package compositePatternPackage;

/**
 * Class represents Beginner level state of a component.
 *
 * @author Aditee Nagle
 */
public class BeginnerLevelState extends ExpertiseLevelState {

    public BeginnerLevelState() {
        this.levelMultiplier = 1;
    }

    public String getLevelDescription() {
        return "Beginner";
    }

    public String toString() {
        return "BeginnerLevelState Object";
    }
}
