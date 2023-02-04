package db;


import pojos.Product;
import pojos.ShoppingCart;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartDB {
    private static ShoppingCartDB theInstance;
    // Name & Shoppingcart
    private Map<String, ShoppingCart> shoppingcarts = new HashMap<>();

    private ShoppingCartDB(){

    }
    public static ShoppingCartDB getTheInstance() {
        if(theInstance == null){
            theInstance = new ShoppingCartDB();
        }
        return theInstance;
    }


    public void createShoppingCart(String mail){
        if(shoppingcarts.containsKey(mail)){
            throw new KeyAlreadyExistsException();
        }
        shoppingcarts.put(mail, new ShoppingCart());
    }

    public ShoppingCart getShoppingCart(String mail){
       return shoppingcarts.get(mail);
    }






}