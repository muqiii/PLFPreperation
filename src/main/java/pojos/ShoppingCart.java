package pojos;
import lombok.Data;
import java.util.Map;

@Data
public class ShoppingCart {
    private Map<Product,Integer> shoppingcart;

    public void addProduct(Product product) {
        if(shoppingcart.keySet().contains(product)){
            int number = shoppingcart.get(product).intValue();
            shoppingcart.put(product,number+1);
        }
        else{
            shoppingcart.put(product,1);
        }
    }

    public void removeProduct(Product product){
        if(shoppingcart.keySet().contains(product) && shoppingcart.get(product).intValue() > 1){
            int number = shoppingcart.get(product).intValue();
            shoppingcart.put(product,number-1);
        }
        else{
            shoppingcart.remove(product);
        }
    }
}
