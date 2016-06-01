package abstractFactoryPattern;

import compositePatternPackage.productPackage.Brush;
import compositePatternPackage.productPackage.BrushTip;

/**
 * Defines interface for painting product category builders.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public interface PaintingProductFactory extends ProductFactory {

    public Brush createBrush(BrushTip tip);
}
