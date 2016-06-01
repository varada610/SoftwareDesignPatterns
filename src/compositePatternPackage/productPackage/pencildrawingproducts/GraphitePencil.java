package compositePatternPackage.productPackage.pencildrawingproducts;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.KitLeaf;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.GraphiteGrade;

/**
 * Class that represents graphite pencil in pencil drawing product category.
 *
 * @author Aditee Nagle
 */
public class GraphitePencil extends KitLeaf {

    /**
     * base dimension of this pencil.
     */
    private int basicDimension;

    /**
     * Graphite grade of this pencil.
     */
    private GraphiteGrade grade;

    /**
     * Constructor.
     *
     * @param grade - grade of this pencil
     */
    public GraphitePencil(GraphiteGrade grade) {
        this(grade, new SmallSizeState(), new BeginnerLevelState());
    }

    /**
     * Constructor.
     *
     * @param grade - grade of this pencil
     * @param size - SizeState of this graphite pencil
     * @param level - ExpertiseLevelState of this graphite pencil
     */
    public GraphitePencil(GraphiteGrade grade, SizeState size, ExpertiseLevelState level) {
        this.basicDimension = 3;     //default dimension set here
        this.grade = grade;
        this.sizeState = size;
        this.levelState = level;
        this.baseCost = 0.1;
        this.parent = null;
        System.out.println("Constructor::GraphitePencil " + grade);
    }

    public int getDimension() {
        return (getBasicDimension() * sizeState.getSizeMultiplier());
    }

    public int getBasicDimension() {
        return this.basicDimension;
    }

    public void setBasicDimension(int dimension) {
        this.basicDimension = dimension;
    }

    protected GraphiteGrade getGraphiteGrade() {
        return this.grade;
    }

    protected void setGraphiteGrade(GraphiteGrade grade) {
        this.grade = grade;
    }

    /**
     * Get details of this product including size and expertise level.
     *
     * @return - string containing details
     */
    @Override
    public String showDetails() {
        return ("\tGraphite Pencil: " + this.grade + ", "
                + getDimension() + "\" ("
                + levelState.getLevelDescription() + " Level)<br>");
    }

    public String toString() {
        return "GraphitePencil Object";
    }

    /**
     * Calculate final cost using sizeMultiplier and levelMultiplier.
     *
     * @return - cost of this product
     */
    @Override
    public double getCost() {
        double totalCost = baseCost * levelState.getLevelMultiplier() * sizeState.getSizeMultiplier();
        System.out.println(toString() + " cost: " + totalCost);
        return totalCost;
    }

}
