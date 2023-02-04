package pojos;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ShoppingCart {
    private Map<Product,Integer> shoppingcart;

    public ShoppingCart(){
        shoppingcart = new HashMap<>();
    }


    public void addProduct(Product product) {
        if(shoppingcart.containsKey(product)){
            int number = shoppingcart.get(product);
            shoppingcart.put(product,number+1);
        }
        else{
            shoppingcart.put(product,1);
        }
    }

    public void removeProduct(Product product){
        if(shoppingcart.containsKey(product) && shoppingcart.get(product) > 1){
            int number = shoppingcart.get(product);
            shoppingcart.put(product,number-1);
        }
        else{
            shoppingcart.remove(product);
        }
    }
}