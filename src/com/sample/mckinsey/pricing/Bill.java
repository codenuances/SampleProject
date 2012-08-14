/*
 * =========================================================================== Bill.java Interface
 * which must be implemented for any types of Bill
 * ===========================================================================
 */

package com.sample.mckinsey.pricing;

import java.util.Map;

/**
 * @author ash123
 * 
 */
public interface Bill {

    public static final double EMPLOYEE_DISCOUNT_PERCENTAGE = 30;
    public static final double AFFILIATE_DISCOUNT_PERCENTAGE = 10;
    public static final double LOYALTY_DISCOUNT_PERCENTAGE = 5;
    public static final double BILL_BASED_DISCOUNT = 5;

    public double evaluateBill(Map<Double, String> map);

}
