package db;


import pojos.Product;
import pojos.ShoppingCart;

import java.util.Map;

public class ShoppingCartDB {
    private static ShoppingCartDB theInstance;
    // Name & Shoppingcart
    private Map<Product, Integer> shoppingcarts;

    private ShoppingCartDB(){

    }

    public static ShoppingCartDB getTheInstance() {
        if(theInstance == null){
            theInstance = new ShoppingCartDB();
        }
        return theInstance;
    }




}