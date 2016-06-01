package implementation;

import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.ExpertiseLevelState;
import compositePatternPackage.Kit;
import compositePatternPackage.SizeState;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.BrushTip;
import compositePatternPackage.productPackage.GraphiteGrade;
import compositePatternPackage.productPackage.Shade;
import java.util.List;

/**
 * Class that represents objects to store customer order details.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public class CustomizedKitOrderDetails {

    /**
     * Name of this kit.
     */
    private String kitName;
    /**
     * SizeState of this kit.
     */
    private SizeState size;
    /**
     * ExpertiseLevelState of this kit.
     */
    private ExpertiseLevelState level;
    /**
     * number of base materials to make.
     */
    private int baseMaterialToMake;
    /**
     * List of shades of colors to make.
     */
    private List<Shade> colorsToMake;
    /**
     * List of BrushTip styles of brushes to make.
     */
    private List<BrushTip> brushesToMake;
    /**
     * List of Graphite grades of graphite pencils to make.
     */
    private List<GraphiteGrade> graphitePencilsToMake;
    /**
     * List of tools to make.
     */
    private List<String> toolsToMake;
    /**
     * List of names of saved kits to add.
     */
    private List<Kit> savedKitsToAdd;

    public CustomizedKitOrderDetails(String name) {
        this(name, new SmallSizeState(), new BeginnerLevelState());
    }

    public CustomizedKitOrderDetails(String name, SizeState size, ExpertiseLevelState level) {
        this.kitName = name;
        this.size = size;
        this.level = level;
        this.baseMaterialToMake = 0;
        this.brushesToMake = null;
        this.colorsToMake = null;
        this.toolsToMake = null;
    }

    public String getKitName() {
        return this.kitName;
    }

    public void setKitName(String name) {
        this.kitName = name;
    }

    public void setSize(SizeState size) {
        this.size = size;
    }

    public SizeState getSize() {
        return this.size;
    }

    public void setLevel(ExpertiseLevelState level) {
        this.level = level;
    }

    public ExpertiseLevelState getLevel() {
        return this.level;
    }

    public void setBaseMaterialsToMake(int numOfBases) {
        this.baseMaterialToMake = numOfBases;
    }

    public int getBaseMaterialToMake() {
        return this.baseMaterialToMake;
    }

    public void setBrushesToMake(List<BrushTip> brushes) {
        this.brushesToMake = brushes;
    }

    public List<BrushTip> getBrushesToMake() {
        return this.brushesToMake;
    }

    public void setGraphitePencilsToMake(List<GraphiteGrade> grades) {
        this.graphitePencilsToMake = grades;
    }

    public List<GraphiteGrade> getGraphitePencilsToMake() {
        return this.graphitePencilsToMake;
    }

    public void setColorsToMake(List<Shade> colors) {
        this.colorsToMake = colors;
    }

    public List<Shade> getColorsToMake() {
        return this.colorsToMake;
    }

    public void setToolsToMake(List<String> tools) {
        this.toolsToMake = tools;
    }

    public List<String> getToolsToMake() {
        return this.toolsToMake;
    }

    public List<Kit> getSavedKitsToAdd() {
        return this.savedKitsToAdd;
    }

    public void setSavedKitsToAdd(List<Kit> savedKits) {
        this.savedKitsToAdd = savedKits;
    }

    /**
     * Method to get kit order details.
     *
     * @param colors - list of colors
     * @param brushes - list of brushes
     * @param graphitePencils - list of graphite pencils
     * @param toolList - list of tools
     * @param kitList - list of names of saved kits
     * @return - string containing details.
     */
    public String showKitOrderDetails(List<Shade> colors, List<BrushTip> brushes, List<GraphiteGrade> graphitePencils, List<String> toolList, List<Kit> kitList) {
        String orderDetails = "";
        if (colors != null) {
            for (int index = 0; index < colors.size(); index++) {
                orderDetails = orderDetails + "Color Added : ";
                orderDetails = orderDetails + colors.get(index).toString() + "<br>";
            }
        }
        if (brushes != null) {
            for (int index = 0; index < brushes.size(); index++) {
                orderDetails = orderDetails + "Brush Added : ";
                orderDetails = orderDetails + brushes.get(index).toString() + "<br>";
            }
        }
        if (graphitePencils != null) {
            for (int index = 0; index < graphitePencils.size(); index++) {
                orderDetails = orderDetails + "Graphite Pencil Added : ";
                orderDetails = orderDetails + graphitePencils.get(index).toString() + "<br>";
            }
        }
        if (toolList != null) {
            for (int index = 0; index < toolList.size(); index++) {
                orderDetails = orderDetails + "Tool Added : ";
                orderDetails = orderDetails + toolList.get(index) + "<br>";
            }
        }
        if (kitList != null) {
            for (int index = 0; index < kitList.size(); index++) {
                orderDetails = orderDetails + "Kit Added : ";
                orderDetails = orderDetails + kitList.get(index).getKitName() + "<br>";
            }
        }
        orderDetails.replaceAll("_", " ");
        return orderDetails;
    }

    public String toString() {
        return ("CustomizedKitOrderDetails Object for " + getKitName());
    }

}
