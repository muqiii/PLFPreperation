package db;


import jakarta.ws.rs.NotFoundException;
import pojos.Product;
import pojos.ShoppingCart;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartDB {

    public static ShoppingCartDB theInstance;
    // Name & Shoppingcart
    private final Map<String,ShoppingCart> shoppingcarts;

    private ShoppingCartDB(){
        shoppingcarts = new HashMap<>();

    }

    public static ShoppingCartDB getTheInstance() {
        if(theInstance == null){
            theInstance = new ShoppingCartDB();
        }
        return theInstance;
    }

    public void createShoppingCart(String mail) throws IOException {
        if(shoppingcarts.containsKey(mail)){
            throw new KeyAlreadyExistsException();
        } else {
            shoppingcarts.put(mail, new ShoppingCart());
        }
    }

    public ShoppingCart getShoppingCart(String mail){
        if(!shoppingcarts.containsKey(mail)){
            throw new NotFoundException();
        }
        return shoppingcarts.get(mail);
    }
}






