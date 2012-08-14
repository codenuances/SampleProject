/*
 * =========================================================================== Grocery.java
 * Represents ValueObject for Grocery Products
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
public class Grocery implements Product {
    String productCode;
    double productPrice;
    String name;
    String type;

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     *            the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice
     *            the productPrice to set
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.inventory.Product#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.inventory.Product#getType()
     */
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return PRODUCT_TYPE_GROCERY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.inventory.Product#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub
        this.name = name;

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(productPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Grocery other = (Grocery) obj;
        if (Double.doubleToLongBits(productPrice) != Double.doubleToLongBits(other.productPrice))
            return false;
        return true;
    }

}
