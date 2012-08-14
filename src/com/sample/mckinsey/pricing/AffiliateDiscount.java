/*
 * ===========================================================================
 * AffiliateDiscount.java Implements PctDisountedBillDecorator represents discounts for affiliates.
 * ===========================================================================
 */
package com.sample.mckinsey.pricing;

import java.util.Map;

/**
 * @author ash123
 * 
 */
public class AffiliateDiscount extends PctDisountedBillDecorator {

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.pricing.Discount#evaluateDiscount(java.util.Map)
     */

    /**
     * @param pctDisountedBillDecorator
     */
    public AffiliateDiscount(Bill pctDisountedBillDecorator) {
        super(pctDisountedBillDecorator);

    }

    public double evaluateDiscount(double amount) {
        System.out.println("Applying Affiliate discount on Items.");

        double discount = (AFFILIATE_DISCOUNT_PERCENTAGE / 100) * amount;
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
