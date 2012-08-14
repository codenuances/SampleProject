/*
 * =========================================================================== Employee.java
 * Represents EmployeeVO and evaluation of related discounts as applicable
 * ===========================================================================
 */
/**
 * 
 */
package com.sample.mckinsey.user;

import com.sample.mckinsey.pricing.Bill;
import com.sample.mckinsey.pricing.EmployeeDiscount;

/**
 * @author ash123
 * 
 */
public class Employee extends User {

    private Bill bill;

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.user.User#getUserType()
     */
    @Override
    public String getUserType() {
        // TODO Auto-generated method stub
        return "EMPLOYEE";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.user.User#setUserType(java.lang.String)
     */
    @Override
    public void setUserType(String userType) {

    }

    /**
     * @return the discount
     */
    public Bill getDiscount() {
        return bill;
    }

    /**
     * @param discount
     *            the discount to set
     */
    public void setDiscount(Bill bill) {
        this.bill = bill;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sample.mckinsey.user.User#evaluatePctDiscountApplicable(com.sample.mckinsey.user.User)
     */
    @Override
    public boolean evaluatePctDiscountApplicable(User user) {
        setDiscount(new EmployeeDiscount(bill));
        return true;
    }

}
