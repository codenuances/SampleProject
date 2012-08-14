/* 
 * ===========================================================================
 * BillingClient.java
 * 
 * This class primarily is acting as client for framework.
 * It is assumed that all data which is hardcoded here will be given as an input
 * from client(browser or thick client).
 * ===========================================================================
 */
 /**
 * 
 */
 package com.sample.mckinsey.client;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import com.sample.mckinsey.inventory.Grocery;
import com.sample.mckinsey.inventory.Item;
import com.sample.mckinsey.inventory.Product;
import com.sample.mckinsey.inventory.ProductList;
import com.sample.mckinsey.user.Customer;
import com.sample.mckinsey.user.Employee;
import com.sample.mckinsey.user.User;
 /**
 * @author ash123
 *
 */
public class BillingClient {
    
    
    
    public static void main(String args[]){
    
        /* Happy Path Test cases */
        
        /* If the user is an employee of the store, he gets a 30% discount only on ITEMS 
           he has purchased.
        */        
        testDiscountForEmployeeItemsOnly();
        
        /* If the user is an employee of the store, he gets a 30% discount only on ITEMS 
           in addition if billing amount exceeds $100, $5 per $100 is applicable inclusive of GROCERY's.
        */
        testDiscountForEmployeeItemsAndGrocery();
        
        /* If the user is an Affiliate of the store, he gets a 10% discount only on ITEMS.
        */ 
        testDiscountForAffiliateItemsOnly();
        
        /* If the user is an Affiliate of the store, he gets a 10% discount only on ITEMS 
           in addition if billing amount exceeds $100, $5 per $100 is applicable inclusive of GROCERY's.
        */
        testDiscountForAffiliateItemsGrocery();
        
        /*If the user has been a customer for over 2 years, he gets a 5% discount.*/
        testDiscountForLoyaltyItemsOnly();
        
        /* If the user is an Affiliate of the store, he gets a 5% discount only on ITEMS 
        in addition if billing amount exceeds $100, $5 per $100 is applicable inclusive of GROCERY's.
        */
        testDiscountForloyaltyItemsGrocery();
        
        /* For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
         * So here we test out simply on grocerry Loyalti will not be applicable. 
        */        
        testDiscountOnlyOnGrocery();
        
        /* User is an Employee eligible for 30% but he is purchasing only Grocery .
         * So here we test out simply on grocerry and less than $100.
        */  
        testEmployeeOnlyGrocery();
        
        /*A user can get only one of the percentage based discounts on a bill.
          Customer fits into two criteria's he is an Affiliate and Loyalty(+2 years)
          Only higher discount(Affliate 10%) will apply.
        */
         testCustomerAffiliateLoyal();
        
        /* Additional business condition + Negative
         * Discount condition is one of two (Affiliate or Loyalty) and purchases just Grocery.
         * Only BillBased will apply
         * */        
        
         testCustomerAffiliateLoyalGrocerry();
        
        /*Discount condition not applicable this condition arises when user is customer
         * not an affiliate, niether registered for last two years purchase is below $100
         * */        
        testCustomerNoCriteria();
    
        /* Discount condition not applicable this condition arises when user is customer
         * not an affiliate, niether registered for last two years purchase is below
         * 100 and is mixed purchase i.e ITEM & GROCERY BOTH
         * */        
         testCustomerNoCriteriaMixed();
    }

    /**
     * 
     */
    private static void testCustomerNoCriteriaMixed() {
        User user = new Customer();
        user.setAffiliate(false);
        Calendar dateOfRegisteration = new GregorianCalendar(2012, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product apple = new Grocery();
        apple.setProductCode("002");
        apple.setName("APPLE");
        apple.setProductPrice(30);
        
        Product painting = new Item();
        painting.setProductCode("002");
        painting.setName("PAINTING");
        painting.setProductPrice(1000);
        
        productList.add(apple);
        productList.add(painting);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        
        double amountAfterPctDiscount = 0;
        if(user.getDiscount() != null){
        amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount  " + amountAfterPctDiscount);
        }else{
        amountAfterPctDiscount =  purchasedItems.totalAmountItemsNodiscount(productList);
        }
        double amountAfterBillBasedDiscount = 0;
      
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
            mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
            amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        }else{
            amountAfterBillBasedDiscount =amountAfterPctDiscount;
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        if(amountAfterPctDiscount==0 && amountAfterBillBasedDiscount==0  ){
            System.out.println("Total Due Amount Before Discount "+purchasedItems.totalAmountNodiscount(productList));
            }else{
            System.out.println("Final Amount after discount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        }
        
    }

    /**
     * 
     */
    private static void testCustomerNoCriteria() {
        User user = new Customer();
        user.setAffiliate(false);
        Calendar dateOfRegisteration = new GregorianCalendar(2012, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product apple = new Grocery();
        apple.setProductCode("002");
        apple.setName("APPLE");
        apple.setProductPrice(200);
        
        Product lettuce = new Grocery();
        lettuce.setProductCode("006");
        lettuce.setName("LETTUCE");
        lettuce.setProductPrice(100);
        
        productList.add(apple);
        productList.add(lettuce);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        
        
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        
        double amountAfterPctDiscount = 0;
        if(user.getDiscount() != null){
        amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        }
        double amountAfterBillBasedDiscount = 0;
      
        if( user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
            mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
            amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        }else{
            amountAfterBillBasedDiscount =amountAfterPctDiscount;
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        if(amountAfterPctDiscount==0 && amountAfterBillBasedDiscount==0  ){
            System.out.println("Total Due Amount Before Discount "+purchasedItems.totalAmountNodiscount(productList));
            }else{
            System.out.println("Final Amount after discount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        }
        
        
    }

    /**
     * 
     */
    private static void testCustomerAffiliateLoyal() {
        User user = new Customer();
        user.setAffiliate(true);
        Calendar dateOfRegisteration = new GregorianCalendar(2001, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product table = new Item();
        table.setProductCode("002");
        table.setName("TABLE");
        table.setProductPrice(200);
        
        Product fridge = new Item();
        fridge.setProductCode("003");
        fridge.setName("FRIDGE");
        fridge.setProductPrice(10000);
        
        Product oranges = new Grocery();
        oranges.setProductCode("004");
        oranges.setName("ORANGES");
        oranges.setProductPrice(10);
        
        
        productList.add(table);
        productList.add(fridge);
        productList.add(oranges);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount  " + amountAfterPctDiscount);
        
        double amountAfterBillBasedDiscount = 0;
      
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
            mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
            amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        }else{
            amountAfterBillBasedDiscount =amountAfterPctDiscount;
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        if(amountAfterPctDiscount==0 && amountAfterBillBasedDiscount==0  ){
            System.out.println("Total Due Amount Before Discount "+purchasedItems.totalAmountNodiscount(productList));
            }else{
            System.out.println("Final Amount after discount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        }
    }

    /**
     * For this only discount applicable is BillBased(+100)
     * 
     */
    private static void testCustomerAffiliateLoyalGrocerry() {
        User user = new Customer();
        user.setAffiliate(true);
        Calendar dateOfRegisteration = new GregorianCalendar(2001, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product apple = new Grocery();
        apple.setProductCode("002");
        apple.setName("APPLE");
        apple.setProductPrice(200);
        
        Product lettuce = new Grocery();
        lettuce.setProductCode("002");
        lettuce.setName("LETTUCE");
        lettuce.setProductPrice(100);
        
        productList.add(apple);
        productList.add(lettuce);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
        double amountAfterBillBasedDiscount = 0;
      
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
            mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
            amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        }else{
            amountAfterBillBasedDiscount =amountAfterPctDiscount;
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        if(amountAfterPctDiscount==0 && amountAfterBillBasedDiscount==0  ){
            System.out.println("Total Due Amount Before Discount "+purchasedItems.totalAmountNodiscount(productList));
            }else{
            System.out.println("Final Amount after discount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        }
        
    }

    /**
     * 
     */
    private static void testEmployeeOnlyGrocery() {
        User user = new Employee();
        user.setAffiliate(false);
        Calendar dateOfRegisteration = new GregorianCalendar(2001, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product apple = new Grocery();
        apple.setProductCode("002");
        apple.setName("APPLE");
        apple.setProductPrice(90);
        
        Product lettuce = new Grocery();
        lettuce.setProductCode("002");
        lettuce.setName("LETTUCE");
        lettuce.setProductPrice(5);
        
        productList.add(apple);
        productList.add(lettuce);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
        double amountAfterBillBasedDiscount = 0;
        
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
            mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
            amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        }else if(amountAfterPctDiscount != 0) {
            amountAfterBillBasedDiscount =amountAfterPctDiscount;
        } 
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        
        if(amountAfterPctDiscount==0 && amountAfterBillBasedDiscount==0  ){
        System.out.println("Total Due Amount Before Discount "+purchasedItems.totalAmountNodiscount(productList));
        }else{
        System.out.println("Final Amount after discount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        }
        
    }

   

    /**
     * 
     */
    private static void testDiscountOnlyOnGrocery() {
        User user = new Customer();
        user.setAffiliate(false);
        Calendar dateOfRegisteration = new GregorianCalendar(2001, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product apple = new Grocery();
        apple.setProductCode("002");
        apple.setName("APPLE");
        apple.setProductPrice(200);
        
        Product lettuce = new Grocery();
        lettuce.setProductCode("003");
        lettuce.setName("LETTUCE");
        lettuce.setProductPrice(100);
        
        productList.add(apple);
        productList.add(lettuce);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
        double amountAfterBillBasedDiscount = 0;
      
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
            mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
            amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        }else{
            amountAfterBillBasedDiscount =amountAfterPctDiscount;
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        if(amountAfterPctDiscount==0 && amountAfterBillBasedDiscount==0  ){
            System.out.println("Total Due Amount Before Discount "+purchasedItems.totalAmountNodiscount(productList));
            }else{
            System.out.println("Final Amount after discount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        }
        
    }

    /**
     * 
     */
    private static void testDiscountForLoyaltyItemsOnly() {
        User user = new Customer();
        user.setAffiliate(false);
        Calendar dateOfRegisteration = new GregorianCalendar(2001, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product apple = new Grocery();
        apple.setProductCode("002");
        apple.setName("APPLE");
        apple.setProductPrice(200);
        
        Product fan = new Item();
        fan.setProductCode("002");
        fan.setName("Fan");
        fan.setProductPrice(200);
        
        Product itemTable = new Item();
        itemTable.setProductCode("002");
        itemTable.setName("Table");
        itemTable.setProductPrice(200); 
        
        productList.add(fan);
        productList.add(itemTable);
        productList.add(apple);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
       
      
        double amountAfterBillBasedDiscount = 0;
        
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
            mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
            amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        }else{
            amountAfterBillBasedDiscount =amountAfterPctDiscount;
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        if(amountAfterPctDiscount==0 && amountAfterBillBasedDiscount==0  ){
            System.out.println("Total Due Amount Before Discount "+purchasedItems.totalAmountNodiscount(productList));
            }else{
            System.out.println("Final Amount after discount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        }
        
    }

    /**
     * 
     */
    private static void testDiscountForloyaltyItemsGrocery() {
        User user = new Customer();
        user.setAffiliate(false);
        Calendar dateOfRegisteration = new GregorianCalendar(2001, Calendar.JANUARY, 27);
        user.setRegisteredSince(dateOfRegisteration);
        ArrayList<Product>  productList = new ArrayList<Product>();

        Product fan = new Item();
        fan.setProductCode("002");
        fan.setName("Fan");
        fan.setProductPrice(200);
        
        Product itemTable = new Item();
        itemTable.setProductCode("003");
        itemTable.setName("Table");
        itemTable.setProductPrice(100); 
        
        productList.add(fan);
        productList.add(itemTable);
        
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
       
      
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            System.out.println("Purchase is more than $100 applying BillBased discount"+ user.getDiscount());
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
        mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
        double amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        System.out.println("Final Amount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        
    }

    /**
     * 
     */
    private static void testDiscountForAffiliateItemsOnly() {
        User user = new Customer();
        user.setAffiliate(true);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        
        Product item = new Item();
        item.setProductCode("002");
        item.setName("Fan");
        item.setProductPrice(200);
        
        Product itemTable = new Item();
        itemTable.setProductCode("002");
        itemTable.setName("Table");
        itemTable.setProductPrice(100); 
        productList.add(item);
        productList.add(itemTable);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
       
      
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            System.out.println("Purchase is more than $100 applying BillBased discount"+ user.getDiscount());
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
        mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
        double amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        System.out.println("Final Amount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        
        
    }

    /**
     * 
     */
    private static void testDiscountForEmployeeItemsAndGrocery() {
        User user = new Employee();
        
        ArrayList<Product>  productList = new ArrayList<Product>();
        
        Product grocery = new Grocery();
        grocery.setProductCode("001");
        grocery.setName("Oranges");
        grocery.setProductPrice(103);
        
        Product item = new Item();
        item.setProductCode("002");
        item.setName("Fan");
        item.setProductPrice(200);
        
        Product itemTable = new Item();
        itemTable.setProductCode("002");
        itemTable.setName("Table");
        itemTable.setProductPrice(100); 
        productList.add(grocery);
        productList.add(item);
        productList.add(itemTable);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
        //System.out.println("Net amount payble = " +new Invoice().generateInvoice(0, grocery.getProductPrice(), 0));
      
        
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            System.out.println("Purchase is more than $100 applying BillBased discount"+ user.getDiscount());
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
        mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
        double amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        System.out.println("Final Amount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        
    }

    /**
     * 
     */
    private static void testDiscountForEmployeeItemsOnly() {
        // TODO Auto-generated method stub
        User employee = new Employee();
        
        ArrayList<Product>  productList = new ArrayList<Product>();
        
         
        Product item = new Item();
        item.setProductCode("002");
        item.setName("Fan");
        item.setProductPrice(200);
        
        Product itemTable = new Item();
        itemTable.setProductCode("002");
        itemTable.setName("Table");
        itemTable.setProductPrice(100); 
       
        productList.add(item);
        productList.add(itemTable);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        
        System.out.println("User Type is = " +employee.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        
        employee.evaluatePctDiscountApplicable(employee);
        
        System.out.println("Discount Type Applicable is = " +employee.getDiscount());
        double amountAfterPctDiscount = employee.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
        
        
        if(employee.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            System.out.println("Purchase is more than $100 applying BillBased discount"+employee.getDiscount());
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
        mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
        double amountAfterBillBasedDiscount = employee.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        System.out.println("Final Amount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
       // System.out.println("Net amount payble = " +new Invoice().generateInvoice(0, grocery.getProductPrice(), 0)); 
    }
        
    /**
     * 
     */
    private static void testDiscountForAffiliateItemsGrocery() {
        User user = new Customer();
        user.setAffiliate(true);
        ArrayList<Product>  productList = new ArrayList<Product>();
        
  
        Product apple = new Grocery();
        apple.setProductCode("002");
        apple.setName("APPLE");
        apple.setProductPrice(200);
        
        Product item = new Item();
        item.setProductCode("003");
        item.setName("Fan");
        item.setProductPrice(250);
        
        Product itemTable = new Item();
        itemTable.setProductCode("004");
        itemTable.setName("Table");
        itemTable.setProductPrice(100); 
        
        productList.add(item);
        productList.add(itemTable);
        productList.add(apple);
        
        ProductList purchasedItems = new ProductList(productList);
        System.out.println("***********************************************************");
        System.out.println("User is = " +user.getUserType());
        System.out.println("Purchased Products & Price = " +purchasedItems.getAllItemsProductPrice());
        
        user.evaluatePctDiscountApplicable(user);
        
        System.out.println("Discount Type Applicable is = " +user.getDiscount());
        double amountAfterPctDiscount = user.getDiscount().evaluateBill(purchasedItems.purchasedProductItemPrice(purchasedItems));
        System.out.println("Amount after applying Percentage Discount " + amountAfterPctDiscount);
        
       
      
        if(user.evaluateBillBasedApplicable(amountAfterPctDiscount,purchasedItems.purchasedProductGrocerryPrice(purchasedItems))){
            System.out.println("Purchase is more than $100 applying BillBased discount "+ user.getDiscount());
        }
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        Map<Double, String> mapGroceryTotalPctDiscount = purchasedItems.purchasedProductGrocerryPrice(purchasedItems);
        mapGroceryTotalPctDiscount.put(amountAfterPctDiscount, "AMOUNT_PERCENTAGE_DISCOUNT_ITEMS");
        double amountAfterBillBasedDiscount = user.getDiscount().evaluateBill(mapGroceryTotalPctDiscount);
        System.out.println("Final Amount "+Double.valueOf(twoDForm.format(amountAfterBillBasedDiscount)));
        
        
    }
}
