/*
 * =========================================================================== User.java
 *
 * Base class for Customer and Employee, kept it abstract as default behavior 
 * needs to be implemented for general user of system.
 * ===========================================================================
 */
/**
 * 
 */
package com.sample.mckinsey.user;

import java.util.Calendar;
import java.util.Map;

import com.sample.mckinsey.pricing.Bill;
import com.sample.mckinsey.pricing.BillBasedDiscount;

/**
 * @author ash123
 * 
 */
public abstract class User {
    private Bill discount;
    private boolean affiliate;
    private Calendar dateOfRegisteration;

    /**
     * @return the discount
     */
    public Bill getDiscount() {
        return discount;
    }

    /**
     * @param discount
     *            the discount to set
     */
    public void setDiscount(Bill discount) {
        this.discount = discount;
    }

    /**
     * @return the userType
     */
    public abstract String getUserType();

    /**
     * @param userType
     *            the userType to set
     */
    public abstract void setUserType(String userType);

    /**
     * @param user
     * 
     */
    public abstract boolean evaluatePctDiscountApplicable(User user);

    /**
     * @param amountAfterPctDiscount
     * 
     */
    public boolean evaluateBillBasedApplicable(double amountAfterPctDiscount, Map<Double, String> map) {
        boolean isApplicable = false;
        double groceryPrice = 0;
        double totalPrice = 0;

        for (Map.Entry<Double, String> entry : map.entrySet()) {
            Double price = entry.getKey();
            groceryPrice = groceryPrice + price;
        }
        totalPrice = amountAfterPctDiscount + groceryPrice;
        if (totalPrice > 100) {
            this.setDiscount(new BillBasedDiscount());
            isApplicable = true;
        }
        return isApplicable;
    }

    /**
     * @return
     */
    public boolean isAffiliate() {

        return this.affiliate;
    }

    public void setAffiliate(boolean affiliate) {
        this.affiliate = affiliate;

    }

    /**
     * @param dateOfRegisteration2
     * @param dateOfRegisteration
     */
    public void setRegisteredSince(Calendar dateOfRegisteration) {
        this.dateOfRegisteration = dateOfRegisteration;

    }

    /**
     * @return the dateOfRegisteration
     */
    public Calendar getDateOfRegisteration() {
        return dateOfRegisteration;
    }

    /**
     * @param dateOfRegisteration
     *            the dateOfRegisteration to set
     */
    public void setDateOfRegisteration(Calendar dateOfRegisteration) {
        this.dateOfRegisteration = dateOfRegisteration;
    }

}
