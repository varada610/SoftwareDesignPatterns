package abstractFactoryPattern;

import compositePatternPackage.productPackage.GraphiteGrade;
import compositePatternPackage.productPackage.pencildrawingproducts.GraphitePencil;

/**
 * Defines interface for Drawing products category builders.
 *
 * @author Aditee Nagle
 */
public interface DrawingProductFactory extends ProductFactory {

    public GraphitePencil createGraphitePencil(GraphiteGrade grade);
}
