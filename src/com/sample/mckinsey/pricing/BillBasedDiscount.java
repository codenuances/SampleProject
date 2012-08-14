/* 
 * ===========================================================================
 * PricedBasedDiscount.java
 * 
 * Implements Bill represents discounts Based on Billing.
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
public class BillBasedDiscount implements Bill{

    
    
    public double evaluateDiscount(double amount) {
        System.out.println("Applying Billbased discount on Discounted Items & Grocery");
       double totalAmountBeforeDiscount=amount;
       int discountAmount =0;
        
       discountAmount =  (int)((totalAmountBeforeDiscount/100)*BILL_BASED_DISCOUNT);
            
        
        
        
        return discountAmount;
    }
    /* (non-Javadoc)
     * @see com.sample.mckinsey.pricing.Discount#evaluateDiscount(java.util.Map)
     */
    @Override
    public double evaluateBill(Map<Double, String> map) {
        // TODO Auto-generated method stub
        double amount= 0;
        double amountAfterPercentageDiscount =0;
        for (Map.Entry<Double,String> entry : map.entrySet()) {
            if(entry.getValue().equals("AMOUNT_PERCENTAGE_DISCOUNT_ITEMS")){
                amountAfterPercentageDiscount = entry.getKey();
                              
            }else{
            Double price = entry.getKey();
            amount = amount+price;
     
            }
        }
        amount=amountAfterPercentageDiscount+amount;
        
        double discount = evaluateDiscount(amount);
        amount = amount-discount;
        return amount;
    }

}
