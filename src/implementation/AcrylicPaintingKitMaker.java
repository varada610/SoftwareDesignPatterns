package implementation;

import abstractFactoryPattern.AcrylicProductsFactory;
import abstractFactoryPattern.PaintingProductFactory;
import compositePatternPackage.BeginnerLevelState;
import compositePatternPackage.Kit;
import compositePatternPackage.SmallSizeState;
import compositePatternPackage.productPackage.BrushTip;
import compositePatternPackage.productPackage.Shade;
import java.util.List;

/**
 * Class responsible for creating acrylic painting kit as per user's order
 * details.
 *
 * @author Aditee Nagle
 */
public class AcrylicPaintingKitMaker implements KitMaker {

    /**
     * Customer order details object.
     */
    CustomizedKitOrderDetails orderDetails;
    /**
     * Builder to be used for kit component creation.
     */
    PaintingProductFactory factory;

    /**
     * Constructor
     *
     * @param order - customer order details
     */
    public AcrylicPaintingKitMaker(CustomizedKitOrderDetails order) {
        this(order, new AcrylicProductsFactory());
    }

    /**
     * Constructor
     *
     * @param order - customer order details
     * @param factory - builder corresponding to product category
     */
    public AcrylicPaintingKitMaker(CustomizedKitOrderDetails order, PaintingProductFactory factory) {
        this.orderDetails = order;
        this.factory = factory;
        if (orderDetails.getSize() == null) {
            orderDetails.setSize(new SmallSizeState());
        }
        if (orderDetails.getLevel() == null) {
            orderDetails.setLevel(new BeginnerLevelState());
        }

        System.out.println("Constructor::AcrylicPaintingKitMaker "
                + orderDetails.getSize().getSizeDescription() + " "
                + orderDetails.getLevel().getLevelDescription());
    }

    /**
     * Method to check if order is empty.
     *
     * @return - true if empty, false otherwise
     */
    public boolean isOrderEmpty() {
        boolean noBases = (orderDetails.getBaseMaterialToMake() == 0);
        boolean noColors = (orderDetails.getColorsToMake() == null) || (orderDetails.getColorsToMake().isEmpty());
        boolean noBrushes = (orderDetails.getBrushesToMake() == null) || (orderDetails.getBrushesToMake().isEmpty());
        boolean noTools = (orderDetails.getToolsToMake() == null) || (orderDetails.getToolsToMake().isEmpty());

        return (noBases && noColors && noBrushes && noTools);
    }

    /**
     * Method to construct kit.
     *
     * @return - Kit object
     */
    @Override
    public Kit makeKit() {
        if (isOrderEmpty()) {
            throw new IllegalArgumentException("Order is empty");
        }
        //Create new Kit
        Kit newKit = new Kit(orderDetails.getKitName(), orderDetails.getSize(), orderDetails.getLevel());

        //Set size of kit
        newKit.setSizeState(orderDetails.getSize());

        //Set expertise level of kit
        newKit.setLevelState(orderDetails.getLevel());

        //If base materials are ordered, create baseMaterials and add to kit
        int numberOfBases = orderDetails.getBaseMaterialToMake();
        for (int i = 0; i < numberOfBases; i++) {
            newKit.add(factory.createBase());
        }

        //If colors are ordered, create color-set and add it to kit
        List<Shade> colorsList = orderDetails.getColorsToMake();
        if ((colorsList != null) && (!colorsList.isEmpty())) {
            Kit colorKit = new Kit("Color-set in " + orderDetails.getKitName(),
                    orderDetails.getSize(), orderDetails.getLevel());
            for (Shade aShade : colorsList) {
                colorKit.add(factory.createColoringMedium(aShade));
            }
            newKit.add(colorKit);
        }

        //If brushes are ordered, create brush-set and add it to kit
        List<BrushTip> brushList = orderDetails.getBrushesToMake();
        if ((brushList != null) && (!brushList.isEmpty())) {
            Kit brushKit = new Kit("Brush-set in " + orderDetails.getKitName(),
                    orderDetails.getSize(), orderDetails.getLevel());
            for (BrushTip tip : brushList) {
                brushKit.add(factory.createBrush(tip));
            }
            System.out.println("All brushes created");
            newKit.add(brushKit);
            System.out.println("brushes added to kit");
        }

        //If tools are ordered, create tools
        List<String> toolsList = orderDetails.getToolsToMake();
        if ((toolsList != null) && (!toolsList.isEmpty())) {
            Kit toolKit = new Kit("Tool-set in " + orderDetails.getKitName(),
                    orderDetails.getSize(), orderDetails.getLevel());
            for (String tool : toolsList) {
                toolKit.add(factory.createTools(tool));
            }
            newKit.add(toolKit);
        }

        //If previously saved kits are ordered, add them to kit
        List<Kit> savedKitList = orderDetails.getSavedKitsToAdd();
        if ((savedKitList != null) && (!savedKitList.isEmpty())) {
            for (Kit aKit : savedKitList) {
                newKit.add(aKit);
            }
        }

        return newKit;
    }

    public String toString() {
        return "AcrylicPaintingKitMaker Object";
    }
}
