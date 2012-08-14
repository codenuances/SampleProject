/*
 * ===========================================================================
 * DisountedBillDecorator.java Implements decorator pattern to extend or modify the behaviour of ‘an
 * instance’ at runtime decorator design pattern is used. Inheritance is used to extend the
 * abilities of ‘a class’. Unlike inheritance, you can choose any single object of a class and
 * modify its behaviour leaving the other instances unmodified.
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
public abstract class PctDisountedBillDecorator implements Bill {

    protected Bill pctDisountedBillDecorator;

    public PctDisountedBillDecorator(Bill pctDisountedBillDecorator) {
        this.pctDisountedBillDecorator = pctDisountedBillDecorator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sample.mckinsey.pricing.Bill#evaluateBill(java.util.Map)
     */
    @Override
    public abstract double evaluateBill(Map<Double, String> map);

}
