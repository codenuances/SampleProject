/*
 * =========================================================================== ProductList.java
 * Represents DAO for products to get Products based on price,type,name etc.
 * ===========================================================================
 */
package com.sample.mckinsey.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author ash123
 * 
 */
public class ProductList {

    Map<Double, String> productPriceMap = new HashMap<Double, String>();
    Product product;
    private ArrayList<Product> list;

    /**
     * @param productList
     */
    public ProductList(ArrayList<Product> productList) {
        this.list = productList;
    }

    public List<Product> getAllItems() {
        return list;
    }

    public String getAllItemsProductPrice() {
        Iterator<Product> itr = list.iterator();
        StringBuffer sb = new StringBuffer();
        // use hasNext() and next() methods of Iterator to iterate through the elements
        while (itr.hasNext()) {
            product = (Product) itr.next();
            if (itr.hasNext())
                sb.append(product.getName() + " : " + product.getProductPrice() + " , ");
            else
                sb.append(product.getName() + " : " + product.getProductPrice());

        }

        return sb.toString();
    }

    /**
     * @return
     */

    public double totalAmountItemsNodiscount(List<Product> list) {
        double totalPrice = 0;
        Iterator<Product> itr = list.iterator();

        // use hasNext() and next() methods of Iterator to iterate through the elements
        while (itr.hasNext()) {
            product = (Product) itr.next();
            if (product.getType().equalsIgnoreCase(Product.PRODUCT_TYPE_ITEM)) {
                totalPrice = totalPrice + product.getProductPrice();
            }
        }
        return totalPrice;
    }

    /**
     * @return
     */

    public double totalAmountGroceryNodiscount(List<Product> list) {
        double totalPrice = 0;
        Iterator<Product> itr = list.iterator();

        // use hasNext() and next() methods of Iterator to iterate through the elements

        while (itr.hasNext()) {
            product = (Product) itr.next();
            if (product.getType().equalsIgnoreCase(Product.PRODUCT_TYPE_GROCERY)) {
                totalPrice = totalPrice + product.getProductPrice();
            }
        }
        return totalPrice;
    }

    public double totalAmountNodiscount(List<Product> list) {
        double totalPrice = 0;
        Iterator<Product> itr = list.iterator();

        // use hasNext() and next() methods of Iterator to iterate through the elements
        while (itr.hasNext()) {
            product = (Product) itr.next();
            totalPrice = totalPrice + product.getProductPrice();

        }
        return totalPrice;
    }

    public Map<Double, String> purchasedProductPrice(ProductList purchasedItems) {
        // get an Iterator object for ArrayList using iterator() method.
        Iterator<Product> itr = purchasedItems.getAllItems().iterator();

        // use hasNext() and next() methods of Iterator to iterate through the elements
        while (itr.hasNext()) {
            product = (Product) itr.next();
            productPriceMap.put(product.getProductPrice(), product.getName());
        }
        return productPriceMap;
    }

    public Map<Double, String> purchasedProductTypePrice(ProductList purchasedItems) {
        // get an Iterator object for ArrayList using iterator() method.
        Iterator<Product> itr = purchasedItems.getAllItems().iterator();

        // use hasNext() and next() methods of Iterator to iterate through the elements
        while (itr.hasNext()) {
            product = (Product) itr.next();
            productPriceMap.put(product.getProductPrice(), product.getType());
        }
        return productPriceMap;
    }

    public Map<Double, String> purchasedProductGrocerryPrice(ProductList purchasedItems) {
        // get an Iterator object for ArrayList using iterator() method.
        Map<Double, String> productPriceGrocery = new HashMap<Double, String>();
        // get an Iterator object for ArrayList using iterator() method.
        Iterator<Product> itr = purchasedItems.getAllItems().iterator();

        // use hasNext() and next() methods of Iterator to iterate through the elements

        while (itr.hasNext()) {
            product = (Product) itr.next();
            if (product.getType().equalsIgnoreCase(Product.PRODUCT_TYPE_GROCERY)) {
                productPriceGrocery.put(product.getProductPrice(), product.getType());
            }

        }
        return productPriceGrocery;
    }

    public Map<Double, String> purchasedProductItemPrice(ProductList purchasedItems) {
        Map<Double, String> productPriceItem = new HashMap<Double, String>();
        // get an Iterator object for ArrayList using iterator() method.
        Iterator<Product> itr = purchasedItems.getAllItems().iterator();

        // use hasNext() and next() methods of Iterator to iterate through the elements
        while (itr.hasNext()) {
            product = (Product) itr.next();
            if (product.getType().equalsIgnoreCase(Product.PRODUCT_TYPE_ITEM)) {
                productPriceItem.put(product.getProductPrice(), product.getType());
            }

        }
        return productPriceItem;
    }
}
