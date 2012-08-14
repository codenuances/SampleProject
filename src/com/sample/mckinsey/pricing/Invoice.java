/*
 * =========================================================================== INvoice.java
 * Invoice represents finally generated invoice for the User.
 * ===========================================================================
 */
/**
 * 
 */
package com.sample.mckinsey.pricing;

/**
 * @author ash123
 * 
 */
public class Invoice {

    Bill discount;
    private double finalPrice = 0;

    public final double generateInvoice(final double priceItem, final double priceItemGrocery, final double discount) {

        /*
         * itemAfterDiscount = priceItem-discount; totalPrice = itemAfterDiscount + priceitemGrocery
         * if(totalPrice>100){ additionalDiscount = totalprice - (totalPrice%5); } finalPrice =
         * totalprice-additionalDiscount;
         */
        return finalPrice;
    }
}
