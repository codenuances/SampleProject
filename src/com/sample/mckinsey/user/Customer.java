/*
 * =========================================================================== Customer.java
 * 
 * Created on Aug 10, 2012
 * Represents CustomerVO and evaluation of related discounts as applicable
 * ===========================================================================
 */
/**
 * 
 */
package com.sample.mckinsey.user;

import java.util.Calendar;

import com.sample.mckinsey.pricing.AffiliateDiscount;
import com.sample.mckinsey.pricing.Bill;
import com.sample.mckinsey.pricing.LoyalityDiscount;

/**
 * @author ash123
 * 
 */
public class Customer extends User {

    private Bill bill;
    private boolean status;

    /**
     * @return the bill
     */
    public Bill getBill() {
        return bill;
    }

    /**
     * @param bill
     *            the bill to set
     */
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.user.User#getUserType()
     */
    @Override
    public String getUserType() {
        // TODO Auto-generated method stub
        return "CUSTOMER";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.user.User#setUserType(java.lang.String)
     */
    @Override
    public void setUserType(String userType) {

    }

    public boolean evaluatePctDiscountApplicable(User user) {
        // check if customer is affiliate or if registered since
        // last two years
        // Date dateRegistered = new Date("12/02/2011");

        if (user.isAffiliate()) {

            setDiscount(new AffiliateDiscount(bill));
            return true;
        } else if (getRegisteredSince(user.getDateOfRegisteration()) > 2) {

            setDiscount(new LoyalityDiscount(bill));
            System.out.println("Registered for more than two years");
            return true;

        } else {
            System.out.println("Boss sorry, no percentage based discounts for you.."
                    + ".ok here is the thing....we give you $5 discount on purchase of each $100....Cool?");

        }

        return false;
    }

    /**
     * @return
     */
    public boolean isAffiliate() {
        // TODO Auto-generated method stub
        return status;
    }

    public void setAffiliate(boolean status) {
        // TODO Auto-generated method stub
        this.status = status;
    }

    public int getRegisteredSince(Calendar date) {
        Calendar registeredSince = date;

        Calendar today = Calendar.getInstance();
        int curYear = today.get(Calendar.YEAR);
        int curMonth = today.get(Calendar.MONTH);
        int curDay = today.get(Calendar.DAY_OF_MONTH);

        int year = registeredSince.get(Calendar.YEAR);
        int month = registeredSince.get(Calendar.MONTH);
        int day = registeredSince.get(Calendar.DAY_OF_MONTH);

        int registeredYears = curYear - year;
        if (curMonth < month || (month == curMonth && curDay < day)) {
            registeredYears--;
        }
        System.out.println("Registered years " + registeredYears);
        return registeredYears;

    }

}
