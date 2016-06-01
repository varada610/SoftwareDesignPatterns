package abstractFactoryPattern;

import compositePatternPackage.productPackage.ColoringMedium;
import compositePatternPackage.productPackage.BaseMaterial;
import compositePatternPackage.productPackage.acrylicProductPackage.Canvas;
import compositePatternPackage.productPackage.Brush;
import compositePatternPackage.productPackage.toolsProductPackage.Sharpener;
import compositePatternPackage.productPackage.BrushTip;
import compositePatternPackage.productPackage.Tools;
import compositePatternPackage.productPackage.Shade;
import compositePatternPackage.productPackage.toolsProductPackage.Eraser;
import compositePatternPackage.productPackage.toolsProductPackage.RegularPencil;
import compositePatternPackage.productPackage.toolsProductPackage.Easel;
import compositePatternPackage.productPackage.acrylicProductPackage.AcrylicBrush;
import compositePatternPackage.productPackage.acrylicProductPackage.AcrylicColor;
import compositePatternPackage.productPackage.toolsProductPackage.Palette;

/**
 * This class creates products from acrylic painting product category.
 * 
 * @author Aditee Nagle, Varada Gurjar
 */
public class AcrylicProductsFactory implements PaintingProductFactory {
    /**
     * Creates new canvas
     * @return Canvas
     */
    public BaseMaterial createBase() {
        return new Canvas();
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
     * Creates acrylic color specified by Shade shade
     * @param shade - shade of acrylic color to be created
     * @return - acrylic color
     */
    public ColoringMedium createColoringMedium(Shade shade) {
        return new AcrylicColor(shade);
    }

    /**
     * Creates brush of specified BrushTip
     * @param tip - BrushTip of brush to be created
     * @return - brush
     */
    public Brush createBrush(BrushTip tip) {
        return new AcrylicBrush(tip);
    }

    /**
     * String representation for product category.
     * @return - string of product category
     */
    public String toString() {
        return "AcrylicProductsFactory Object";
    }

}
