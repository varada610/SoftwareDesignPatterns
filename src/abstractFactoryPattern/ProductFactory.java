package abstractFactoryPattern;

import compositePatternPackage.productPackage.ColoringMedium;
import compositePatternPackage.productPackage.BaseMaterial;
import compositePatternPackage.*;
import compositePatternPackage.productPackage.Shade;
import compositePatternPackage.productPackage.Tools;
import java.util.List;
import java.util.Map;

/**
 * Defines interface for builders.
 *
 * @author Aditee Nagle, Varada Gurjar
 */
public interface ProductFactory {

    public BaseMaterial createBase();

    public Tools createTools(String toolName);

    public ColoringMedium createColoringMedium(Shade shade);
}
