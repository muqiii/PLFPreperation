package db;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.NotFoundException;
import lombok.Data;
import pojos.Product;
import pojos.ProductM;

import java.io.IOException;
import java.util.List;


@Data
public class ProductDB {
    private static ProductDB theInstance;
    private ProductM product;

    private ProductDB() throws IOException {
        loadData();
    }

    public static ProductDB getTheInstance() throws IOException {
        if(theInstance == null){
            theInstance = new ProductDB();
        }
        return theInstance;
    }

    private void loadData() throws IOException {
        ObjectMapper om = new ObjectMapper();
        product = om.readValue(this.getClass().getClassLoader().getResource("products.json"), ProductM.class);
    }

    public static void main(String[] args) {
        try {
            ProductDB.getTheInstance().loadData();
            System.out.println(ProductDB.getTheInstance().product);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProductById(int id) {
        return product.getProducts().stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new NotFoundException());
    }
}
