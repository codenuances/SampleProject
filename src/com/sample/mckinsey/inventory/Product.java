/*
 * =========================================================================== Product.java
 * Interface for all Retail Products Any new category of Product must implement this interface.
 * ===========================================================================
 */
/**
 * 
 */
package com.sample.mckinsey.inventory;

/**
 * @author ash123
 * 
 */
public interface Product {

    String PRODUCT_TYPE_GROCERY = "GROCERY";
    String PRODUCT_TYPE_ITEM = "ITEM";

    /**
     * @param string
     */
    void setProductCode(String string);

    /**
     * @param i
     */
    void setProductPrice(double i);

    /**
     * @return
     */
    String getProductCode();

    /**
     * @return
     */
    double getProductPrice();

    /**
     * @return
     */
    String getName();

    /**
     * @return
     */
    String getType();

    /**
     * @param string
     */
    void setName(String string);

}
