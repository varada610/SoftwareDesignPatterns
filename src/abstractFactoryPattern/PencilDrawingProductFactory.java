package abstractFactoryPattern;

import compositePatternPackage.productPackage.BaseMaterial;
import compositePatternPackage.productPackage.Brush;
import compositePatternPackage.productPackage.BrushTip;
import compositePatternPackage.productPackage.ColoringMedium;
import compositePatternPackage.productPackage.GraphiteGrade;
import compositePatternPackage.productPackage.Shade;
import compositePatternPackage.productPackage.Tools;
import compositePatternPackage.productPackage.acrylicProductPackage.AcrylicBrush;
import compositePatternPackage.productPackage.pencildrawingproducts.ColoredPencil;
import compositePatternPackage.productPackage.pencildrawingproducts.DrawingPaper;
import compositePatternPackage.productPackage.pencildrawingproducts.GraphitePencil;
import compositePatternPackage.productPackage.toolsProductPackage.Easel;
import compositePatternPackage.productPackage.toolsProductPackage.Eraser;
import compositePatternPackage.productPackage.toolsProductPackage.Palette;
import compositePatternPackage.productPackage.toolsProductPackage.RegularPencil;
import compositePatternPackage.productPackage.toolsProductPackage.Sharpener;

/**
 * This class creates products from pencil drawing product category.
 * @author Aditee Nagle
 */
public class PencilDrawingProductFactory implements DrawingProductFactory {
    /**
     * Creates new drawing paper
     * @return DrawingPaper
     */
    public BaseMaterial createBase() {
        return new DrawingPaper();
    }

    /**
     * Creates a tool specified by String toolname.
     * @param toolName - name of tool to be created
     * @return Tool
     */
    public Tools createTools(String toolName) {
        switch (toolName) {
            case "palette":
                return new Palette();
            case "pencil":
                return new RegularPencil();
            case "easel":
                return new Easel();
            case "sharpener":
                return new Sharpener();
            case "eraser":
                return new Eraser();
            default:
                return null;
        }

    }

    /**
     * Creates color pencil of specified shade
     * @param shade - shade of acrylic color to be created
     * @return - color pencil
     */
    public ColoringMedium createColoringMedium(Shade shade) {
        return new ColoredPencil(shade);
    }

    /**
     * Creates GraphitePencil of specified grade
     * @param grade - GraphiteGrade of pencil to be created
     * @return - Graphite Pencil
     */
    public GraphitePencil createGraphitePencil(GraphiteGrade grade) {
        return new GraphitePencil(grade);
    }    

    /**
     * String representation for product category.
     * @return - string of product category
     */
    public String toString() {
        return "PencilDrawingProductFactory Object";
    }

}
