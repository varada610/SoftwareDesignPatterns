package implementation;

import compositePatternPackage.Kit;

/**
 * Defines interface for classes responsible for making kits. Directors in
 * builder pattern.
 *
 * @author Aditee Nagle
 */
public interface KitMaker {

    public Kit makeKit();
}
