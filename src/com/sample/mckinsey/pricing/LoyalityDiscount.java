/*
 * =========================================================================== LoyalityDiscount.java
 * Discount based on Loyallty
 * ===========================================================================
 */
/**
 * 
 */
package com.sample.mckinsey.pricing;

import java.util.Map;

/**
 * @author ash123
 * 
 */
public class LoyalityDiscount extends PctDisountedBillDecorator {

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.pricing.Discount#evaluateDiscount(java.util.Map)
     */

    /**
     * @param pctDisountedBillDecorator
     */
    public LoyalityDiscount(Bill pctDisountedBillDecorator) {
        super(pctDisountedBillDecorator);
        // TODO Auto-generated constructor stub
    }

    public double evaluateDiscount(double amount) {
        System.out.println("Applying Loyalty Discount on Items.");

        double discount = (LOYALTY_DISCOUNT_PERCENTAGE / 100) * amount;
        System.out.println("Discount is for " + discount);
        return discount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.pricing.PctDisountedBillDecorator#evaluateBill(java.util.Map)
     */
    @Override
    public double evaluateBill(Map<Double, String> map) {
        // TODO Auto-generated method stub
        double amount = 0;
        for (Map.Entry<Double, String> entry : map.entrySet()) {
            Double price = entry.getKey();
            amount = amount + price;
        }
        double discount = evaluateDiscount(amount);
        amount = amount - discount;
        return amount;
    }

}
